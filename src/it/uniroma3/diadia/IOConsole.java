package it.uniroma3.diadia;

import java.util.Scanner;



public class IOConsole implements IO {
	
	Scanner scannerDiLinee;
	
	public IOConsole(Scanner scanner) {
		this.scannerDiLinee=scanner;
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		//**QUESTE LINE DI CODICE SONO SPOSTATE NEL MAIN
		//Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();
		return riga;
	}
}