package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{

	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato !");  // si desidera smettere
		partita.setFinita();		
	}
	

	@Override
	public void setParametro(String parametro) {
		
	}


	@Override
	public void setIO(IO io) {
		this.io=io;
		
	}


	@Override
	public String getNome() {
		return "fine";
	}


	@Override
	public String getParametro() {
		
		return null;
	}

}
