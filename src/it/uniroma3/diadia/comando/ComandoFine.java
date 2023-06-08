package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {
		this.getIO().mostraMessaggio("Grazie di aver giocato !");  // si desidera smettere
		partita.setFinita();		
	}
	

	@Override
	public String getNome() {
		return "fine";
	}

}
