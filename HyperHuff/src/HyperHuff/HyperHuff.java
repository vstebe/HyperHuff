package HyperHuff;

import java.util.ArrayList;
import java.util.Arrays;

public class HyperHuff {

	public static void main(String[] args) {
		
		Node[] nodes = new Node[] {
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
		
		h.process();

	}

}
