package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{

	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null)
			this.getIO().mostraMessaggio("Nessun oggetto selezionato, cosa vuoi prendere ?");
		else {
			Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(attrezzoDaPrendere != null)
				if(partita.getStanzaCorrente().hasAttrezzo(nomeAttrezzo) && ((partita.getGiocatore().getBorsa().getPeso()+attrezzoDaPrendere.getPeso())<=10))
				{
					partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere));
					this.getIO().mostraMessaggio("Ho preso " + nomeAttrezzo);
				}
				else
				{
					this.getIO().mostraMessaggio("Qualcosa è andato storto, inserisci un nuovo comando");
				}
			else
				this.getIO().mostraMessaggio("Attrezzo non presente nella stanza");
		}
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
		
	}

	@Override
	public String getNome() {
		return "prendi";
	}
	
	@Override
	public String getParametro() {
		
		return this.nomeAttrezzo;
	}

}
