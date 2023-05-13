package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Map<String, String> direzioni;
	private Stanza ultimaStanza;
	private Labirinto labirinto;
	private Map<String, Stanza> mappaStanze;
	
	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.mappaStanze = new HashMap<>();
		//direzione scelta, e quella opposta
		this.direzioni = new HashMap<String, String>();
		this.direzioni.put("nord", "sud");
		this.direzioni.put("sud", "nord");
		this.direzioni.put("est", "ovest");
		this.direzioni.put("ovest", "est");
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
	
	public LabirintoBuilder addStanzaBloccata(String nome, String direzione, String attrezzo) {
		Stanza stanza = new StanzaBloccata(nome, direzione,  attrezzo);
		this.mappaStanze.put(nome, stanza);
		this.ultimaStanza=stanza;
		return this;
	}
	
	
	public LabirintoBuilder addAdiacenza(String partenza, String arrivo, String direzione) {
		String direzioneOpposta = this.direzioni.get(direzione);
		this.mappaStanze.get(partenza).impostaStanzaAdiacente(direzione, this.mappaStanze.get(arrivo));
		this.mappaStanze.get(arrivo).impostaStanzaAdiacente(direzioneOpposta, this.mappaStanze.get(partenza));
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		Attrezzo attrezzo = new Attrezzo(nome, peso);
		this.ultimaStanza.addAttrezzo(attrezzo);
		return this;
	}
	
	public Map<String, Stanza> getListaStanze(){
		return this.mappaStanze;
	}
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
}
