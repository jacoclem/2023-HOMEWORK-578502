package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuratore {

	private static final String FILE_PROPERTIES = "diadia.properties";
	private static final String pesoMassimo = "pesoMassimo";
	private static final String cfu = "cfu";
	
	private static Properties prop = null;
	
	public static int getCfu() {
		if(prop==null) {
			carica();
		}
		return Integer.parseInt(prop.getProperty(cfu));
	}
	
	public static int getPesoMassimo() {
		if(prop==null) {
			carica();
		}
		return Integer.parseInt(prop.getProperty(pesoMassimo));
	}
	
	private static void carica() {
		prop = new Properties();
		try {
			FileInputStream in = new FileInputStream(FILE_PROPERTIES);
			prop.load(in);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
