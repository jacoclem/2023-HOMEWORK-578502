package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando{

	private String nomeAttrezzo;
	
	@Override
	public String getNome() {
		return "regala";
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo=parametro;
		
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null)
		{
			this.getIO().mostraMessaggio("In questa stanza non c'è nessuno...");
		}
		else if(this.nomeAttrezzo==null){
			this.getIO().mostraMessaggio("Cosa vuoi regalare ?");
		}else {
		
			Attrezzo attrezzoDaRegalare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(attrezzoDaRegalare == null)
				this.getIO().mostraMessaggio("Non hai questo attrezzo nella borsa");
			else
				//regala l'attrezzo presente nella borsa
				this.getIO().mostraMessaggio(personaggio.riceviRegalo(attrezzoDaRegalare, partita));
		}
	}
		
	

}

