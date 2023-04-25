package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends StanzaProtected{

	private Attrezzo attrezzoSpeciale;
	
	public StanzaBuia(String nome, Attrezzo attrezzo) {
		super(nome);
		this.attrezzoSpeciale = attrezzo;
		
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoSpeciale.getNome()))
			return "Stanza corrente: "+this.toString();
		else
			return "qui c'è un buio pesto";
	}

	
	
}
