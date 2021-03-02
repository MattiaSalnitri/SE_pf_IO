package it.polimi.deib.pf.es01.IO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Mattia Salnitri
 * wrong implementation using buffered IO
 *
 */
public class CSVReader_impl2 implements CSVReader {

	private FileInputStream fis = null;
	private Scanner scanner = null;
	BufferedInputStream bis = null;
	
	/**
	 * constructor of teh class, it uses a buffer
	 */
	public CSVReader_impl2() 
	{
		File file = new File("resources/exchange.csv");
		
		try {
			
			fis = new FileInputStream(file);
			
			//buffer, to skip later on 
			bis = new BufferedInputStream(fis);
			
			//select scanner since i work with tokens 
			scanner = new Scanner(bis);
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
	
		//map to return
		Map<String, String> result = new HashMap<String, String>();	
		
		//i must know how many byte to skip ( which i don't) since i don't know when the line finishes
		//possible problem here
		try {
			bis.skip(index);
		} catch (IOException e) {
			e.printStackTrace();
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
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return amount;
	}

	public static void main(String[] args) 
	{
		CSVReader_impl2 reader = new CSVReader_impl2();
		
		System.out.println("size of the file: " + reader.size());
		
		System.out.println("Row 3: " + reader.getRow(3).get("CAT") );

	}

}
