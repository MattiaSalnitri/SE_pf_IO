package it.polimi.deib.pf.es01.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Mattia Salnitri
 *
 */
public class CSVReader_impl implements CSVReader 
{

	private FileInputStream fis=null;
	private Scanner scanner=null;
	
	/**
	 * constructor of the class
	 */
	public CSVReader_impl() 
	{
		File file = new File("resources/exchange.csv");
		
		try {
			
			fis = new FileInputStream(file);
			//no buffer, do not need with such small files 
			
			//select scanner since i work with tokens 
			scanner = new Scanner(fis);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public Map<String, String> getRow(int index)
	{
		String lineRead = null;
	
		//map da restrituire
		Map<String, String> result = new HashMap<String, String>();
		
		
		//read the lines that are not interested
		for (int i = 0; i<index && scanner.hasNextLine(); i++)
		{
			scanner.nextLine();
		}
		
        if(scanner.hasNextLine())
        {
        	lineRead=scanner.nextLine();
        	String[] parts = lineRead.split(",");
        	result.put(parts[0], parts[1]);        	
        	return result;
        }
        else
        	throw new IllegalArgumentException();

				
	}
	
	
	/**
	 * closes the scanner, and therefore, the stream 
	 */
	public void close() 
	{
		if (fis != null)
		{
			scanner.close();
		}
	}

	@Override
	public int size() 
	{
		int amount =0;
		
		try {
			 amount=fis.available();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return amount;
	}

	public static void main(String[] args) 
	{
		CSVReader_impl reader = new CSVReader_impl();
		
		System.out.println("size of the file: " + reader.size());
		
		System.out.println("Row 3: " + reader.getRow(3).get("CAT") );
		
		reader.close();//remember to close the stream

	}

}
