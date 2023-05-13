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
		labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("biblioteca")
				.getLabirinto();
		stanzaVincente = new Stanza("biblioteca");
		stanzaIniziale = new Stanza("atrio");
	}
	

	@Test
	final void testGetStanzaVincente() {
		assertEquals(this.stanzaVincente, this.labirinto.getStanzaVincente());
	}
	
	@Test
	final void testGetStanzaIniziale() {
		assertEquals(this.stanzaIniziale, this.labirinto.getStanzaIniziale());
	}




}