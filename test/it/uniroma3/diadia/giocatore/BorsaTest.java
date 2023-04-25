package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa borsaVuota;
	private Borsa borsaNonVuota;
	private Attrezzo spada;
	
	@BeforeEach
	public void setUp() {
		this.borsaVuota = new Borsa();
		this.borsaNonVuota = new Borsa();
		this.spada = new Attrezzo("Spada", 5);
		this.borsaNonVuota.addAttrezzo(spada);
	}
	


	@Test
	final void testAddAttrezzo() {
		assertTrue(this.borsaNonVuota.addAttrezzo(spada));
		assertSame(spada, this.borsaNonVuota.getAttrezzo("Spada"));
	}

	@Test
	final void testGetPesoMax() {
		assertEquals(10, this.borsaVuota.getPesoMax());
	}

	@Test
	final void testGetAttrezzo() {
		assertSame(spada, this.borsaNonVuota.getAttrezzo("Spada"));
	}

	@Test
	final void testGetPeso() {
		assertEquals(0, this.borsaVuota.getPeso());
		assertEquals(5, this.borsaNonVuota.getPeso());
	}

	@Test
	final void testIsEmpty() {
		assertTrue(this.borsaVuota.isEmpty());
		assertFalse(this.borsaNonVuota.isEmpty());
	}

	@Test
	final void testHasAttrezzo() {
		assertTrue(this.borsaNonVuota.hasAttrezzo("Spada"));
		assertFalse(this.borsaVuota.hasAttrezzo("Spada"));
	}

	@Test
	final void testRemoveAttrezzo() {
		this.borsaNonVuota.removeAttrezzo("Spada");
		assertTrue(this.borsaNonVuota.isEmpty());
	}

	@Test
	final void testToString() {
		String risultato = "Contenuto borsa (5kg/10kg): Spada (5kg) ";
		assertEquals(risultato, this.borsaNonVuota.toString());
		
	}

}