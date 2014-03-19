package HyperHuff;

public class TableEntry {
	public short value;
	public String code;
	
	public TableEntry(short v, String code)
	{
		value = v;
		this.code = code;
	}
	
	public byte[] toBytes()
	{
		StringBuffer buffer = new StringBuffer(code);
		byte val = (byte)value;
		byte[] bytes = new byte[(int)Math.ceil(((double)buffer.length())/8.f)];
		byte nbBits = (byte) StringByte.encodebool(buffer, bytes);
				
		byte[] res = new byte[bytes.length+2];
		
		res[0] = val;
		res[1] = nbBits;
		
		System.arraycopy(bytes, 0, res, 2, bytes.length);
		
		return res;
	}
}
