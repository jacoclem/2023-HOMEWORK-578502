package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {

	private Partita partita;
	private Stanza stanzaAttuale;
	private Stanza nuovaStanza;
	
	@BeforeEach
	public void setUp() {
		this.partita = new Partita();
		this.stanzaAttuale = new Stanza("atrio");
		this.nuovaStanza = new Stanza("N10");
	}
	
	@Test
	final void testPartita() {
		assertSame("atrio", this.partita.getStanzaCorrente().getNome());
		assertFalse(this.partita.isFinita());
	}

	@Test
	void testSetStanzaCorrente() {
		this.partita.setStanzaCorrente(this.nuovaStanza);
		assertSame(this.nuovaStanza.getNome(), this.partita.getStanzaCorrente().getNome());
	}

	@Test
	void testGetStanzaCorrente() {
		assertSame(this.stanzaAttuale.getNome(), this.partita.getStanzaCorrente().getNome());
	}


	@Test
	void testIsFinita() {
		assertFalse(this.partita.isFinita());
		
	}

	@Test
	void testSetFinita() {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}

}
