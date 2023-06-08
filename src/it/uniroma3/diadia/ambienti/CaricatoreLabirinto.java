package it.uniroma3.diadia.ambienti;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sound.sampled.Line;

import it.uniroma3.diadia.FormatoFileNonCorrettoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	private BufferedReader reader;

	private Map<String, Stanza> stanze;
	private Map<Direzione, Direzione> direzioneEReciproco;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	/* SINTASSI CORRETTA RICHIESTA 
	 * 
	 *Stanza: [NomeStanza], [NomeStanza]
	 *Magica: [NomeStanza], [NomeStanza]
	 *Buia: [NomeStanza] [NomeAttrezzo], [NomeStanza][NomeAttrezzo]
	 *Bloccata: [NomeStanza] [NomeAttrezzo] [Direzione], [NomeStanza][NomeAttrezzo] [Direzione]
	 *Mago: [NomeStanza] [NomeMago] [NomeAttrezzo] [PesoAttrezzo] [ParolaPresentazione], [NomeStanza] [NomeMago] [NomeAttrezzo] [ParolaPresentazione]
	 *Strega: [NomeStanza] [NomeStrega] [ParolaPresentazione], [NomeStanza] [NomeStrega] [ParolaPresentazione]  
	 *Cane: [NomeStanza] [NomeStrega] [AttrezzoDaRegalare] [AttrezzoPosseduto] [ParolaPresentazione], [NomeStanza] [NomeStrega] [AttrezzoDaRegalare] [AttrezzoPosseduto] [ParolaPresentazione]
	 *Iniziale: [NomeStanza]
	 *Vincente: [NomeStanza]
	 *Attrezzo: [NomeAttrezzo] [PesoAttrezzo] [NomeStanza], [NomeAttrezzo] [PesoAttrezzo] [NomeStanza] 
	 *Uscita: [NomeStanzaPartenza] [Direzione] [NomeStanzaDestinazione]
	 */

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.stanze=new HashMap<>();
		this.direzioneEReciproco=new HashMap<>();
		this.direzioneEReciproco.put(Direzione.nord, Direzione.sud);
		this.direzioneEReciproco.put(Direzione.sud, Direzione.nord);
		this.direzioneEReciproco.put(Direzione.ovest, Direzione.est);
		this.direzioneEReciproco.put(Direzione.est, Direzione.ovest);
	}

	/**
	 * Metodo che carica il labirinto da file
	 * @throws FormatoFileNonCorrettoException
	 */
	public void carica() throws FormatoFileNonCorrettoException{
		try {
			
			this.scansionaECreaStanze();
			this.scansionaECreaStanzeMagiche();
			this.scansionaECreaStanzeBloccate();
			this.scansionaECreaStanzeBuie();
			this.scansionaInizialeEVincente();
			this.scansionaECreaMaghi();
			this.scansionaECreaStreghe();
			this.scansionaECreaCani();
			this.scansionaEPosaAttrezzo();
			this.scansionaEImpostaUscite();
			
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Metodo che legge la riga che inizia con una parola chiave passata da parametro
	 * @param chiave
	 * @return
	 * @throws FormatoFileNonCorrettoException
	 */
	public String leggiRigaCheIniziaCon(String chiave) throws FormatoFileNonCorrettoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(chiave), "Era attesa una riga che iniziasse con la parola "+chiave);
			return riga.substring(chiave.length());
		}catch (IOException e) {
			throw new FormatoFileNonCorrettoException(e.getMessage());
		}
	}

	/**
	 * Metodo che controlla una condizione e da un messaggio di errore nel caso in cui sia falsa
	 * @param condizione
	 * @param messaggioErrore
	 * @throws FormatoFileNonCorrettoException
	 */
	public void check(boolean condizione, String messaggioErrore) throws FormatoFileNonCorrettoException {
		if(!condizione)
			throw new FormatoFileNonCorrettoException("Formato Non Corretto [" + ((LineNumberReader) this.reader).getLineNumber() + "] " + messaggioErrore);

	}

	/**
	 * Metodo che restituisce il testo del messaggio di errore
	 * @param msg
	 * @return
	 */
	private String messaggioTerminazioneAnticipata(String msg) {
		return "Terminazione anticipata del programma prima della lettura di "+msg;
	}

	/**
	 * Metodo che restituisce una lista di stringhe separate da una virgola
	 * @param string
	 * @return
	 */
	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}

	/**
	 * Metodo che scansiona lla riga di un file che inizia per "Stanza:" e crea stanze semplici
	 * @throws FormatoFileNonCorrettoException
	 */
	public void scansionaECreaStanze() throws  FormatoFileNonCorrettoException{
		String nomiStanze = this.leggiRigaCheIniziaCon("Stanza:");
		for(String nomeStanza : this.separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.stanze.put(nomeStanza, stanza);
		}
		
	}

	/**
	 * Metodo che scansiona la riga di un file che inizia per "Magica:" e crea stanze magiche
	 */
	public void scansionaECreaStanzeMagiche()throws  FormatoFileNonCorrettoException{
		String nomiStanze = this.leggiRigaCheIniziaCon("Magica:");
		for(String nomeStanza : this.separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new StanzaMagica(nomeStanza);
			this.stanze.put(nomeStanza, stanza);
		}
	}

	/**
	 * Metodo che scansiona la riga di un file che inizia per "Buia:" e crea stanze buie
	 */
	public void scansionaECreaStanzeBuie()throws  FormatoFileNonCorrettoException{
		String nomiStanzeESpecifiche = this.leggiRigaCheIniziaCon("Buia:");
		for(String valore : this.separaStringheAlleVirgole(nomiStanzeESpecifiche)) {

			try (Scanner scannerDiLinea = new Scanner(valore)){
				while(scannerDiLinea.hasNext())
				{
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione della stanza, valore ottenuto: "+valore+"\n") );
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella creazione dell'attrezzo per utilizzare correttamente il comando guarda nella stanza "+valore));
					String nomeAttrezzo = scannerDiLinea.next();

					Stanza stanza = new StanzaBuia(nomeStanza, nomeAttrezzo);
					this.stanze.put(nomeStanza, stanza);
				}
			}
		}
	}

	/**
	 * Metodo che scansiona la riga di un file che inizia per "Bloccata:" e crea stanze bloccate
	 */
	public void scansionaECreaStanzeBloccate() throws FormatoFileNonCorrettoException{
		String nomiStanzeESpecifiche = this.leggiRigaCheIniziaCon("Bloccata:");
		for(String valore : this.separaStringheAlleVirgole(nomiStanzeESpecifiche)) {
			try (Scanner scannerDiLinea = new Scanner(valore)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione della stanza, valore ottenuto: " + valore +"\n"));
					String nomeStanza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), messaggioTerminazioneAnticipata("Errore nella creazione dell'attrezzo che consente alla direzione di essere sbloccata nella stanza "+valore+ "\n"));
					String nomeAttrezzo = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("La direzione della stanza "+nomeStanza+ " non esiste \n"));
					Direzione direzione = Direzione.valueOf(scannerDiLinea.next());

					Stanza stanza = new StanzaBloccata(nomeStanza, direzione,  nomeAttrezzo);
					stanze.put(nomeStanza, stanza);
				}
			}
		}
	}


	/**
	 * Metodo che scansiona la riga di un file che inizia per "Mago:" e crea un personaggio di tipo mago
	 */
	public void scansionaECreaMaghi() throws FormatoFileNonCorrettoException{
		String nomiStanzeESpecifiche = this.leggiRigaCheIniziaCon("Mago:");
		for(String valore : this.separaStringheAlleVirgole(nomiStanzeESpecifiche)) {
			try(Scanner scannerDiLinea = new Scanner(valore)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione della stanza, valore ottenuto: "+valore+"\n"));
					String nomeStanza = scannerDiLinea.next();
					check(this.isStanzaValida(nomeStanza), this.messaggioTerminazioneAnticipata("La stanza dove inserire il personaggio non esiste "+valore+"\n"));
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione del mago"+valore+"\n"));
					String nomeMago = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione dell'attrezzo che il mago deve donare "+valore+"\n"));
					String nomeAttrezzo = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione dell'attrezzo che il mago deve donare "+valore+"\n"));
					int peso = Integer.parseInt(scannerDiLinea.next());
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella creazione della presentazione del mago "+valore+"\n"));
					String presentazione = scannerDiLinea.next();

					AbstractPersonaggio personaggio = new Mago(nomeMago, presentazione, new Attrezzo(nomeAttrezzo, peso));
					stanze.get(nomeStanza).setPersonaggio(personaggio);

				}
			}
		}
	}

	/**
	 * Metodo che scansiona la riga di un file che inizia per "Strega:" e crea un personaggio di tipo strega
	 */
	public void scansionaECreaStreghe() throws FormatoFileNonCorrettoException{
		String nomiStanzeESpecifiche = this.leggiRigaCheIniziaCon("Strega:");		
		for(String valore : this.separaStringheAlleVirgole(nomiStanzeESpecifiche)) {
			try(Scanner scannerDiLinea = new Scanner(valore)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione della stanza, valore ottenuto: "+valore+"\n"));
					String nomeStanza = scannerDiLinea.next();
					check(stanze.containsKey(nomeStanza), this.messaggioTerminazioneAnticipata("La stanza dove inserire il personaggio non esiste "+valore+"\n"));
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione della strega"+valore+"\n"));
					String nomeStrega = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella creazione della presentazione della strega "+valore+"\n"));
					String presentazione = scannerDiLinea.next();

					AbstractPersonaggio personaggio = new Strega(nomeStrega, presentazione);
					stanze.get(nomeStanza).setPersonaggio(personaggio);

				}
			}
		}
	}

	/**
	 * Metodo che scansiona la riga di un file che inizia per "Cane:" e crea un personaggio di tipo cane
	 */
	public void scansionaECreaCani() throws FormatoFileNonCorrettoException{
		String nomiStanzeESpecifiche = this.leggiRigaCheIniziaCon("Cane:");
		for(String valore : this.separaStringheAlleVirgole(nomiStanzeESpecifiche)) {
			
			try(Scanner scannerDiLinea = new Scanner(valore)){
				while(scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione della stanza, valore ottenuto: "+valore+"\n"));
					String nomeStanza = scannerDiLinea.next();
					check(stanze.containsKey(nomeStanza), this.messaggioTerminazioneAnticipata("La stanza dove inserire il personaggio non esiste "+valore+"\n"));
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione del cane"+valore+"\n"));
					String nomeCane = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione dell'attrezzo da regalare al cane "+valore+"\n"));
					String nomeAttrezzoDaRegalare = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella crezione dell'attrezzo che il cane regala "+valore+"\n"));
					String nomeAttrezzoCheRegala = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella creazione della presentazione del cane "+valore+"\n"));
					int peso = Integer.parseInt(scannerDiLinea.next());
					check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nella creazione della presentazione del cane "+valore+"\n"));
					String presentazione = scannerDiLinea.next();
					check(!scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Qualcosa è andato storto, forse hai inserito un parametro in più o in meno "+valore));
					

					AbstractPersonaggio personaggio = new Cane(nomeCane, presentazione, nomeAttrezzoDaRegalare, new Attrezzo(nomeAttrezzoCheRegala, peso));
					stanze.get(nomeStanza).setPersonaggio(personaggio);
				}
			}
		}
	}

	/**
	 * Metodo che stabilisce se una stanza è valida
	 * @param nome
	 * @return
	 */
	public boolean isStanzaValida(String nome) {
		return this.stanze.containsKey(nome);
	}

	/**
	 * Metodo che scansiona le righe di un file che iniziano con "Iniziale:" e "Vincente:" e imposta la stanza iniziale e quella vincente
	 * @throws FormatoFileNonCorrettoException
	 */
	public void scansionaInizialeEVincente() throws FormatoFileNonCorrettoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale=this.leggiRigaCheIniziaCon("Iniziale:");
		check(this.isStanzaValida(nomeStanzaIniziale), this.messaggioTerminazioneAnticipata("La stanza iniziale "+ nomeStanzaIniziale + " non esiste\n"));
		String nomeStanzaVincente=this.leggiRigaCheIniziaCon("Vincente:");
		check(this.isStanzaValida(nomeStanzaVincente), this.messaggioTerminazioneAnticipata("La stanza vincente "+ nomeStanzaVincente + " non esiste \n"));
		this.stanzaIniziale = this.stanze.get(nomeStanzaIniziale);
		this.stanzaVincente = this.stanze.get(nomeStanzaVincente);
	}

	/**
	 * Metodo che scansiona la riga di un file che inizia con "Attrezzo:" e aggiunge in una stanza l'attrezzo descritto
	 * @throws FormatoFileNonCorrettoException
	 */
	public void scansionaEPosaAttrezzo() throws FormatoFileNonCorrettoException {
		String valore = this.leggiRigaCheIniziaCon("Attrezzo:");
		for(String attrezzo : this.separaStringheAlleVirgole(valore)) {
			try (Scanner scannerDiLinea = new Scanner(attrezzo)){
				check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Non esiste il nome di un attrezzo " + valore + "\n"));
				String nomeAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Non esiste il peso di un attrezzo " + valore + "\n"));
				String pesoAttrezzo = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Non esiste il nome della stanza in cui mettere un attrezzo " + valore + "\n"));
				String nomeStanza = scannerDiLinea.next();
				check(this.stanze.containsKey(nomeStanza), this.messaggioTerminazioneAnticipata("La stanza in cui è contenuto un attrezzo non esiste " + valore + "\n"));
				aggiungiAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
			}
		}
	}

	/**
	 * Metodo che crea un oggetto e lo colloca in una stanza
	 * @param nome
	 * @param pesoStringa
	 * @param stanza
	 * @throws FormatoFileNonCorrettoException
	 */
	public void aggiungiAttrezzo(String nome, String pesoStringa, String stanza) throws FormatoFileNonCorrettoException {
		try {
			int peso = Integer.parseInt(pesoStringa);	
			Attrezzo attrezzo = new Attrezzo(nome, peso);
			check(this.isStanzaValida(stanza), this.messaggioTerminazioneAnticipata("La stanza "+stanza+" non è valida"));
			this.stanze.get(stanza).addAttrezzo(attrezzo);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nome+ " non valido");
		}

	}
	
	/**
	 * Metodo che crea e imposta le adiacenze tra le stanze
	 * @throws FormatoFileNonCorrettoException
	 */
	public void scansionaEImpostaUscite() throws FormatoFileNonCorrettoException {
		String valore = this.leggiRigaCheIniziaCon("Uscita:");
		for(String uscita : this.separaStringheAlleVirgole(valore)) {
			try (Scanner scannerDiLinea = new Scanner(uscita)){
				check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nel selezionare la stanza di partenza\n"));
				String stanzaPartenza = scannerDiLinea.next();
				check(isStanzaValida(stanzaPartenza), this.messaggioTerminazioneAnticipata("Errore nel selezionare la stanza di partenza"));
				check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nel selezionare la direzione dalla stanza "+stanzaPartenza+"\n"));
				String direzione = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), this.messaggioTerminazioneAnticipata("Errore nel selezionare la stanza di destinazione"));
				String stanzaDestinazione = scannerDiLinea.next();
				check(isStanzaValida(stanzaDestinazione), this.messaggioTerminazioneAnticipata("Errore nel selezionare la stanza di destinazione"));
				
				collocaUscita(stanzaPartenza, direzione, stanzaDestinazione);
			}
		}
	}
	
	/**
	 * Metodo che crea una singola adiacenza
	 * @param partenza
	 * @param direzione
	 * @param destinazione
	 */
	public void collocaUscita(String partenza, String direzione, String destinazione) {
		Stanza stanzaPartenza = this.stanze.get(partenza);
		Stanza stanzaDestinazione = this.stanze.get(destinazione);
		Direzione stanzaDirezione = Direzione.valueOf(direzione);
		Direzione stanzaDirezioneOpposta = this.direzioneEReciproco.get(stanzaDirezione);
		stanzaPartenza.impostaStanzaAdiacente(stanzaDirezione, stanzaDestinazione);
		stanzaDestinazione.impostaStanzaAdiacente(stanzaDirezioneOpposta, stanzaPartenza);
		
	}
	
	
	
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}



}