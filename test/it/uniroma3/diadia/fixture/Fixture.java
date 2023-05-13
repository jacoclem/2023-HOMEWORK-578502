package it.uniroma3.diadia.fixture;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

public class Fixture {


	public Fixture() {}

	/**
	 * Funzione che crea un labirinto con due stanze
	 * @return labirinto
	 */
	public Labirinto labirintoBilocale() {

		Labirinto labirinto  = new LabirintoBuilder()
				.addStanzaIniziale("aula 1")
				.addAttrezzo("chiave", 2)
				.addStanzaVincente("aula 2")
				.addAdiacenza("aula 1", "aula 2", "est")
				.getLabirinto();

		return labirinto;

	}




	/**
	 * Funzione che crea un labirinto con tre stanze
	 * @return labirinto
	 */
	public Labirinto labirintoTrilocale() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addAttrezzo("chiave", 2)
				.addStanza("campus")
				.addAttrezzo("computer", 5)
				.addAdiacenza("atrio", "campus", "est")
				.addStanzaVincente("biblioteca")
				.addAdiacenza("atrio", "biblioteca", "nord")
				.getLabirinto();
		return labirinto;
	}

	public Labirinto labirintoTrilocaleStanzaBloccataConAttrezzo() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaBloccata("N11", "nord", "chiave")
				.addAttrezzo("chiave", 2)
				.addAdiacenza("atrio", "N11", "nord")
				.addStanzaVincente("campus")
				.addAdiacenza("N11", "campus", "nord")
				.getLabirinto();

		return labirinto;
	}

	public Labirinto labirintoTrilocaleStanzaBloccataSenzaAttrezzo() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanzaBloccata("N11", "nord", "chiave")
				.addAdiacenza("atrio", "N11", "nord")
				.addStanzaVincente("campus")
				.addAdiacenza("N11", "campus", "nord")
				.getLabirinto();

		return labirinto;
	}

	/**
	 * Funzione che crea un labirinto con quattro
	 * @return labirinto
	 */
	public Labirinto labirintoQuattroStanzeCollegate() {
		Labirinto labirinto = new LabirintoBuilder()
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
				.getLabirinto();		
		return labirinto;
	}

	/**
	 * Funzione che crea un labirinto con quattro stanze speciali
	 * @return labirinto
	 */
	public Labirinto labirintoQuattroStanzeSpeciali() {
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
	}

}
