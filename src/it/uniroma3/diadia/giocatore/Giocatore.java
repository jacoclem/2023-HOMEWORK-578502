package it.uniroma3.diadia.giocatore;


public class Giocatore {

	static final private int CFU_INIZIALI = 20;
	public int cfu;
	public Borsa borsa;
	
	public Giocatore(){
		borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
	}
	
	
	public int getCfu() {
		return this.cfu;
	}
	

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public String getDescrizione() {
		return "Giocatore: " + this.toString();
	}
	
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append("CFU Rimanenti: ");
		risultato.append(this.cfu);
		risultato.append("\nBorsa: ");
		risultato.append(this.borsa.toString());
		return risultato.toString();
		
	}
	
}
