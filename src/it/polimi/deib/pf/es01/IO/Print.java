package it.polimi.deib.pf.es01.IO;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


//example taken from https://examples.javacodegeeks.com/core-java/io/printwriter/java-printwriter-example/

public class Print 
{
	final static String filename = "resources/printFileExample.txt";
    int i = 5;
	
	/**
	 * the standard output as OutputStreamWriter 
	 */
	private void printStdOut()
	{
        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println("Hello world");
        
        double k = 10.0;
        pw.printf("i = %d and k = %f\n",i,k);
        
        // flush the instance pw
        pw.flush();
	}
	
	/**
	 * write sth in a file (deletes the lines if exist) 
	 */
	private void printFileErease() 
	{
        PrintWriter pwFile = null;
        Date date = new Date();
        
        try{
            pwFile = new PrintWriter(filename);
            i++;
            // write a builtIn object
            pwFile.println(date);
            pwFile.write("Write something in a line. i = "+i);
            System.out.println("Write to the file successfully");
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(SecurityException e) {
            e.printStackTrace();
        }finally {
            // always close the output stream
            if(pwFile != null){
                pwFile.close();
            }
        }
	}
	
	
 
	/**
	 *  writes in a file in a newline (no deletion of previous writing)
	 * fileWriter vd PrintWriter https://stackoverflow.com/questions/5759925/printwriter-vs-filewriter-in-java
	 * why used a printWriter and not wrote directly in the buffer: https://beginnersbook.com/2014/01/how-to-append-to-a-file-in-java/ 
	 */
	public void printNewLine() 
	{
        PrintWriter pwFile1 = null;
        //creates a string, but saved in an object tyo memorise it later
        //System.getProperty("line.separator") carattere di "a capo"
        Object obj = System.getProperty("line.separator")+"A new object";
        
        
        try {
            FileWriter fl = new FileWriter(filename, true);
            BufferedWriter br = new BufferedWriter(fl);//si mette da buffer, quindi noi scriviamo direttamente nel buffer --> performace migliori
            pwFile1 = new PrintWriter(br) ;//uso printWriter per l'aiuto con i tipi che mi da 
             
            pwFile1.println(obj);
            // write the string beginning from the 5th char until the 9th (\n to be counted)
            pwFile1.write("prova!!! Test!!!\n",5 ,12); //write(char[] buf, int off, int len)
            
            pwFile1.println("This is first line");
            pwFile1.println("This is second line");
            pwFile1.println("This is third line");
            
            
            System.out.println("Add new lines to the file successfully");
            
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // for FileWriter
            e.printStackTrace();
        }finally {
           // no matter what happen, close the output stream
            if(pwFile1 != null){
                pwFile1.close();
           }
        }
	}
	
	
	public static void main(String[] args) 
	{
		Print print = new Print();
		
		print.printStdOut();
		print.printFileErease();
		print.printNewLine();
		

	}

}
