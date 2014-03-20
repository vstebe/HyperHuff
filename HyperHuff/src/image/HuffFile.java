package image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import HyperHuff.Huffman;
import HyperHuff.Node;
import HyperHuff.Reader;
import HyperHuff.Saver;
import HyperHuff.StringByte;
import HyperHuff.TableEntry;

public class HuffFile {
	public void save(GreyImage img)
	{
		HashMap<Short, Integer> caracters = new HashMap<Short, Integer>();
		for(int i=0; i<img.getSizeData(); i++)
		{
			Short s = img.getPixel(i);
			if(caracters.containsKey(s))
				caracters.put(s, caracters.get(s)+1);
			else
				caracters.put(s, 0);
		}
		
		Node[] nodes = new Node[caracters.size()];
		int i=0;
		for(Entry<Short, Integer> entry : caracters.entrySet())
		{
			nodes[i] = new Node(entry.getKey(), entry.getValue());
			i++;
		}
		
		
		
		Huffman h = new Huffman(new ArrayList<Node>(Arrays.asList(nodes)));
		
		ArrayList<TableEntry> table = h.process();
		
		
		StringBuffer buffer = new StringBuffer();
		
		for(int j=0; j<img.getSizeData(); j++)
		{
			short value = img.getPixel(j);
			String code = "";
			for(TableEntry entry : table)
			{
				if(entry.value == value)
				{
					code = entry.code;
					break;
				}
			}
			//codes[j] = code;
			//System.out.println(code);
			
			buffer.append(code);
		}
		
		
		System.out.println("debut buffer : ");
		System.out.println(buffer.substring(0, 50));
		
		byte[] bytesImage = new byte[(int)Math.ceil(((double)buffer.length())/8.f)];
		
		StringByte.encodebool(buffer, bytesImage);
		
		byte[][] bytesTab = new byte[table.size()][];
		
		for(int j=0; j<table.size(); j++)
		{
			bytesTab[j] = table.get(j).toBytes();
		}
		
		System.out.println("buffer length : " + buffer.length());
		
		Saver.save(img.getSizeX(), img.getSizeY(), bytesTab, bytesImage, "/tmp/test.huf");
		
		//for(byte b : bytes)
		//{
	//		System.out.println(b);
	//	}
		
		//openBytes(img.getSizeX(), img.getSizeY(), bytesImage, table);
		
	}
	
	public void openHuf(String str)
	{
		Reader r = new Reader(str);
		byte[][] tableBytes = r.getArbre();
		ArrayList<TableEntry> table = new ArrayList<TableEntry>();
		System.out.println("a");
		for(int i=0; i<tableBytes.length; i++)
		{
			table.add(TableEntry.fromBytes(tableBytes[i]));
		}
		
		System.out.println("nbEntries : " + table.size());
		
		openBytes(r.getSizeX(), r.getSizeY(), r.getSequence(), table);
		System.out.println("b");
	}
	
	
	public void openBytes(int dimX, int dimY, byte[] bytes, ArrayList<TableEntry> table)
	{
		String img = StringByte.toString(bytes).toString();
		
		System.out.println("debut buffer : ");
		System.out.println(img.substring(0, 50));
		
		System.out.println("taile : " + img.length());
		
		short[] values = new short[dimX*dimY];
		//Collections.sort(table);
		int index = 0;
		for(int i=0; (i<dimX*dimY) && index < img.length(); i++)
		{
			//String str = img.toString();
			short value = 0;
			String code = "";
			for(TableEntry entry : table)
			{
				if((index + entry.code.length()) < img.length() &&  img.subSequence(index, index + entry.code.length()).equals(entry.code))
				{
					value = entry.value;
					code = entry.code;
					//System.out.println("found");
					break;
				}
					
			}
			
			//f(i%1000 == 0)
			//	System.out.println(i);
			
			//img.delete(0, code.length());
			index+=code.length();
			values[i] = value;
			
			///if(code.length() == 0)
			//	System.out.println("not foudn !!");
			
			//System.out.println(value + " " + code);
			
		}
		

		
		GreyImage img2 = new GreyImage(dimX, dimY, values);
		try {
			img2.save("/tmp/pgm.pgm");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 
}
