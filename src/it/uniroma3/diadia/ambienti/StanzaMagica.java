package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends StanzaProtected{

	final static protected int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	
	/**
	 * MGC
	 * @param nome
	 * @param soglia
	 */
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati=0;
		this.sogliaMagica=soglia;	
	}
	
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		//this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati<this.sogliaMagica) {
			//attrezzo = this.modificaAttrezzo(attrezzo);
			this.contatoreAttrezziPosati++;
			
		}
		else {
			attrezzo = modificaAttrezzo(attrezzo);
			
		}
		return super.addAttrezzo(attrezzo);
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInverso = new StringBuilder(attrezzo.getNome());
		nomeInverso = nomeInverso.reverse();
		return new Attrezzo(nomeInverso.toString(), attrezzo.getPeso()*2);
	}
	
	
	
	
	
	
}
