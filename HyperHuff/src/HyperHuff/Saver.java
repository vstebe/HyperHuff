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
	/**
	 * Enregistre byte par byte le fichier .huff
	 * @param sizeX longueur de l'image
	 * @param sizeY hauteur de l'image
	 * @param tab1 tableau contennant les noeuds codées
	 * @param tab2 tableau contenant le nouveau code
	 * @param url l'url où enregistré le fichier
	 */
	static public void save(int sizeX, int sizeY,byte[][] tab1, byte[] tab2, String url)
	{
		// création du fichier pour l'écriture
		File output = new File(url);
		try
		{
			output.createNewFile();
		} catch (IOException e)
		{
			
			System.out.println("Impossible de crÃ©er le fichier " + output.getAbsolutePath().toString());
		}
		 
		try
		{
			// Création des instances nécessaires à l'écriture
		    FileOutputStream outputstream = new FileOutputStream(output);
		    DataOutputStream cursor = new DataOutputStream(outputstream);
		    try
		    {
		    	// header ----------------------------------
		    	cursor.writeChars("hf"); // signature
		    	cursor.writeInt(sizeX);
		    	cursor.writeInt(sizeY);
		    	cursor.writeInt(tab1.length);
		    	// premier tableau -------------------------
		    	for(int i =0;i<tab1.length;i++)
		    	{
		    		cursor.write(tab1[i],0,tab1[i].length);
		    	}
		    	// deuxième tableau ------------------------
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
		    System.out.println ("Le fichier n'a pas Ã©tÃ© trouvÃ©");
		}
	}
}
