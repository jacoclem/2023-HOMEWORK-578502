package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	
	Labirinto labirinto;
	Stanza stanzaCorrente;
	private boolean finita;
	Giocatore giocatore;
	
	public Partita(Labirinto labirinto){
		
		this.labirinto=labirinto;
		stanzaCorrente = this.labirinto.getStanzaIniziale();
		giocatore = new Giocatore();
		this.finita = false;
		
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public void setGiocatore(Giocatore giocatore) {
		this.giocatore=giocatore;
	}
	
	/**
	 * Funzione che modifica la stanza corrente
	 * @param stanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	
	/**
	 * Funzione che restituisce la stanza corrente
	 * @return stanzaCorrente
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== labirinto.getStanzaVincente();
	}

	
	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.cfu == 0);
	}

	
	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	
	public boolean giocatoreIsVivo() {
		return !(this.getGiocatore().getCfu()==0);
	}


}