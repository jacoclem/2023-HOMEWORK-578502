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

		if(direzioneBloccata.equals(direzione) && !this.hasAttrezzo(attrezzoCheSblocca)) {
			return this;
		}
		return super.getStanzaAdiacente(direzione);		
	}

	@Override
	public String getDescrizione() {

		String str = "La porta in direzione " + this.direzioneBloccata + " sembra chiusa, cerca qualcosa per aprirla.  \nStanza corrente: " + this.toString();

		if(!this.hasAttrezzo(attrezzoCheSblocca))
			return str;
		else
			
			return super.getDescrizione();
		
	}

}
