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
	 * @param tab1 tableau contennant les noeuds cod�es
	 * @param tab2 tableau contenant le nouveau code
	 * @param url l'url o� enregistr� le fichier
	 */
	static public void save(int sizeX, int sizeY,byte[][] tab1, byte[] tab2, String url)
	{
		// cr�ation du fichier pour l'�criture
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
			// Cr�ation des instances n�cessaires � l'�criture
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
		    	// deuxi�me tableau ------------------------
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
