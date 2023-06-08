package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private Stanza stanzaUscita;
	private Attrezzo chiave;
	
	@BeforeEach
	void setUp() throws Exception {
		chiave = new Attrezzo("chiave", 1);
		stanzaBloccata = new StanzaBloccata("atrio", Direzione.nord, "chiave");
		stanzaUscita = new Stanza("uscita");
	}
	
	@Test
	final void testDirezioneInesistente() {
		assertEquals(this.stanzaBloccata ,this.stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	final void testStanzaBloccataSenzaChiave() {
		this.stanzaBloccata.impostaStanzaAdiacente(Direzione.nord, stanzaUscita);
		assertEquals(this.stanzaBloccata, this.stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	final void testStanzaBloccataConChiave() {
		this.stanzaBloccata.impostaStanzaAdiacente(Direzione.nord, stanzaUscita);
		this.stanzaBloccata.addAttrezzo(chiave);
		assertEquals(this.stanzaUscita, this.stanzaBloccata.getStanzaAdiacente("nord"));
	}
	
	@Test
	final void testDescrizioneSenzaChiave() {
		this.stanzaBloccata.impostaStanzaAdiacente(Direzione.nord, stanzaUscita);
		String output = "La porta in direzione nord sembra chiusa, cerca qualcosa per aprirla.  \nStanza corrente: " + this.stanzaBloccata.toString();
		assertEquals(output, this.stanzaBloccata.getDescrizione());
	}
	
	@Test
	final void testDescrizioneConChiave() {
		this.stanzaBloccata.impostaStanzaAdiacente(Direzione.nord, stanzaUscita);
		this.stanzaBloccata.addAttrezzo(chiave);
		assertEquals("Stanza Corrente: " + this.stanzaBloccata.toString(), this.stanzaBloccata.getDescrizione());
	}

}
