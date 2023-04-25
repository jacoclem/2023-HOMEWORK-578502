package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{

	
	private String direzioneBloccata;
	private String attrezzoCheSblocca;
	
	
	public StanzaBloccata(String nome, String direzione, String attrezzo) {
		super(nome);
		this.direzioneBloccata=direzione;
		this.attrezzoCheSblocca=attrezzo;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {		
		
		if((direzione.equals(direzioneBloccata)) && (this.hasAttrezzo(attrezzoCheSblocca))) {
			return super.getStanzaAdiacente(direzione);
		}
		else
		{
			if(!(direzione.equals(direzioneBloccata)))
				return super.getStanzaAdiacente(direzione);
			else
				return this;
		}
	}
	
	@Override
	public String getDescrizione() {
		if(this.hasAttrezzo(attrezzoCheSblocca)) {
			return "Stanza corrente: "+this.toString();
		}
		else
			return "La porta in direzione " + this.direzioneBloccata + " sembra chiusa a chiave, cerca qualcosa per aprirla.  \nStanza corrente: " + this.toString();
	}
	
}
