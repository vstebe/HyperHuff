package HyperHuff;

import java.util.ArrayList;

public class Node {
	private Node left = null;
	private Node right = null;
	
	private short value;
	
	private boolean isLeaf;
	
	private int frequency = 0;
	
	public Node(Node left, Node right)
	{
		this.left = left;
		this.right = right;
		this.isLeaf = false;
	}
	
	public Node(short value, int frequency )
	{
		this.value = value;
		this.isLeaf = true;
		this.frequency = frequency;
	}
	
	public int count()
	{
		int res = 0;
		if(left != null)
			res += left.count();
		if(right != null)
			res += right.count();
		
		res += frequency;
		
		return res;
	}
	
	public boolean isLeaf()
	{
		return isLeaf;
	}
	
	/*public ArrayList<Short, Boolean[]> getTable()
	{
		ArrayList<Short, Boolean[]> res = new ArrayList<Short, Boolean[]>();
		
		if(left.isLeaf())
		{
			
		}
		
		return res;
	}*/
}
