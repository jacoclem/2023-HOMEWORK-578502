package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{

	private String nomeAttrezzo;
	private IO io;
	
	
	@Override
	public void esegui(Partita partita) {
		if(nomeAttrezzo == null)
			io.mostraMessaggio("Nessun oggetto selezionato, cosa vuoi posare ?");
		else {
			Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(attrezzoDaPosare != null)
			if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo))
			{
				partita.getStanzaCorrente().addAttrezzo(partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo));
				io.mostraMessaggio("Ho posato " + nomeAttrezzo);
			}else {
				io.mostraMessaggio("Qualcosa è andato storto, inserisci un nuovo comando");;
			}
			else
			{
				io.mostraMessaggio("Attrezzo non presente nella borsa");
			}
		}
		
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
		
	}

	@Override
	public void setIO(IO io) {
		this.io=io;
		
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
