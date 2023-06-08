package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonCorrettoException;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

class ComandoPosaTest {

	private Stanza s;
	private Attrezzo a;
	private Partita p;
	private Giocatore g;
	private Borsa b;
	private Comando posa;
	
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonCorrettoException {
		s = new Stanza("aula");
		/*Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaVincente("biblioteca")
				.addAdiacenza("atrio", "biblioteca", "nord")
				.getLabirinto();*/
		
		Labirinto labirinto = Labirinto.newBuilder("Labirinto1.txt").getLabirinto();
		p = new Partita(labirinto);
		g = new Giocatore();
		b = new Borsa();
		posa= new ComandoPosa();
		Scanner scanner = new Scanner(System.in);
		posa.setIO( new IOConsole(scanner) );
		
	}
	
	
	@Test
	final void testPosaNull() {
		a = new Attrezzo("penna", 1);
		p.setStanzaCorrente(s);
		p.getStanzaCorrente().addAttrezzo(a);
		posa.esegui(p);
		assertTrue(s.hasAttrezzo("penna"));
		
	}
	
	@Test
	final void testPosaOggettoPresente() {
		a = new Attrezzo("penna", 1);
		p.setStanzaCorrente(s);
		p.setGiocatore(g);
		p.getGiocatore().setBorsa(b);
		p.getGiocatore().getBorsa().addAttrezzo(a);
		posa.setParametro("penna");
		posa.esegui(p);
		assertFalse(p.getGiocatore().getBorsa().hasAttrezzo("penna"));
		assertTrue(p.getStanzaCorrente().hasAttrezzo("penna"));
	}

	
	@Test
	final void testPosaOggettoInesistente() {
		p.setGiocatore(g);
		p.getGiocatore().setBorsa(b);;
		posa.setParametro("penna");
		posa.esegui(p);
		assertFalse(p.getStanzaCorrente().hasAttrezzo("penna"));
	}
}
