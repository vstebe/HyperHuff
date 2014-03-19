package HyperHuff;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Reader
{
	private FileInputStream input;
	private DataInputStream cursor;
	private int sizeX, sizeY;
	private byte[] sequence;
	private byte[][] arbre;
	
	public int getSizeX()
	{
		return sizeX;
	}
	public int getSizeY()
	{
		return sizeY;
	}
	public byte[] getSequence()
	{
		return sequence;
	}
	public byte[][] getArbre()
	{
		return arbre;
	}
	public Reader(String url)
	{
		try
		{
			input = new FileInputStream(url);
			
			cursor = new DataInputStream(input);
		/*************************************************************/
			
			try
			{
				String prefixe = ""+ cursor.readChar();
				prefixe += cursor.readChar();
				
				this.sizeX = cursor.readInt();
				
				this.sizeY = cursor.readInt();
				int sizetab = cursor.readInt();
				System.out.println(prefixe + " " + sizeX + " " + sizeY + " " + sizetab);
				byte[] data = new byte[1];
				byte[] pixel = new byte[1];
				byte[] bytecourant = new byte[1];
				byte[] resligne = null;
				byte[][] res = new byte[sizetab][];
				int longueurligne;
				// lecture arbre
				for(int i=0;i<sizetab;i++)
				{
					cursor.read(pixel);
					cursor.read(data);
					longueurligne =convertByteToInt(data)/8+3;
					System.out.println(longueurligne);
					resligne = new byte[longueurligne];
					resligne[0] = pixel[0];
					resligne[1] = data[0];
					for(int j=2;j<longueurligne;j++)
					{
						cursor.read(bytecourant);
						resligne[j] = bytecourant[0];
					}
					res[i] = resligne;
					
				}
				this.arbre = res;
				// lecture sequence
				int longueursequence=0;
				while(cursor.read(data) != -1)
				{
					longueursequence++;
				}
				byte[] res2 = new byte[longueursequence];
				cursor.read(res2);
				this.sequence=res2;
				
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e)
		{
			System.out.println("le fichier n'a pas pu être ouvert");
			
		}
		
		
		
	}
	public int convertByteToInt(byte[] b)
	{           
	    int value= 0;
	    for(int i=0; i<b.length; i++)
	       value = (value << 8) | b[i];     
	    return value;       
	}
}
		

