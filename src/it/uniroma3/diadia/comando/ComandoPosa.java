package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando{

	private String nomeAttrezzo;
	
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null)
			this.getIO().mostraMessaggio("Nessun oggetto selezionato, cosa vuoi posare ?");
		else {
			Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(attrezzoDaPosare != null)
			if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo))
			{
				partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo));
				this.getIO().mostraMessaggio("Ho posato " + nomeAttrezzo);
			}else {
				this.getIO().mostraMessaggio("Qualcosa è andato storto, inserisci un nuovo comando");;
			}
			else
			{
				this.getIO().mostraMessaggio("Attrezzo non presente nella borsa");
			}
		}
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
		
	}

	@Override
	public String getNome() {
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	
}
