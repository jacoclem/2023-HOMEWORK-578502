package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	private Borsa borsaVuota;
	private Borsa borsaNonVuota;
	private Attrezzo spada;
	
	@BeforeEach
	public void setUp() {
		this.borsaVuota = new Borsa();
		this.borsaNonVuota = new Borsa();
		this.spada = new Attrezzo("Spada", 5);
		this.borsaNonVuota.addAttrezzo(spada);
		
	}
	


	@Test
	final void testAddAttrezzo() {
		assertTrue(this.borsaNonVuota.addAttrezzo(spada));
		assertSame(spada, this.borsaNonVuota.getAttrezzo("Spada"));
	}

	@Test
	final void testGetPesoMax() {
		assertEquals(10, this.borsaVuota.getPesoMax());
	}

	@Test
	final void testGetAttrezzo() {
		assertSame(spada, this.borsaNonVuota.getAttrezzo("Spada"));
	}

	@Test
	final void testGetPeso() {
		assertEquals(0, this.borsaVuota.getPeso());
		assertEquals(5, this.borsaNonVuota.getPeso());
	}

	@Test
	final void testIsEmpty() {
		assertTrue(this.borsaVuota.isEmpty());
		assertFalse(this.borsaNonVuota.isEmpty());
	}

	@Test
	final void testHasAttrezzo() {
		assertTrue(this.borsaNonVuota.hasAttrezzo("Spada"));
		assertFalse(this.borsaVuota.hasAttrezzo("Spada"));
	}

	@Test
	final void testRemoveAttrezzo() {
		assertEquals(this.spada, this.borsaNonVuota.removeAttrezzo("Spada"));
		assertFalse(this.borsaNonVuota.hasAttrezzo("Spada"));
		assertTrue(this.borsaNonVuota.isEmpty());
	}

	@Test
	final void testToString() {
		String risultato = "Contenuto borsa (5kg/10kg):\nSpada: (5Kg)\n";
		assertEquals(risultato, this.borsaNonVuota.toString());
	}
	
	@Test
	final void testGetSortedOrdinatoPerPesoTreOggettiConPesoDiverso() {
		Attrezzo fionda = new Attrezzo("Fionda", 1);
		Attrezzo ascia = new Attrezzo("Ascia", 2);
		this.borsaNonVuota.addAttrezzo(fionda);
		this.borsaNonVuota.addAttrezzo(ascia);
		SortedSet<Attrezzo> setOrdinato = new TreeSet<>(new ComparatorePesoNome());
		assertTrue(setOrdinato.add(fionda));
		assertTrue(setOrdinato.add(ascia));
		assertTrue(setOrdinato.add(this.spada));
		assertArrayEquals(setOrdinato.toArray(), this.borsaNonVuota.getSortedSetOrdinatoPerPeso().toArray());
	}
	
	@Test void testGetSortedOrdinatoPerPesoTreOggettiDuePesoUguale() {
		Attrezzo ascia = new Attrezzo("Ascia", 2);
		Attrezzo fionda = new Attrezzo("Fionda", 2);
		this.borsaNonVuota.addAttrezzo(fionda);
		this.borsaNonVuota.addAttrezzo(ascia);
		SortedSet<Attrezzo> setOrdinato = new TreeSet<>(new ComparatorePesoNome());
		setOrdinato.add(ascia);
		setOrdinato.add(fionda);
		setOrdinato.add(this.spada);
		assertArrayEquals(setOrdinato.toArray(), this.borsaNonVuota.getSortedSetOrdinatoPerPeso().toArray());
		assertEquals(ascia, this.borsaNonVuota.getAttrezzo("Ascia"));
		assertEquals(fionda, this.borsaNonVuota.getAttrezzo("Fionda"));
	}
	
	@Test void testContenutoOrdinatoPerPesoTreAttrezziPesiDiversi() {
		Attrezzo fionda = new Attrezzo("Fionda", 1);
		Attrezzo ascia = new Attrezzo("Ascia", 2);
		this.borsaNonVuota.addAttrezzo(ascia);
		this.borsaNonVuota.addAttrezzo(fionda);
		List<Attrezzo> listOrdinata = new ArrayList<>();
		listOrdinata.add(fionda);
		listOrdinata.add(ascia);
		listOrdinata.add(spada);
		assertArrayEquals(listOrdinata.toArray(), this.borsaNonVuota.getContenutoOrdinatoPerPeso().toArray());		
	}
	
	@Test void testContenutoOrdinatoPerPesoTreAttrezziPesiUguali() {
		Attrezzo fionda = new Attrezzo("Fionda", 2);
		Attrezzo ascia = new Attrezzo("Ascia", 2);
		this.borsaNonVuota.addAttrezzo(ascia);
		this.borsaNonVuota.addAttrezzo(fionda);
		List<Attrezzo> listOrdinata = new ArrayList<>();
		listOrdinata.add(ascia);
		listOrdinata.add(fionda);
		listOrdinata.add(spada);
		assertArrayEquals(listOrdinata.toArray(), this.borsaNonVuota.getContenutoOrdinatoPerPeso().toArray());
	}
	
	@Test void testGetContenutoOrdinatoPerNomeTreAttrezzi() {
		Attrezzo fionda = new Attrezzo("Fionda", 1);
		Attrezzo ascia = new Attrezzo("Ascia", 2);
		this.borsaNonVuota.addAttrezzo(ascia);
		this.borsaNonVuota.addAttrezzo(fionda);
		List<Attrezzo> listOrdinata = new ArrayList<>();
		listOrdinata.add(ascia);
		listOrdinata.add(fionda);
		listOrdinata.add(spada);
		assertArrayEquals(listOrdinata.toArray(), this.borsaNonVuota.getContenutoOrdinatoPerNome().toArray());
	}
	
	@Test void testGetContenutoOrdinatoPerNomeTreAttrezziConPesoUguale() {
		Attrezzo zattera = new Attrezzo("Zattera", 2);
		Attrezzo ascia = new Attrezzo("Ascia", 2);
		this.borsaNonVuota.addAttrezzo(ascia);
		this.borsaNonVuota.addAttrezzo(zattera);
		List<Attrezzo> listOrdinata = new ArrayList<>();
		listOrdinata.add(ascia);
		listOrdinata.add(spada);
		listOrdinata.add(zattera);
		assertArrayEquals(listOrdinata.toArray(), this.borsaNonVuota.getContenutoOrdinatoPerNome().toArray());
	}
	
	@Test void testGetContenutoRaggruppatoPerPesoTreAttrezziPesiDiversi() {
		Attrezzo fionda = new Attrezzo("Fionda", 1);
		Attrezzo ascia = new Attrezzo("Ascia", 2);
		this.borsaNonVuota.addAttrezzo(ascia);
		this.borsaNonVuota.addAttrezzo(fionda);
		
		Map<Integer, Set<Attrezzo>> mapOrdinata = new HashMap<>();
		Set<Attrezzo> setAttrezzi = new HashSet<>();
		setAttrezzi.add(fionda);
		mapOrdinata.put(fionda.getPeso(), setAttrezzi);
		setAttrezzi = new HashSet<>();
		setAttrezzi.add(ascia);
		mapOrdinata.put(ascia.getPeso(), setAttrezzi);
		setAttrezzi = new HashSet<>();
		setAttrezzi.add(spada);
		mapOrdinata.put(spada.getPeso(), setAttrezzi);
		assertEquals(mapOrdinata.toString(), this.borsaNonVuota.getContenutoRaggruppatoPerPeso().toString());
	}
	
	@Test void testGetContenutoRaggruppatoPerPesoTreAttrezziDuePesiUguali() {
		Attrezzo fionda = new Attrezzo("Fionda", 2);
		Attrezzo ascia = new Attrezzo("Ascia", 2);
		this.borsaNonVuota.addAttrezzo(ascia);
		this.borsaNonVuota.addAttrezzo(fionda);
		
		Map<Integer, Set<Attrezzo>> mapOrdinata = new HashMap<>();
		Set<Attrezzo> setAttrezzi = new HashSet<>();
		setAttrezzi.add(fionda);
		setAttrezzi.add(ascia);
		mapOrdinata.put(2, setAttrezzi);		
		setAttrezzi = new HashSet<>();
		setAttrezzi.add(spada);
		mapOrdinata.put(spada.getPeso(), setAttrezzi);
		assertEquals(mapOrdinata.toString(), this.borsaNonVuota.getContenutoRaggruppatoPerPeso().toString());
	}

}