package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{

	private String direzione;

	
	
	@Override
	public void esegui(Partita partita) {
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(direzione == null)
		{
			this.getIO().mostraMessaggio("Dove vuoi andare ? Indica una direzione");
			return;
		}
		
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if(prossimaStanza == null)
		{
			this.getIO().mostraMessaggio("Direzione inesistente");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		this.getIO().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		
		
		
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;
		
	}


	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		
		return this.direzione;
	}

}
