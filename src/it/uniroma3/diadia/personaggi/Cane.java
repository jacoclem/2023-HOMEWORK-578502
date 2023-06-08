package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	final static String msg = "Gnam!! ti ho morso, hai perso dei CFU!";
	
	final static String MESSAGGIO_REGALO = "Gnam! Questo è il mio cibo preferito";
	
	final static String MESSAGGIO_REGALO_RIFIUTATO = "Gnam!, portami qualcosa da mangiare altrimenti ti mordo ancora";
	
	private String attrezzoSpeciale;
	private Attrezzo attrezzoPosseduto;
	
	public Cane(String nome, String presentazione, String attrezzoDaRegalare, Attrezzo attrezzoPosseduto) {
		super(nome, presentazione);
		this.attrezzoSpeciale=attrezzoDaRegalare;
		this.attrezzoPosseduto=attrezzoPosseduto;
	}

	@Override
	public String agisci(Partita partita) {
		String msg = this.msg;
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-2);
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if(this.attrezzoSpeciale==attrezzo.getNome())
		{
			msg=MESSAGGIO_REGALO;
			partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
			partita.getStanzaCorrente().addAttrezzo(attrezzoPosseduto);
		}
		else
		{
			msg=MESSAGGIO_REGALO_RIFIUTATO;
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		}
		
		return msg;
	}

}
