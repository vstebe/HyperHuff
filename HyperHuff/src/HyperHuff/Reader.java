package HyperHuff;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	public int getTab()
	{
		int sizeY=0;
		try
		{
			byte[] data = new byte[4];
			cursor.reset();
			cursor.read(data);
			cursor.readInt();
			cursor.readInt();
			cursor.
		} catch (IOException e)
		{
			System.out.println("erreur de lecture");
		}
		return sizeY;
	}
	
}