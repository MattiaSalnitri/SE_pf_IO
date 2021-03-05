package it.polimi.deib.pf.es01.readElf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

//https://unix.stackexchange.com/questions/153352/what-is-elf-magic

/**
 * class that reads the magic number of unix based files
 */
public class ElfReader {
	
	//elf number for executable file ( on mac)
	int[] elf = {207,
			250,
			237,
			254,
			7,
			0,
			0,
			1,
			3,
			0,
			0,
			128,
			2,
			0,
			0,
			0
};

	
	/**
	 * A method that checks the ln file 
	 * 
	 * @return true in the first 16 bytes are equal to elf variable, i.e., true if the file in executable. false othewise.
	 */
	public boolean checkElf()
	{
		File file = new File("/bin/ln");
		
		try {
			
			ByteArrayInputStream bais = new ByteArrayInputStream(Files.readAllBytes(file.toPath()), 0, 16);
			
			for (int i = 0; i<16; i++)
				if (bais.read()!=elf[i])
					return false;		
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	public static void main(String[] args) 
	{
		ElfReader elfReader = new ElfReader();
		if (elfReader.checkElf())
			System.out.println("Executable file");
		else
			System.out.println("Not executable file");
			
		
		
	}

}
