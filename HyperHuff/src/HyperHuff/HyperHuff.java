package HyperHuff;

import image.GreyImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class HyperHuff {

	public static void main(String[] args) {
		
		/*Node[] nodes = new Node[] {
				new Node((short)0, 1), //a
				new Node((short)1, 1), //c
				new Node((short)2, 1),//s
				new Node((short)3, 1),//u
				new Node((short)4, 1),//m
				new Node((short)5, 2),//o
				new Node((short)6, 2),//l
				new Node((short)7, 3),//i
				new Node((short)8, 3),//e
				new Node((short)9, 5),//n
				new Node((short)10, 5),//t
		};
		
		Huffman h = new Huffman(new ArrayList<Node>(Arrays.asList(nodes)));
		
		h.process();*/
		
		
		GreyImage img;
		try {
			img = GreyImage.loadPGM("/home/vincent/barbara.pgm");
			
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
			h.process();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}
