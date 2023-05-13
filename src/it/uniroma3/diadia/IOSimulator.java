package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{

	private List<String> comandiLetti;
	private int indiceComandiLetti;
	

	private List<String> messaggiProdotti;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiMostrati;
	
	public IOSimulator(List<String> comandiLetti) {
		this.comandiLetti=comandiLetti;
		this.indiceComandiLetti=0;
		this.indiceMessaggiMostrati=0;
		this.messaggiProdotti = new ArrayList<String>();
	}
	
	
	public List<String> getMessaggiProdotti(){
		return messaggiProdotti;
	}
	
	public void setMessaggiProdotti(List<String> messaggiProdotti) {
		this.messaggiProdotti = messaggiProdotti;
	}
	
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(messaggio);
		this.indiceMessaggiProdotti++;
	}

	@Override
	public String leggiRiga() {
			
		String stringa = null;
		
		stringa = this.comandiLetti.get(indiceComandiLetti);
		this.indiceComandiLetti++;
		return stringa;
		
	}
	
	
	public String getMessaggioMostrato(int i) {
		return this.messaggiProdotti.get(i);
	}
	
	public List<String> getAllMessaggiMostrati() {
		return this.messaggiProdotti;
	}
	
	
	public boolean hasNextMessage() {
		return this.indiceMessaggiMostrati < this.indiceMessaggiProdotti;
	}
	
	public String getNextMessage() {
		String msg = this.messaggiProdotti.get(indiceMessaggiMostrati);
		this.indiceMessaggiMostrati++;
		return msg;
	}


	public Integer getNumeroMessaggiProdotti() {
		return this.indiceMessaggiProdotti;
	}

	
	
}
