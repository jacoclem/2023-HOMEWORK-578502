package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.ComparatorePesoNome;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	private AbstractPersonaggio personaggio;
	
	

	/*
	 * Inserisco le nuove implementazioni con un 2 alla fine del nome
	 */

	private String nome;
	private Map<String, Attrezzo> attrezzi;

	private Map<Direzione, Stanza> direzioni;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.direzioni = new HashMap<>();
		this.attrezzi = new HashMap<>();
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio=personaggio;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public boolean impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		if(this.direzioni.size()<NUMERO_MASSIMO_DIREZIONI) {
			this.direzioni.put(direzione, stanza);
			return true;
		}
		else
			return false;
	}

	/**	
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.direzioni.get(Direzione.valueOf(direzione));
	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return "Stanza Corrente: " + this.toString();
	}


	/**
	 * Restituisce il numero di stanze adiacenti
	 */
	public int getNumeroStanzeAdiacenti() {
		return this.direzioni.size();
	}


	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public List<Attrezzo> getAttrezzi() {

		List<Attrezzo> lista = new ArrayList<>(this.attrezzi.values());

		return   lista;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;

	}
	
	
	//se esiste un personaggio lo sovrascrivo poichè in una stanza può esistere solo un personaggio
	public boolean addPersonaggio(AbstractPersonaggio personaggio) {
		
			this.personaggio=personaggio;
			return true;
		
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.getDirezioni().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.getAttrezzi().toString());
		risultato.append("\nPersonaggi nella stanza: ");
		if(this.personaggio==null)
			risultato.append("Nessun personaggio nella stanza");
		else
			risultato.append(this.personaggio.getNome());
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}


	public boolean hasDirezione(String direzione) {
		return this.direzioni.containsKey(direzione);
	}

	/**
	 * Funzione che ritorna il numero di attrezzi presenti nella stanza
	 * @return intero numero attrezzi
	 */
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(this.hasAttrezzo(nomeAttrezzo))
			return this.attrezzi.get(nomeAttrezzo);
		else
			return null;
	}


	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {

		return 	this.direzioni;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return ritorna l'attrezzo rimosso o null se non esiste
	 */
	public Attrezzo removeAttrezzo(Attrezzo attrezzo) {

		if(this.attrezzi.containsKey(attrezzo.getNome()))
		{
			if(this.attrezzi.remove(attrezzo.getNome(), attrezzo))
			{
				return attrezzo;
			}
			else
				return null;
		}
		else
			return null;

	}
	

	public Set<Direzione> getDirezioni() {
		return this.direzioni.keySet();
	}

	/**
	 * Funzione che restituisce la stanza adiacente con più attrezzi
	 * @return
	 */
	public Stanza getStanzaConMaxAttrezzi() {
		Map<Direzione, Stanza> stanzeAdiacenti = this.getMapStanzeAdiacenti();
		if(stanzeAdiacenti == null)
			return null;
		Stanza stanzaAttrezziMax=null;;
		int attrezziMax=-1;
		for(Stanza stanza : stanzeAdiacenti.values()) {
			if(attrezziMax==-1)
			{
				attrezziMax = stanza.getNumeroAttrezzi();
				stanzaAttrezziMax=stanza;
			}
			else {
				if(stanza.getNumeroAttrezzi() > attrezziMax) {
					attrezziMax = stanza.getNumeroAttrezzi();
					stanzaAttrezziMax=stanza;
				}
			}
		}
		return stanzaAttrezziMax;
	}
	
	/**
	 * Funzione che restituisce la stanza con il numero minore di attrezzi
	 * @return
	 */
	public Stanza getStanzaConMinAttrezzi() {
		Map<Direzione, Stanza> stanzeAdiacenti = this.getMapStanzeAdiacenti();
		if(stanzeAdiacenti == null)
			return null;
		Stanza stanzaAttrezziMax=null;;
		int attrezziMax=-1;
		for(Stanza stanza : stanzeAdiacenti.values()) {
			if(attrezziMax==-1)
			{
				attrezziMax = stanza.getNumeroAttrezzi();
				stanzaAttrezziMax=stanza;
			}
			else {
				if(stanza.getNumeroAttrezzi() < attrezziMax) {
					attrezziMax = stanza.getNumeroAttrezzi();
					stanzaAttrezziMax=stanza;
				}
			}
		}
		return stanzaAttrezziMax;
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(this == o)
			return true;
		Stanza that = (Stanza)o;
		return this.getNome().equals(that.getNome());
	}


}