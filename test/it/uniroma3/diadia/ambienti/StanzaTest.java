package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza vuota;
	private Stanza nonVuota;
	private Attrezzo spada;


	@BeforeEach
	public void setUp() {
		this.vuota = new Stanza("vuota");
		this.nonVuota = new Stanza("non-vuota");
		this.spada = new Attrezzo("spada", 5);
		this.nonVuota.addAttrezzo(spada);

	}
	
	@Test
	final void testAddAttrezzo() {
		this.vuota.addAttrezzo(new Attrezzo("spada", 5));
		assertFalse(this.vuota.hasAttrezzo("libro"));
		assertTrue(this.vuota.hasAttrezzo("spada"));
	}
	
	
	@Test
	final void testhasAttrezzo() {
		assertFalse(this.vuota.hasAttrezzo("libro"));
		assertTrue(this.nonVuota.hasAttrezzo("spada"));
		assertFalse(this.nonVuota.hasAttrezzo("libro"));
	}
	
	@Test
	final void testGetAttrezzo() {
		assertEquals(this.spada, this.nonVuota.getAttrezzo("spada"));
		assertEquals(null, this.vuota.getAttrezzo("spada"));
	}
	
	@Test
	final void testRemoveAttrezzo() {
		assertEquals(this.spada, this.nonVuota.removeAttrezzo(this.spada));
		assertFalse(this.nonVuota.hasAttrezzo("spada"));
		assertEquals(null, this.vuota.removeAttrezzo(this.spada));
		
	}
}