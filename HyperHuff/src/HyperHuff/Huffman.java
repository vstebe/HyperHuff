package HyperHuff;

import java.util.ArrayList;
import java.util.Collections;

public class Huffman {
	ArrayList<Node> nodes = new ArrayList<Node>();
	
	public Huffman(ArrayList<Node> nodes)
	{
		this.nodes = nodes;
	}
	
	public ArrayList<TableEntry> process()
	{
		while(nodes.size() != 1)
		{
			Collections.sort(nodes);
			
			Node n1 = nodes.get(0);
			Node n2 = nodes.get(1);
			
			nodes.remove(0);
			nodes.remove(0);
			
			nodes.add(0, new Node(n1, n2));
		}
		
		ArrayList<TableEntry> list = nodes.get(0).getTable();
		
		return list;
	}
	
}
