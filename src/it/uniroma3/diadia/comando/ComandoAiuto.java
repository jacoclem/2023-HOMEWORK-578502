package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{

	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda", "interagisci", "saluta", "regala"};
	
	
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length; i++) 
			this.getIO().mostraMessaggio(elencoComandi[i] + " ");
		
		this.getIO().mostraMessaggio(" ");
		
	}

	@Override
	public String getNome() {
		return "aiuto";
	}








	
}
