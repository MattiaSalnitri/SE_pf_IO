package it.polimi.deib.pf.es01.IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MemoryIO 
{
	ByteArrayOutputStream baus;
	ByteArrayInputStream bais;
	

	public MemoryIO() 
	{
		
		baus = new ByteArrayOutputStream();
		baus.write(3);
		
		bais = new ByteArrayInputStream(baus.toByteArray());
		
		bais.read();
	}
	
	
	public static void main()
	{

	}
	

}
