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
	public void save(GreyImage img, String filename)
	{
		
		//Création des nodes de départ pour l'arbre. On compte le nombre d'occurence de chaque valeur de gris
		HashMap<Short, Integer> caracters = new HashMap<Short, Integer>();
		for(int i=0; i<img.getSizeData(); i++)
		{
			Short s = img.getPixel(i);
			if(caracters.containsKey(s))
				caracters.put(s, caracters.get(s)+1);
			else
				caracters.put(s, 0);
		}
		//On transforme le hashmap (valeur pixel - nb occurences) en liste de nodes
		Node[] nodes = new Node[caracters.size()];
		int i=0;
		for(Entry<Short, Integer> entry : caracters.entrySet())
		{
			nodes[i] = new Node(entry.getKey(), entry.getValue());
			i++;
		}

		//Qu'on envoie à Huffman pour la compression
		Huffman h = new Huffman(new ArrayList<Node>(Arrays.asList(nodes)));
		
		ArrayList<TableEntry> table = h.process(); //On a notre table de correspondance
		
		
		//On s'attaque maintenant à la compression elle-même
		StringBuffer buffer = new StringBuffer();
		
		//Pour chaque pixel
		for(int j=0; j<img.getSizeData(); j++)
		{
			short value = img.getPixel(j);
			String code = "";
			for(TableEntry entry : table)
			{ //Parcours de la table
				if(entry.value == value)
				{
					code = entry.code;
					break;
				}
			}
		
			buffer.append(code);//On enregistre le code dans le buffer
		}
		
		
		//Init du tablaeu de bytes
		byte[] bytesImage = new byte[(int)Math.ceil(((double)buffer.length())/8.f)];
		
		//On transforme le buffer de String en bytes pour l'enregistrement
		StringByte.encodebool(buffer, bytesImage);
		
		//Enregistrement du tableau de correspondance en bytes
		byte[][] bytesTab = new byte[table.size()][];
		
		for(int j=0; j<table.size(); j++)
		{
			bytesTab[j] = table.get(j).toBytes();
		}
				
		//On envoie ces tableaux au Saver qui fera l'enregistrement
		Saver.save(img.getSizeX(), img.getSizeY(), bytesTab, bytesImage, filename);

		
	}
	
	public GreyImage openHuf(String str)
	{
		//On ouvre le fichier via Reader qui nous donne les tableaux de bytes
		Reader r = new Reader(str);
		
		//Reconstruction de la table de correspondance
		byte[][] tableBytes = r.getArbre();
		ArrayList<TableEntry> table = new ArrayList<TableEntry>();
		for(int i=0; i<tableBytes.length; i++)
		{
			table.add(TableEntry.fromBytes(tableBytes[i]));
		}
		
		//On reconstruit l'image via la table et la séquence de bytes
		int dimX = r.getSizeX();
		int dimY = r.getSizeY();
		byte[] bytes = r.getSequence();

		//On transforme le tableau de bytes en String simple
		String img = StringByte.toString(bytes).toString();
		
		short[] values = new short[dimX*dimY];
		
		int index = 0; //Position courante dans le String de l'image
		
		//Pour chaque pixel
		for(int i=0; (i<dimX*dimY) && index < img.length(); i++)
		{
			short value = 0;
			String code = "";
			//On cherche le code à utiliser
			for(TableEntry entry : table)
			{
				if((index + entry.code.length()) < img.length() &&  img.subSequence(index, index + entry.code.length()).equals(entry.code))
				{
					value = entry.value;
					code = entry.code;
					break;
				}
					
			}

			index+=code.length(); //On continue le parcours
			values[i] = value;
			
		}
		

		
		return new GreyImage(dimX, dimY, values);
	}
	

	
	 
}
