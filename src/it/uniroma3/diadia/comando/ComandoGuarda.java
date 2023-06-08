package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{

	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		this.getIO().mostraMessaggio(partita.getGiocatore().getDescrizione());
		
	}
	

	@Override
	public String getNome() {
		return "guarda";
	}
	
	
}
