package HyperHuff;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Saver
{

	static public void save(int sizeX, int sizeY,byte[][] tab1, byte[] tab2, String url)
	{
	
		File output = new File(url);
		try
		{
			output.createNewFile();
		} catch (IOException e)
		{
			
			System.out.println("Impossible de créer le fichier " + output.getAbsolutePath().toString());
		}
		 
		try
		{
		    FileOutputStream outputstream = new FileOutputStream(output);
		    DataOutputStream cursor = new DataOutputStream(outputstream);
		    try
		    {
		    	cursor.writeUTF("hf");
		    	cursor.writeInt(sizeX);
		    	cursor.writeInt(sizeY);
		    	cursor.writeInt(tab1.length);
		    	for(int i =0;i<tab1.length;i++)
		    	{
		    		cursor.write(tab1[i],0,tab1[i].length);
		    	}
		    	cursor.write(tab2, 0,tab2.length);
		        cursor.close();
		    }
		    catch (IOException exception)
		    {
		        System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		    }
		}
		catch (FileNotFoundException exception)
		{
		    System.out.println ("Le fichier n'a pas été trouvé");
		}
	}
}
