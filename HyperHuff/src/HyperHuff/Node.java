package HyperHuff;

import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private Node left = null;
	private Node right = null;
	
	private short value;
	
	private boolean isLeaf;
	
	private int frequency = 0;
	
	/**
	 * Création d'un node parent en donnant ses enfants
	 * @param left
	 * @param right
	 */
	public Node(Node left, Node right)
	{
		this.left = left;
		this.right = right;
		this.isLeaf = false;
	}
	
	/**
	 * Création d'un node feuille
	 * @param value valeur de gris du pixel
	 * @param frequency
	 */
	public Node(short value, int frequency )
	{
		this.value = value;
		this.isLeaf = true;
		this.frequency = frequency;
	}
	
	/**
	 * Calcul la fréquence totale du noeud (si feuille = sa propre fréquence, sinon, en fonction des fils)
	 * @return
	 */
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
	
	/**
	 * Renvoie la table de correspondance de ce noeud
	 * On récupère la table de correspondance des noeuds fils, et on concatène les codes par 0 ou 1 en fonction de
	 * fils gauche ou fils droit
	 * @return
	 */
	public ArrayList<TableEntry> getTable()
	{
		ArrayList<TableEntry> res = new ArrayList<TableEntry>();
		
		if(isLeaf())
		{
			res.add(new TableEntry(value, "0"));
		} else {
		
			//Noeud gauche
			if(left.isLeaf())
			{
				res.add(new TableEntry(left.getValue(), "0")); //Dans le cas d'une feuille, la table est directe
			} else {
				ArrayList<TableEntry> tmp = left.getTable(); //Table de ce noeud
				for(TableEntry entry : tmp)
				{
					//On ajoute à la table finale cette table-ci, en concatenant le code
					res.add(new TableEntry(entry.value, "0"+entry.code));
				}
			}
			//Procédé identique pour le noeud droit
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
			
		}
		
		return res;
		
	}

	//Permet de trier les noeuds par ordre croissant de réccurence
	@Override
	public int compareTo(Node arg0) {
		return this.count() - arg0.count();
	}
}
