package it.uniroma3.diadia.ambienti;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.FormatoFileNonCorrettoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	
	
	public Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonCorrettoException {
		CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
		
		 c.carica();
		 
		 this.stanzaIniziale= c.getStanzaIniziale();
		 this.stanzaVincente = c.getStanzaVincente();
	}
	
	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonCorrettoException {
		return new LabirintoBuilder(labirinto);
	}
	
	/**
	 * Funzione che restituisce la stanza vincente
	 * @return stanzaVincente
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	/**
	 * Funzione che restituisce la stanza vincente
	 * @return stanzaVincente
	 */
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	
	
	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale=stanza;
	}
	
	public void setStanzaVincente(Stanza stanza) {
		this.stanzaVincente=stanza;
	}

	
	/* CLASSE LABIRINTO BUILDER NIDIFICATA*/
	public static class LabirintoBuilder {


			private Map<String, String> direzioniEReciproco;
			private Stanza ultimaStanza;
			private Labirinto labirinto;
			private Map<String, Stanza> mappaStanze;
			
			public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonCorrettoException {
				
				this.labirinto = new Labirinto(labirinto);
				
				this.mappaStanze = new HashMap<>();
				//direzione scelta, e quella opposta
				this.direzioniEReciproco = new HashMap<String, String>();
				
				this.direzioniEReciproco.put("nord", "sud");
				this.direzioniEReciproco.put("sud", "nord");
				this.direzioniEReciproco.put("est", "ovest");
				this.direzioniEReciproco.put("ovest", "est");
				
			}
			
			/**
			 * Funzione che imposta la stanza iniziale di un labirinto
			 * @param nome
			 * @return labirinto builder
			 */
			public LabirintoBuilder addStanzaIniziale(String nome) {
				Stanza stanza = new Stanza(nome);
				this.mappaStanze.put(nome, stanza);
				this.labirinto.setStanzaIniziale(stanza);
				this.ultimaStanza=stanza;
				return this;
			}
			
			/**
			 * Funzione che imposta la stanza vincente di un labirinto
			 * @param nome
			 * @return labirinto builder
			 */
			public LabirintoBuilder addStanzaVincente(String nome) {
				
				Stanza stanza = new Stanza(nome);
				this.mappaStanze.put(nome, stanza);
				this.labirinto.setStanzaVincente(stanza);
				this.ultimaStanza=stanza;
				return this;
			}
			
			
			public LabirintoBuilder addStanza(String nome) {
				Stanza stanza = new Stanza(nome);
				this.mappaStanze.put(nome, stanza);
				this.ultimaStanza=stanza;
				return this;
			}
			
			public LabirintoBuilder addStanzaMagica(String nome) {
				Stanza stanza = new StanzaMagica(nome);
				this.mappaStanze.put(nome, stanza);
				this.ultimaStanza=stanza;
				return this;
			}
			
			public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
				Stanza stanza = new StanzaMagica(nome, soglia);
				this.mappaStanze.put(nome, stanza);
				this.ultimaStanza=stanza;
				return this;
			}
			
			public LabirintoBuilder addStanzaBuia(String nome, String attrezzo) {
				Stanza stanza = new StanzaBuia(nome, attrezzo);
				this.mappaStanze.put(nome, stanza);
				this.ultimaStanza=stanza;
				return this;
			}
			
			public LabirintoBuilder addStanzaBloccata(String nome, Direzione direzione, String attrezzo) {
				Stanza stanza = new StanzaBloccata(nome, direzione,  attrezzo);
				this.mappaStanze.put(nome, stanza);
				this.ultimaStanza=stanza;
				return this;
			}
			
			
			public LabirintoBuilder addAdiacenza(String partenza, String arrivo, String direzione) {
				Direzione dir = Direzione.valueOf(direzione);
				String direzioneOpposta = this.direzioniEReciproco.get(direzione);
				Direzione dirOpposta = Direzione.valueOf(direzioneOpposta);
				this.mappaStanze.get(partenza).impostaStanzaAdiacente(dir, this.mappaStanze.get(arrivo));
				this.mappaStanze.get(arrivo).impostaStanzaAdiacente(dirOpposta, this.mappaStanze.get(partenza));
				return this;
			}
			
			public LabirintoBuilder addAttrezzo(String nome, int peso) {
				Attrezzo attrezzo = new Attrezzo(nome, peso);
				this.ultimaStanza.addAttrezzo(attrezzo);
				return this;
			}
			
			public LabirintoBuilder addMago(String nome, String presentazione, Attrezzo attrezzo) {
				AbstractPersonaggio personaggio = new Mago(nome, presentazione, attrezzo);
				this.ultimaStanza.addPersonaggio(personaggio);
				return this;
			}
			
			public LabirintoBuilder addCane(String nome, String presentazione, String attrezzoDaRegalare, Attrezzo attrezzoPosseduto) {
				AbstractPersonaggio personaggio = new Cane(nome, presentazione, attrezzoDaRegalare, attrezzoPosseduto);
				this.ultimaStanza.addPersonaggio(personaggio);
				return this;
			}
			
			public LabirintoBuilder addStrega(String nome, String presentazione) {
				AbstractPersonaggio personaggio = new Strega(nome, presentazione);
				this.ultimaStanza.addPersonaggio(personaggio);
				return this;
			}

			public Map<String, Stanza> getListaStanze(){
				return this.mappaStanze;
			}
			
			public Labirinto getLabirinto() {
				return this.labirinto;
			}
		

		
		
		
	}
}
	
