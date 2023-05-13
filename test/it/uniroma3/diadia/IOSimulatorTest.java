package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	private IOSimulator io;
	
	private List<String> stringa;
	@BeforeEach
	void setUp() throws Exception {
		stringa = new ArrayList<String>();
	}

	@Test
	void testLeggiUnComando() {
		
		stringa.add("fine");
		assertEquals("fine", new IOSimulator(stringa).leggiRiga());
	}
	
	@Test
	void testLeggiDueComandi() {
		stringa.add("vai nord");
		stringa.add("fine");
		IOSimulator ioNuovo = new IOSimulator(stringa);
		assertEquals("vai nord", ioNuovo.leggiRiga());
		assertEquals("fine", ioNuovo.leggiRiga());
	}
	
	@Test
	void testMostraMessaggio() {
		IOSimulator io = new IOSimulator(stringa);
		io.mostraMessaggio("ciao mondo");
		assertEquals(1, io.getNumeroMessaggiProdotti());
		assertEquals("ciao mondo", io.getMessaggioMostrato(0));
	}
	
	@Test
	void testDoppioMessaggio() {
		IOSimulator io = new IOSimulator(stringa);
		io.mostraMessaggio("ciao");
		io.mostraMessaggio("mondo");
		assertEquals(2, io.getNumeroMessaggiProdotti());
		assertEquals("ciao", io.getMessaggioMostrato(0));
		assertEquals("mondo", io.getMessaggioMostrato(1));
	}
	
	@Test
	void testLeggiEMostra() {
		stringa.add("ciao");
		IOSimulator io = new IOSimulator(stringa);
		io.mostraMessaggio("mondo");
		assertEquals("ciao", io.leggiRiga());
		assertEquals("mondo", io.getMessaggioMostrato(0));
	}
	

	
	
	

}
