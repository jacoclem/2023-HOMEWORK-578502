package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonCorrettoException;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;

	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonCorrettoException {
		/*labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("biblioteca")
				.getLabirinto();*/
		labirinto = labirinto.newBuilder("Labirinto1.txt").getLabirinto();
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