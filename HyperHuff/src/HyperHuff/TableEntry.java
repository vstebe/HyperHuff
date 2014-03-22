package HyperHuff;

import java.util.Arrays;

public class TableEntry {
	public short value;
	public String code;
	
	public TableEntry(short v, String code)
	{
		value = v;
		this.code = code;
	}
	
	/**
	 * Transforme cette entrée de la table en bytes
	 * VALEUR DU PIXEL
	 * NBRE BITS DU CODE
	 * CODE
	 * @return tableau de byte à enregistrer
	 */
	public byte[] toBytes()
	{
		//On a besoin d'un stringbuffer
		StringBuffer buffer = new StringBuffer(code);
		
		//Init du tableau de bytes
		byte[] bytes = new byte[(int)Math.ceil(((double)buffer.length())/8.f)];
		StringByte.encodebool(buffer, bytes);
		byte nbBits = (byte) code.length();	
		
		//On a désormais le code en bytes
		byte val = (byte)value;

		//On remplit le tableau final
		byte[] res = new byte[bytes.length+2];
		res[0] = val;
		res[1] = nbBits;
		System.arraycopy(bytes, 0, res, 2, bytes.length);

		return res;
	}
	
	/**
	 * Construit une entrée du tableau en fonction des bytes d'entrée
	 * @param bytes
	 * @return
	 */
	public static TableEntry fromBytes(byte[] bytes)
	{
		short s = (short) (bytes[0] & 0xFF); //On récupère la valeur du pixel
		
		byte[] codeBytes = Arrays.copyOfRange(bytes, 2, bytes.length); //On récupère le tableau de bytes correspondant au code uniquement
		String code = StringByte.toString(codeBytes).toString(); //On transfere
		
		int codeLength = (int) bytes[1]; //Longueur du code

		//On doit couper le String en fonction de la longueur réelle du code
		if(codeLength>8)
			code = code.substring(0, (int)bytes[1]);
		else
			code = code.substring(0, codeLength);
		
		TableEntry entry = new TableEntry(s, code);
		return entry;
		
	}


}
