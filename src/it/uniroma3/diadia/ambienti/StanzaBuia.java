package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{

	private String attrezzoSpeciale;
	
	public StanzaBuia(String nome, String attrezzo) {
		super(nome);
		this.attrezzoSpeciale = attrezzo;
		
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoSpeciale))
			return "Stanza corrente: "+this.toString();
		else
			return "qui c'è un buio pesto";
	}

	
	
}
