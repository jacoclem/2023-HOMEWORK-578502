package it.uniroma3.diadia.comando;

import java.util.Scanner;

import java.lang.reflect.*;

import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi{

	private IO io;

	public FabbricaDiComandiRiflessiva(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione) throws Exception {
		Scanner scannerDiParole = new Scanner(istruzione); // es. ‘vai sud’
		 String nomeComando = null; // es. ‘vai’
		 String parametro = null; // es. ‘sud’
		 Comando comando = null;

		 if (scannerDiParole.hasNext())
		 nomeComando = scannerDiParole.next();//prima parola: nome del comando
		 if (scannerDiParole.hasNext())
		 parametro = scannerDiParole.next();//seconda parola: eventuale parametro
		 try {
			 StringBuilder nomeClasse
				= new StringBuilder("it.uniroma3.diadia.comando.Comando");
				 nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
				 // es. nomeClasse: ‘it.uniroma3.diadia.comando.ComandoV’
				 nomeClasse.append( nomeComando.substring(1) ) ;
				 // es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’
				 comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
				 comando.setParametro(parametro);
			 } catch (Exception e) {
			 comando = new ComandoNonValido();
			 }
		 return comando;

	}




}
