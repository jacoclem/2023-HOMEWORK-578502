package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando{

	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando non valido, digita 'aiuto' per conoscere i comandi disponibili");
		
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
		return "non valido";
	}
	
	@Override
	public String getParametro() {
		
		return null;
	}

}
