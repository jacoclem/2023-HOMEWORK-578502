package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.fixture.Fixture;

class ComandoVaiTest {

	
	private Stanza s1;
	private Stanza s2;
	private Comando vai;
	private Partita p;
	private Labirinto labirinto;
	private List<String> comandiDaLeggere;
	private IO io;
	
	
	
	@BeforeEach
	public void setUp() throws Exception {
		io = new IOConsole();
		s1 = new Stanza("aula 1");
		s2 = new Stanza("aula 2");
		vai = new ComandoVai();
		labirinto = new Fixture().labirintoBilocale();
		p = new Partita(labirinto);
		vai.setIO(io);
		comandiDaLeggere = new ArrayList<>();

	}

	
	@Test
	void testVaiNull() {
		p.setStanzaCorrente(s1);
		vai.esegui(p);
		assertEquals(s1, p.getStanzaCorrente());
	}
	
	@Test
	final void testVaiSpostamento() {
		vai.setParametro("est");
		vai.esegui(p);
		assertEquals(s2, p.getStanzaCorrente());
	}
	
	@Test
	final void testVaiSpostamentoDirezioneInesistente() {
		
		vai.setParametro("ovest");
		vai.esegui(p);
		assertNotEquals(s2, p.getStanzaCorrente());
	}
	
	
	
	@Test
	final void testPartitaComandoVaiBilocale() {
		comandiDaLeggere.add("vai est");
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		DiaDia gioco = new DiaDia(labirinto, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("aula 2", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Hai vinto!!", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
	}
	
	@Test
	void testPartitaComandoVaiBilocaleDirezioneInesistente() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("fine");
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		DiaDia gioco = new DiaDia(labirinto, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Direzione inesistente", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Grazie di aver giocato !", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
	}
	
	@Test void testPartitaComandoVaiTrilocaleTornaIndietro() {
		comandiDaLeggere.add("vai est");
		comandiDaLeggere.add("vai ovest");
		comandiDaLeggere.add("vai nord");
		
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto2 = new Fixture().labirintoTrilocale();
		DiaDia gioco = new DiaDia(labirinto2, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("campus", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("atrio", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("biblioteca", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Hai vinto!!", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
		
	}
	
	@Test
	void testPartitaComandoVaiTrilocaleVinciSubito() {
		comandiDaLeggere.add("vai nord");
		
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto2 = new Fixture().labirintoTrilocale();
		DiaDia gioco = new DiaDia(labirinto2, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("biblioteca", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Hai vinto!!", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
	}
	
	@Test
	void testPartitaComandoVaiQuattroStanze() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("vai est");
		comandiDaLeggere.add("vai sud");
		
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto2 = new Fixture().labirintoQuattroStanzeCollegate();
		DiaDia gioco = new DiaDia(labirinto2, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N10", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Campus", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N11", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Hai vinto!!", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
		
		
		
		
}
	
	@Test
	void testPartitaComandoVaiQuattroStanzePercorsoVeloce() {
		comandiDaLeggere.add("vai est");
		
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto2 = new Fixture().labirintoQuattroStanzeCollegate();
		DiaDia gioco = new DiaDia(labirinto2, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N11", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Hai vinto!!", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
	}
	
	@Test
	void testPartitaComandoVaiQuattroStanzeTornaIndietroPercorsoVeloce() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("vai sud");
		comandiDaLeggere.add("vai est");
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto2 = new Fixture().labirintoQuattroStanzeCollegate();
		DiaDia gioco = new DiaDia(labirinto2, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N10", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("atrio", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N11", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Hai vinto!!", ioComandi.getNextMessage());
	}
	
	@Test
	void testPartitaComandoVaiStanzaBloccata() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("vai nord");
		
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto2 = new Fixture().labirintoTrilocaleStanzaBloccataConAttrezzo();
		DiaDia gioco = new DiaDia(labirinto2, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N11", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("campus", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Hai vinto!!", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
	}
	
	@Test
	void testPartitaComandoVaiTrilocaleStanzaBloccataSenzaAttrezzo() {
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("fine");
		
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto2 = new Fixture().labirintoTrilocaleStanzaBloccataSenzaAttrezzo();
		DiaDia gioco = new DiaDia(labirinto2, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N11", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N11", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Grazie di aver giocato !", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
	}
	
	
	@Test
	void testPartitaComandoVaiQuattroStanzeSpeciali() {
		//con questo test intendo verificare solamente la possibilità di entrare nelle stanze
		//e l'impossibilità di proseguire nella direzione bloccata
	
		comandiDaLeggere.add("vai ovest");
		comandiDaLeggere.add("vai est");
		comandiDaLeggere.add("vai sud");
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("vai est");
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("vai ovest");
		comandiDaLeggere.add("vai nord");
		comandiDaLeggere.add("fine");
		
		
		IOSimulator ioComandi = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto2 = new Fixture().labirintoQuattroStanzeSpeciali();
		DiaDia gioco = new DiaDia(labirinto2, ioComandi);
		gioco.gioca();
		assertTrue(ioComandi.hasNextMessage());
		assertEquals(gioco.getMessaggioBenvenuto(), ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("magazzino", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("atrio", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("N11", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("atrio", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("laboratorio", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		//stanza bloccata e resto in laboratorio
		assertEquals("laboratorio", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("atrio", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("biblioteca", ioComandi.getNextMessage());
		assertTrue(ioComandi.hasNextMessage());
		assertEquals("Hai vinto!!", ioComandi.getNextMessage());
		assertFalse(ioComandi.hasNextMessage());
		
	}

}
