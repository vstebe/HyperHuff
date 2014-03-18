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
	
	public Reader(String url)
	{
		try
		{
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
			cursor.reset();
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
			cursor.reset();
			cursor.read(data);
			cursor.readInt();
			sizeY = cursor.readInt();
		} catch (IOException e)
		{
			System.out.println("erreur de lecture");
		}
		return sizeY;
	}
	public int getTab1()
	{
		ArrayList<byte[]> res = new ArrayList<byte[]>();
		int sizeY=0;
		try
		{
			cursor.reset();
			cursor.skipBytes(16);
			int sizetab1 = cursor.readInt();
			int longueur;
			byte[] data;
			int pos=16;
			for(int i=0;i<sizetab1;i++)
			{
				cursor.reset();
				cursor.skipBytes(pos);
				ArrayList<Byte> resint = new ArrayList<Byte>();
				data = new byte[4];
				cursor.read(data);
				cursor.read(data);
				longueur = Integer.valueOf(new String(data));
				byte[] buffer = new byte[longueur];
				cursor.reset();
				cursor.skipBytes(pos);
				cursor.read(buffer);
				res.add(buffer);
				pos += longueur;
			}
			
			
		} catch (IOException e)
		{
			System.out.println("erreur de lecture");
		}
		return sizeY;
	}
	
}
