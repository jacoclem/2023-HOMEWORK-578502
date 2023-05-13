package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private int peso;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;	
		this.attrezzi = new TreeMap<>(); // speriamo bastino...
		this.numeroAttrezzi = 0;
		this.peso=0;
	}


	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		this.numeroAttrezzi ++;
		this.peso += attrezzo.getPeso();
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(this.hasAttrezzo(nomeAttrezzo))
			return this.attrezzi.get(nomeAttrezzo);
		else
			return null;
	}


	public int getPeso() {
		return this.peso;
	}
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		
		if(this.hasAttrezzo(nomeAttrezzo))
		{
			Attrezzo attrezzoDaRimuovere = this.getAttrezzo(nomeAttrezzo);
			if(this.attrezzi.remove(nomeAttrezzo, attrezzoDaRimuovere))
			{
				this.numeroAttrezzi--;
				return attrezzoDaRimuovere;
			}
			else
				return null;
			
		}
		else
			return null;
	}
	
	public Set<String> nomiAttrezzi(){
		return this.attrezzi.keySet();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		final List <Attrezzo> risultato = new ArrayList<Attrezzo>(this.attrezzi.values());
		Collections.sort(risultato, new ComparatorePesoNome());
		
		return risultato;
	}
	
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		final SortedSet<Attrezzo> risultato = new TreeSet<Attrezzo>(new ComparatoreNome());
		risultato.addAll(this.attrezzi.values());
		return risultato;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		final Map<Integer, Set<Attrezzo>> peso2Attrezzi = new HashMap<>();
		for(Attrezzo attrezzo : this.attrezzi.values()) {
			Set<Attrezzo> delloStessoPeso = peso2Attrezzi.get(attrezzo.getPeso());
			if(delloStessoPeso==null) {
				//Attrezzo di peso già visto prima
				delloStessoPeso = new HashSet<>();				
			}
			delloStessoPeso.add(attrezzo);
			peso2Attrezzi.put(attrezzo.getPeso(), delloStessoPeso);
					
		}
		return peso2Attrezzi;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> setOrdinato = new TreeSet<>(new ComparatorePesoNome());
		setOrdinato.addAll(this.attrezzi.values());
		return setOrdinato;
	}
	
	public int getSizeAttrezzi() {
		return this.attrezzi.size();
	}
	
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg):\n");
			Set <String> nomi = this.nomiAttrezzi();
			for(String str : nomi) {
				s.append(str);
				s.append(": (");
				s.append(this.getAttrezzo(str).getPeso());
				s.append("Kg)\n");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}