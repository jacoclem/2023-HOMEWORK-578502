package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	
	static final String NON_SALUTATA = "Come osi non salutarmi, adesso ti spedirò altrove";
	static final String SALUTATA = "Grazie al mio potere ti teletrasporterò in una stanza piena di attrezzi";
	static final String MESSAGGIO_REGALO = "Ahahahah pensavi di ottenere qualcosa in cambio...";
	
	public Strega (String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if(this.haSalutato()) {
			msg=SALUTATA;
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaConMaxAttrezzi());
		}else
		{
			msg = NON_SALUTATA;
			partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaConMinAttrezzi());
		}
		
		return msg;
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		String msg;
		if(attrezzo==null)
			return null;
		msg=MESSAGGIO_REGALO;
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		
		return msg;
	}

}
