package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	
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

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	
	
}