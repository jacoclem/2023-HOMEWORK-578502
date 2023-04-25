package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {

	private IOSimulator io;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testLeggiUnComando() {
		assertEquals("fine", new IOSimulator("fine").leggiRiga());
	}
	
	@Test
	void testLeggiDueComandi() {
		IOSimulator ioNuovo = new IOSimulator("vai nord","fine");
		assertEquals("vai nord", ioNuovo.leggiRiga());
		assertEquals("fine", ioNuovo.leggiRiga());
	}
	

}
