package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comando.Comando;
import it.uniroma3.diadia.comando.FabbricaDiComandi;
//import it.uniroma3.diadia.comando.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comando.FabbricaDiComandiRiflessiva;

//import org.junit.jupiter.api.Test;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	

	private Partita partita;
	private IO io;
	
	public DiaDia(Labirinto labirinto, IO io) {
		this.io = io;
		this.partita = new Partita(labirinto);
	}
	
	public DiaDia(IO io, Labirinto labirinto) {
		this.io = io;
		this.partita = new Partita(labirinto);
	}
	
	public DiaDia(IO io) {
		this.io = io;
	}

	public void gioca() throws Exception {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
				
		do		
		{
			istruzione = io.leggiRiga();
		}
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(this.io);
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		try {
			comandoDaEseguire = factory.costruisciComando(istruzione);
		} catch (ClassNotFoundException cne) {
			comandoDaEseguire = factory.costruisciComando("NonValido");
		} catch (NullPointerException npe) {
			comandoDaEseguire = factory.costruisciComando("NonValido");
		}
		comandoDaEseguire.setIO(io);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
			
		
		return this.partita.isFinita();
	}



	public static void main(String[] argc) throws Exception {
		/* N.B. unica istanza di IOConsole
		 di cui sia ammessa la creazione */
		Scanner scanner = new Scanner(System.in);
		 IO io = new IOConsole(scanner);
		 
		 Labirinto labirinto = Labirinto.newBuilder("LabirintoFixtureComplesso.txt").getLabirinto();
		 
		 
		 /*Labirinto labirinto = new LabirintoBuilder()
				 .addStanzaIniziale("atrio")
				 .addStanzaBuia("N11", "lanterna")
				 .addAttrezzo("osso", 2)
				 .addAdiacenza("atrio", "N11", "ovest")
				 .addStanza("campusOne")
				 .addMago("Merlino", "e sono un mago", new Attrezzo("lanterna", 5))
				 .addAdiacenza("atrio", "campusOne", "nord")
				 .addStanza("bar")
				 .addStrega("Ursula", "e sono una strega malvagia")
				 .addAdiacenza("atrio", "bar", "est")
				 .addStanza("giardino")
				 .addCane("Rex", "Woof! Woof!", "osso", new Attrezzo("chiave", 3))
				 .addAdiacenza("bar", "giardino", "est")
				 .addStanzaBloccata("N10", "ovest", "chiave")
				 .addAdiacenza("atrio", "N10", "sud")
				 .addStanzaVincente("biblioteca")
				 .addAdiacenza("N10", "biblioteca", "ovest")
				 .getLabirinto();*/
				 
				 
				 
				 
		 DiaDia gioco = new DiaDia(labirinto, io);
		 gioco.gioca();
		 scanner.close();
;
	}
	
	public String getMessaggioBenvenuto() {
		return this.MESSAGGIO_BENVENUTO;
	}
}