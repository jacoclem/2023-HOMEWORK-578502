package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{

	static final String CON_CHI = "Chi devo salutare ?" + "Nella stanza non c'è nessuno ...";
	
	@Override
	public String getNome() {
		return "saluta";
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null) {
			this.getIO().mostraMessaggio(CON_CHI);
		}else {
			this.getIO().mostraMessaggio(personaggio.saluta());
		}
		
	}

}
