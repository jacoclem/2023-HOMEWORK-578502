package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {

	
	private Stanza s1;
	private Stanza s2;
	private Comando vai;
	private Partita p;
	
	
	@BeforeEach
	public void setUp() {
		s1 = new Stanza("aula 1");
		s2 = new Stanza("aula 2");
		vai = new ComandoVai();
		p = new Partita();
		vai.setIO(new IOConsole());
	}
	
	@Test
	void testVaiNull() {
		p.setStanzaCorrente(s1);
		vai.esegui(p);
		assertEquals(s1, p.getStanzaCorrente());
	}
	
	@Test
	final void testVaiSpostamento() {
		p.setStanzaCorrente(s1);
		p.getStanzaCorrente().impostaStanzaAdiacente("est", s2);
		vai.setParametro("est");
		vai.esegui(p);
		assertEquals(s2, p.getStanzaCorrente());
	}
	
	@Test
	final void testVaiSpostamentoDirezioneInesistente() {
		p.setStanzaCorrente(s1);
		p.getStanzaCorrente().impostaStanzaAdiacente("est", s2);
		vai.setParametro("ovest");
		vai.esegui(p);
		assertNotEquals(s2, p.getStanzaCorrente());
	}

}
