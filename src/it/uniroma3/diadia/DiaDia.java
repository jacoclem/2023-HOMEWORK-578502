package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
//import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.Test;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando daEseguire = new Comando(istruzione);
		if(daEseguire == null)
			return false;

		if (daEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (daEseguire.getNome().equals("vai"))
			this.vai(daEseguire.getParametro());
		else if (daEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (daEseguire.getNome().equals("prendi"))
			this.prendi(daEseguire.getParametro());
		else if (daEseguire.getNome().equals("posa"))
			this.posa(daEseguire.getParametro());
		else
			System.out.println("Comando sconosciuto");

		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.giocatore.getCfu() - 1;
			this.partita.giocatore.setCfu(cfu);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
		System.out.println(partita.giocatore.getDescrizione());
	}

	/**
	 * Funzione che prende come parametro un oggetto lo rimuove dalla borsa e lo posa nella stanza
	 * @param attrezzo
	 */
	private void posa(String attrezzo) {
		if(attrezzo == null)
			System.out.println("Nessun oggetto selezionato, cosa vuoi posare ?");
		else {
			Attrezzo attrezzoDaPosare = this.partita.giocatore.borsa.getAttrezzo(attrezzo);
			if(attrezzoDaPosare != null)
			if(this.partita.giocatore.borsa.hasAttrezzo(attrezzo))
			{
				this.partita.getStanzaCorrente().addAttrezzo(this.partita.giocatore.borsa.removeAttrezzo(attrezzo));
				System.out.println(this.partita.getStanzaCorrente().getDescrizione());
				System.out.println(this.partita.getStanzaCorrente().getDescrizione());
				System.out.println(partita.giocatore.getDescrizione());
			}else {
				System.out.println("Qualcosa è andato storto, inserisci un nuovo comando");
				System.out.println(this.partita.getStanzaCorrente().getDescrizione());
				System.out.println(partita.giocatore.getDescrizione());
			}
			else
			{
				System.out.println("Attrezzo non presente nella borsa");
				System.out.println(this.partita.getStanzaCorrente().getDescrizione());
				System.out.println(partita.giocatore.getDescrizione());
			}
		}
	}

	/**
	 * Funzione che prende un oggetto e lo posa nella borsa
	 */
	private void prendi(String attrezzo) {
		if(attrezzo == null)
			System.out.println("Nessun oggetto selezionato, cosa vuoi prendere ?");
		else {
			Attrezzo attrezzoDaPrendere = this.partita.getStanzaCorrente().getAttrezzo(attrezzo);
			if(attrezzoDaPrendere != null)
				if(this.partita.getStanzaCorrente().hasAttrezzo(attrezzo))
				{
					this.partita.giocatore.borsa.addAttrezzo(this.partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere));
					System.out.println(this.partita.getStanzaCorrente().getDescrizione());
					System.out.println(partita.giocatore.getDescrizione());
				}
				else
				{
					System.out.println("Qualcosa è andato storto, inserisci un nuovo comando");
					System.out.println(this.partita.getStanzaCorrente().getDescrizione());
					System.out.println(partita.giocatore.getDescrizione());
				}
			else
				System.out.println("Attrezzo non presente nella stanza");
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}