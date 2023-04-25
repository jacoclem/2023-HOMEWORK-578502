package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private StanzaBuia stanzaBuia;
	private Attrezzo lanterna;
	private Attrezzo osso;
	
	@BeforeEach
	void setUp() throws Exception {
		lanterna = new Attrezzo("lanterna", 1);
		osso = new Attrezzo("osso", 1);
		stanzaBuia = new StanzaBuia("stanzaBuia", lanterna);
	}

	@Test
	void testStanzaBuiaVuota() {
		assertEquals("qui c'è un buio pesto", this.stanzaBuia.getDescrizione());
	}
	
	@Test
	void testStanzaBuiaConOsso() {
		this.stanzaBuia.addAttrezzo(osso);
		assertEquals("qui c'è un buio pesto", this.stanzaBuia.getDescrizione());
	}
	
	@Test
	void testStanzaBuiaConLanterna() {
		this.stanzaBuia.addAttrezzo(lanterna);
		assertEquals("Stanza corrente: "+this.stanzaBuia.toString(), this.stanzaBuia.getDescrizione());
	}

}
