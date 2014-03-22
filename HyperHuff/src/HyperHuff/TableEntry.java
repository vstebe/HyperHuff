package HyperHuff;

import java.util.Arrays;

public class TableEntry implements Comparable {
	public short value;
	public String code;
	
	public TableEntry(short v, String code)
	{
		value = v;
		this.code = code;
	}
	
	public byte[] toBytes()
	{
		//System.out.println("code : " + value + " "  + code);
		StringBuffer buffer = new StringBuffer(code);
		byte val = (byte)value;
		byte[] bytes = new byte[(int)Math.ceil(((double)buffer.length())/8.f)];
		StringByte.encodebool(buffer, bytes);
		byte nbBits = (byte) code.length();	
		byte[] res = new byte[bytes.length+2];
		
		res[0] = val;
		res[1] = nbBits;
		
		//System.out.println(res.length);
		
		System.arraycopy(bytes, 0, res, 2, bytes.length);
		
		TableEntry entry2 = fromBytes(res);
		if(!entry2.code.equals(code))
			System.out.println("erreur : " + code +  " " + entry2.code);
		
		return res;
	}
	
	public static TableEntry fromBytes(byte[] bytes)
	{
		short s = (short) (bytes[0] & 0xFF);
		byte[] codeBytes = Arrays.copyOfRange(bytes, 2, bytes.length);
		String code = StringByte.toString(codeBytes).toString();
		int codeLength = (int) bytes[1];
		//System.out.println("l" + codeLength+ " " + code);
		if(codeLength>8)
			code = code.substring(0, (int)bytes[1]);
		else
			code = code.substring(0, codeLength);
		
		TableEntry entry = new TableEntry(s, code);
		//System.out.println("code : " +entry.value + " "  + entry.code);
		return entry;
		
	}

	@Override
	public int compareTo(Object arg0) {
		return ((TableEntry) arg0).code.length() - code.length();
	}
}
