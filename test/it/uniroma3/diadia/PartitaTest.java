package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {

	private Partita partita;
	private Stanza stanzaAttuale;
	private Stanza nuovaStanza;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonCorrettoException {
		
		Labirinto labirinto = Labirinto.newBuilder("Labirinto1.txt").getLabirinto();
		this.partita = new Partita(labirinto);
		
		this.stanzaAttuale = new Stanza("atrio");
		this.nuovaStanza = new Stanza("N10");
	}
	
	@Test
	final void testPartita() {
		assertEquals(this.stanzaAttuale, this.partita.getStanzaCorrente());
		assertFalse(this.partita.isFinita());
	}

	@Test
	void testSetStanzaCorrente() {
		this.partita.setStanzaCorrente(this.nuovaStanza);
		assertSame(this.nuovaStanza.getNome(), this.partita.getStanzaCorrente().getNome());
	}

	@Test
	void testGetStanzaCorrente() {
		assertEquals(this.stanzaAttuale, this.partita.getStanzaCorrente());
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