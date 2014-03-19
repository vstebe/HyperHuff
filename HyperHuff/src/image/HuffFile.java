package image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import HyperHuff.Huffman;
import HyperHuff.Node;
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
		
		String[] codes = new String[img.getSizeData()];
		
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
		
		byte[] bytesImage = new byte[(int)Math.ceil(((double)buffer.length())/8.f)];
		
		StringByte.encodebool(buffer, bytesImage);
		
		byte[][] bytesTab = new byte[table.size()][];
		
		for(int j=0; j<table.size(); j++)
		{
			bytesTab[j] = table.get(j).toBytes();
		}
		
		Saver.save(img.getSizeX(), img.getSizeY(), bytesTab, bytesImage, "/tmp/test.huf");
		
		//for(byte b : bytes)
		//{
	//		System.out.println(b);
	//	}
		

		
	}
	
	 
}
