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
		//L'algo s'arrete quand il n'y a plus qu'un seul node (un seul arbre connexe)
		while(nodes.size() != 1)
		{
			Collections.sort(nodes);
			
			//On prend les deux premiers nodes
			Node n1 = nodes.get(0);
			Node n2 = nodes.get(1);
			
			
			//On les enlève de la liste
			nodes.remove(0);
			nodes.remove(0);
			
			//On créé le node "fusion" des deux
			nodes.add(0, new Node(n1, n2));
		}
		
		//La table correspond à celle du noeud racine
		ArrayList<TableEntry> list = nodes.get(0).getTable();
		
		return list;
	}
	
}
