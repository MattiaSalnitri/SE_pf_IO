package it.polimi.deib.pf.es01.IO;

import java.util.Map;

public interface CSVReader 
{
	/**
	 * Ritorna una riga (mappa tra i nomi del campo nell’intestazione e il valore della riga)
	 * 
	 * @param index il numero della riga da restituire
	 * @return la mappa con le due colonne della riga selezionata
	 */
	public Map<String, String> getRow(int index);
	
	
	/**
	 * Ritorna il numero di righe presenti
	 * 
	 * @return il numero di righe presenti
	 */
	public int size(); 

}
