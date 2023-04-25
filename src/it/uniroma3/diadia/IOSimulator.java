package it.uniroma3.diadia;

public class IOSimulator implements IO{

	private String comandiLetti[];
	private int indiceDelProssimoComando;
	
	private String messaggiMostrati[];
	private int indiceMessaggiMostrati;
	
	public IOSimulator(String...comandiLetti) {
		this.comandiLetti=comandiLetti;
		this.indiceDelProssimoComando=0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiMostrati[this.indiceDelProssimoComando++]=messaggio;
	}

	@Override
	public String leggiRiga() {
		
		String stringa = null;
		stringa = this.comandiLetti[this.indiceDelProssimoComando++];
		return stringa;
		
	}

	
	
}
