package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

class ComandoPrendiTest {

	private Stanza s;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo a3;
	private Giocatore g;
	private Borsa b;
	private Partita p;
	private Comando prendi;
	
	@BeforeEach
	public void setUp() {
		p = new Partita();
		s = new Stanza("aula");
		a1 = new Attrezzo("penna", 1);
		a2 = new Attrezzo("libro", 15);
		a3 = new Attrezzo("astuccio", 5);
		g = new Giocatore();
		b = new Borsa();
		prendi = new ComandoPrendi();
		prendi.setIO(new IOConsole());
		p.setStanzaCorrente(s);
		p.setGiocatore(g);
		p.getGiocatore().setBorsa(b);
	}
	
	@Test
	final void testInputNull() {
		p.getStanzaCorrente().addAttrezzo(a1);
		prendi.esegui(p);
		assertTrue(p.getStanzaCorrente().hasAttrezzo("penna"));
	}
	
	@Test
	final void testPrendiOggettoEsistente() {
		p.getStanzaCorrente().addAttrezzo(a1);
		prendi.setParametro("penna");
		prendi.esegui(p);
		assertFalse(p.getStanzaCorrente().hasAttrezzo("penna"));
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("penna"));
	}
	
	@Test
	final void testPrendiOggettoTroppoPesante() {
		p.getStanzaCorrente().addAttrezzo(a2);
		prendi.setParametro("libro");
		prendi.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("libro"));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("libro"));
	}
	
	@Test
	final void testPrendiDueOggetti() {
		
		p.getStanzaCorrente().addAttrezzo(a1);
		p.getStanzaCorrente().addAttrezzo(a3);
		assertEquals(2, p.getStanzaCorrente().getNumeroAttrezzi());
		prendi.setParametro("penna");
		prendi.esegui(p);
		prendi.setParametro("astuccio");
		prendi.esegui(p);
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("penna"));
		assertTrue(p.getGiocatore().getBorsa().hasAttrezzo("astuccio"));
		assertEquals(0, p.getStanzaCorrente().getNumeroAttrezzi());
	}

}