package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	private StanzaMagica stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.stanza = new StanzaMagica("stanza");
		this.attrezzo = new Attrezzo("arma", 1);
	}
	
	@Test
	final void testNonMagica() {
		assertTrue(this.stanza.addAttrezzo(attrezzo));
		assertSame(this.attrezzo, this.stanza.getAttrezzo("arma"));
	}
	
	@Test
	final void testAddAttrezzo() {
		for(int i=0;i<StanzaMagica.SOGLIA_MAGICA_DEFAULT-1;i++) {
			assertTrue(this.stanza.addAttrezzo(attrezzo));
		}
		assertTrue(this.stanza.addAttrezzo(attrezzo));
	}
	
	@Test
	final void testIsMagic() {
		for(int i=0;i<StanzaMagica.SOGLIA_MAGICA_DEFAULT;i++)
		{
			Attrezzo attrezzoNuovo = new Attrezzo("attrezzo", 1);
			assertTrue(this.stanza.addAttrezzo(attrezzoNuovo));
		}
		Attrezzo magico = new Attrezzo("magico", 1);
		this.stanza.addAttrezzo(magico);
		assertNull(this.stanza.getAttrezzo("magico"));
		assertNotNull(this.stanza.getAttrezzo("ocigam"));
		Attrezzo ocigam = this.stanza.getAttrezzo("ocigam");
		assertEquals(new Attrezzo("ocigam", 2), ocigam);
	}
	
	
}
