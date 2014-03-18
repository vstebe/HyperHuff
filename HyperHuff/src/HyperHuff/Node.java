package HyperHuff;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Node implements Comparable {
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
	
	public short getValue()
	{
		return value;
	}
	
	boolean[] concat(boolean[] A, boolean[] B) {
		   int aLen = A.length;
		   int bLen = B.length;
		   boolean[] C= new boolean[aLen+bLen];
		   System.arraycopy(A, 0, C, 0, aLen);
		   System.arraycopy(B, 0, C, aLen, bLen);
		   return C;
		}
	
	public ArrayList<TableEntry> getTable()
	{
		ArrayList<TableEntry> res = new ArrayList<TableEntry>();
		
		if(left.isLeaf())
		{
			res.add(new TableEntry(left.getValue(), "0"));
		} else {
			ArrayList<TableEntry> tmp = left.getTable();
			for(TableEntry entry : tmp)
			{
				res.add(new TableEntry(entry.value, "0"+entry.code));
			}
		}
		if(right.isLeaf())
		{
			res.add(new TableEntry(right.getValue(), "1"));
		} else {
			ArrayList<TableEntry> tmp = right.getTable();
			for(TableEntry entry : tmp)
			{
				res.add(new TableEntry(entry.value, "1"+entry.code));
			}
		}
		
		
		return res;
	}

	@Override
	public int compareTo(Object arg0) {
		return this.count() - ((Node) arg0).count();
	}
}
