package HyperHuff;

public class StringByte {

	
	/**
	 * Rempli le tableau passé en paramètre de bytes correspondant au String d'entrée
	 * Par exemple la chaine "000000011" sera transformée en tableau de 2 bytes : 00000001 et 10000000
	 * @param arr String à transformer
	 * @param res Tableau à remplir
	 * @return nombre de bits réellement utiles
	 */
	public static int encodebool(StringBuffer arr, byte[] res)
	{
		int currentByte = -1;
		int currentBit;
		for(int i=0; i<arr.length(); i++)
		{
			currentBit = i%8;
			if(currentBit == 0)
				currentByte++;
			
			//On set le bit courant du byte courant en fonction de la chaine de caractère
			if(arr.charAt(i) == '1')
				res[currentByte] = (byte) (res[currentByte] | (1 << currentBit));
			else
				res[currentByte] = (byte) (res[currentByte] & ~(1 << currentBit));
			
		}
		
		return arr.length();
	}
	
	

	/**
	 * Reconstruit le String correspondant au tableau de bytes d'entrée
	 * @param bytes tableau de bytes
	 * @return représentation binaire
	 */
	public static StringBuffer toString(byte[] bytes)
	{
		StringBuffer buffer = new StringBuffer(bytes.length*8);
		
		for(byte b : bytes)
		{
			for (int i = 0; i < 8; i++)  
		    {  
		        buffer.append(b & 1);  
		        b >>= 1;  
		    }
		}
		
		return buffer;
	}
	
	
}
