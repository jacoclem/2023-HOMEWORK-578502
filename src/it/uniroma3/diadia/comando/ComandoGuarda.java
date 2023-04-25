package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{

	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio(partita.getGiocatore().getDescrizione());
		
	}

	@Override
	public void setParametro(String parametro) {
		
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
		
	}

	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		
		return null;
	}

	
}
