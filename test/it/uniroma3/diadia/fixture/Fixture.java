package it.uniroma3.diadia.fixture;

import java.io.FileNotFoundException;
import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.FormatoFileNonCorrettoException;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;


public class Fixture {


	public Fixture() {}

	/**
	 * Funzione che crea un labirinto con due stanze
	 * @return labirinto
	 * @throws FormatoFileNonCorrettoException 
	 * @throws FileNotFoundException 
	 */
	public Labirinto labirintoBilocale() throws FileNotFoundException, FormatoFileNonCorrettoException {

		/*Labirinto labirinto  = new LabirintoBuilder()
				.addStanzaIniziale("aula 1")
				.addAttrezzo("chiave", 2)
				.addStanzaVincente("aula 2")
				.addAdiacenza("aula 1", "aula 2", "est")
				.getLabirinto();*/
		Labirinto labirinto = Labirinto.newBuilder("LabirintoFixtureBilocale.txt").getLabirinto();
		return labirinto;

	}




	/**
	 * Funzione che crea un labirinto con tre stanze
	 * @return labirinto
	 * @throws FormatoFileNonCorrettoException 
	 * @throws FileNotFoundException 
	 */

	/*public Labirinto labirintoTrilocaleStanzaBloccataConAttrezzo() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaBloccata("N11", "nord", "chiave")
				.addAttrezzo("chiave", 2)
				.addAdiacenza("atrio", "N11", "nord")
				.addStanzaVincente("campus")
				.addAdiacenza("N11", "campus", "nord")
				.getLabirinto();

		return labirinto;
	}*/

	public Labirinto labirintoTrilocale() throws FileNotFoundException, FormatoFileNonCorrettoException {
		/*Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("chiave", 2)
				.addStanza("campus")
				.addAttrezzo("computer", 5)
				.addAdiacenza("atrio", "campus", "est")
				.addStanzaVincente("biblioteca")
				.addAdiacenza("atrio", "biblioteca", "nord")
				.getLabirinto();
		return labirinto;*/
		
		Labirinto labirinto = Labirinto.newBuilder("LabirintoFixtureTrilocale.txt").getLabirinto();
		return labirinto;
	}

	public Labirinto labirintoComplesso() throws FileNotFoundException, FormatoFileNonCorrettoException{
		Labirinto labirinto = Labirinto.newBuilder("LabirintoFixtureComplesso.txt").getLabirinto();
		return labirinto;
	}
	
	/*public Labirinto labirintoTrilocaleStanzaBloccataSenzaAttrezzo() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaBloccata("N11", "nord", "chiave")
				.addAdiacenza("atrio", "N11", "nord")
				.addStanzaVincente("campus")
				.addAdiacenza("N11", "campus", "nord")
				.getLabirinto();

		return labirinto;
	}*/

	/**
	 * Funzione che crea un labirinto con quattro
	 * @return labirinto
	 * @throws FormatoFileNonCorrettoException 
	 * @throws FileNotFoundException 
	 */
	public Labirinto labirintoQuattroStanzeCollegate() throws FileNotFoundException, FormatoFileNonCorrettoException {
		/*Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanza("N10")
				.addAttrezzo("quaderno", 1)
				.addAdiacenza("atrio", "N10", "nord")
				.addStanza("Campus")
				.addAttrezzo("computer", 5)
				.addAdiacenza("N10", "Campus", "est")
				.addStanzaVincente("N11")
				.addAdiacenza("Campus", "N11", "sud")
				.addAdiacenza("N11", "atrio", "ovest")
				.getLabirinto();	*/	
		
		Labirinto labirinto = Labirinto.newBuilder("LabirintoFixtureQuattroStanze.txt").getLabirinto();
		
		return labirinto;
		
		
	}

	/**
	 * Funzione che crea un labirinto con quattro stanze speciali
	 * @return labirinto
	 * @throws Exception 
	 */
	/*public Labirinto labirintoQuattroStanzeSpeciali() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaBuia("magazzino", "lanterna")
				.addAdiacenza("atrio", "magazzino", "ovest")
				.addStanzaBloccata("laboratorio", "nord", "chiave")
				.addAdiacenza("atrio", "laboratorio", "est")
				.addStanzaMagica("N11")
				.addAdiacenza("atrio", "N11", "sud")
				.addStanzaVincente("biblioteca")
				.addAdiacenza("atrio", "biblioteca", "nord")
				.addStanza("bagno")
				.addAdiacenza("laboratorio", "bagno", "nord")
				.getLabirinto();

		return labirinto;
	}*/
	
	


}
