package HyperHuff;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Reader
{
	private FileInputStream input;
	DataInputStream cursor;
	String url;
	
	public Reader(String url)
	{
		try
		{
			this.url=url;
			input = new FileInputStream(url);
			
			cursor = new DataInputStream(input);

		} catch (FileNotFoundException e)
		{
			System.out.println("le fichier n'a pas pu être ouvert");
			
		}
		
	}
	
	public int getSizeX()
	{
		int sizeX=0;
		try
		{
			byte[] data = new byte[4];
			input = new FileInputStream(url);
			cursor = new DataInputStream(input);
			cursor.read(data);
			sizeX = cursor.readInt();
		} catch (IOException e)
		{
			System.out.println("erreur de lecture");
		}
		return sizeX;
	}
	public int getSizeY()
	{
		int sizeY=0;
		try
		{
			byte[] data = new byte[4];
			input = new FileInputStream(url);
			cursor = new DataInputStream(input);
			cursor.read(data);
			sizeY = cursor.readInt();
		} catch (IOException e)
		{
			System.out.println("erreur de lecture");
		}
		return sizeY;
	}
	public byte[][] getArbre()
	{
		ArrayList<byte[]> res = new ArrayList<byte[]>();
		int sizeY=0;
		try
		{
			input = new FileInputStream(url);
			cursor = new DataInputStream(input);
			cursor.skipBytes(16);
			int sizetab1 = cursor.readInt();
			int longueur;
			byte[] data;
			int pos=16;
			byte[] buffer;
			for(int i=0;i<sizetab1;i++)
			{
				input = new FileInputStream(url);
				cursor = new DataInputStream(input);
				cursor.skipBytes(pos);
				ArrayList<Byte> resint = new ArrayList<Byte>();
				data = new byte[1];
				cursor.read(data);
				cursor.read(data);
				longueur = Integer.valueOf(new String(data))/8+3;
				buffer = new byte[longueur];
				input = new FileInputStream(url);
				cursor = new DataInputStream(input);
				cursor.skipBytes(pos);
				cursor.read(buffer);
				res.add(buffer);
				pos += longueur;
			}
			
			
		} catch (IOException e)
		{
			System.out.println("erreur de lecture");
		}
		byte[][] resfinal = new byte[res.size()][];
		 res.toArray(resfinal);
		return resfinal;
	}
	public byte[] getSequence()
	{
		byte[] resfinal = null;
		try
		{
			input = new FileInputStream(url);
			cursor = new DataInputStream(input);
			cursor.skipBytes(16);
			int sizetab1 = cursor.readInt();
			int longueur,longueur2 = 0;
			byte[] data;
			int pos=16;
			
			for(int i=0;i<sizetab1;i++)
			{
				input = new FileInputStream(url);
				cursor = new DataInputStream(input);
				cursor.skipBytes(pos);
				data = new byte[1];
				cursor.read(data);
				cursor.read(data);
				longueur = Integer.valueOf(new String(data))/8+3;
				input = new FileInputStream(url);
				cursor = new DataInputStream(input);
				cursor.skipBytes(pos);
				pos += longueur;
			}
			data = new byte[1];
			
			while(cursor.read(data) != -1)
			{
				longueur2++;
				
			}
			resfinal = new byte[longueur2];
			cursor.read(resfinal);
			
		} catch (IOException e)
		{
			System.out.println("erreur de lecture");
		}
		return resfinal;
	}
	
}
