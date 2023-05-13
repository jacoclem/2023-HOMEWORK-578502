package it.uniroma3.diadia.comando;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;


public interface Comando{
	/**
	 * esecuzione del comando
	 * 
	 */
	public void esegui(Partita partita);
	
	
	/**
	 * set del parametro
	 */
	public void setParametro(String parametro);
	
	/**
	 * get del parametro
	 * @return
	 */
	public String getParametro();
	
	/**
	 * set dell'input/output
	 */
	public void setIO(IO io);
	
	/**
	 * get del nome
	 */
	public String getNome();
}


