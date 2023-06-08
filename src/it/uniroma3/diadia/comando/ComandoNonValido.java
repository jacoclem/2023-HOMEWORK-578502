package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando{


	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("Comando non valido, digita 'aiuto' per conoscere i comandi disponibili");
		
	}


	@Override
	public String getNome() {
		return "non valido";
	}

}
