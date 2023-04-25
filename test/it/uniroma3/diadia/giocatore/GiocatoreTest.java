package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {

private Giocatore giocatoreNuovo;
private Giocatore giocatoreUsato;
	
	@BeforeEach
	public void setUp() {
		this.giocatoreNuovo = new Giocatore();
		this.giocatoreUsato = new Giocatore();
		this.giocatoreUsato.setCfu(15);
	}
	
	@Test
	final void testGetCfu() {
		assertEquals(20, this.giocatoreNuovo.getCfu());
	}
	
	@Test
	final void testSetCfu() {
		this.giocatoreUsato.setCfu(15);
		assertEquals(15, this.giocatoreUsato.getCfu());
	}
	
	@Test
	final void testToString() {
		String risultato = "CFU Rimanenti: 20\nBorsa: Borsa vuota";
		assertEquals(risultato, this.giocatoreNuovo.toString());
		risultato = "CFU Rimanenti: 15\nBorsa: Borsa vuota";
		assertEquals(risultato, this.giocatoreUsato.toString());
		
	}
}