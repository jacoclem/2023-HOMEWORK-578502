package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;

	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto();
		stanzaVincente = new Stanza("Biblioteca");
		stanzaIniziale = new Stanza("atrio");
	}
	

	@Test
	final void testGetStanzaVincente() {
		assertSame(this.stanzaVincente.getNome(), this.labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	final void testGetStanzaIniziale() {
		assertSame(this.stanzaIniziale.getNome(), this.labirinto.getStanzaIniziale().getNome());
	}




}