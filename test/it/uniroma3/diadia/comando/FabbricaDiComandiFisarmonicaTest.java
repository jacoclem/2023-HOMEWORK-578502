package it.uniroma3.diadia.comando;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {

	private Comando vai;
	private Comando prendi;
	private Comando posa;
	private Comando aiuto;
	private Comando fine;
	private Comando guarda;
	private Comando nonValido;
	
	@BeforeEach
	public void setUp() {
			vai = new ComandoVai();
			vai.setParametro("nord");
			prendi = new ComandoPrendi();
			prendi.setParametro("osso");
			posa = new ComandoPosa();
			posa.setParametro("osso");
			aiuto = new ComandoAiuto();
			fine = new ComandoFine();
			guarda = new ComandoGuarda();
			nonValido = new ComandoNonValido();
	}
	
	
	@Test
	final void testComandoVai() {
		assertEquals("vai", vai.getNome());
		assertEquals("nord", vai.getParametro());
	}
	
	
	@Test
	final void testComandoPosa() {
		assertEquals("posa", posa.getNome());
		assertEquals("osso", posa.getParametro());
	}
	
	
	@Test
	final void testComandoPrendi() {
		assertEquals("prendi", prendi.getNome());
		assertEquals("osso", posa.getParametro());
	}
	
	
	@Test
	final void testComandoAiuto() {
		assertEquals("aiuto", aiuto.getNome());
	}
	
	
	@Test
	final void testComandoFine() {
		assertEquals("fine", fine.getNome());
	}
	
	
	@Test
	final void testComandoGuarda() {
		assertEquals("guarda", guarda.getNome());
	}
	
	
	@Test
	final void testComandoNonValido() {
		assertEquals("non valido", nonValido.getNome());
	}

}
