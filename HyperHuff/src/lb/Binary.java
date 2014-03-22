package lb;

import image.HuffFile;

import java.util.ArrayList;

import HyperHuff.Reader;
import HyperHuff.StringByte;

public class Binary {

	public static void main(String[] args) {
		
HuffFile f = new HuffFile();

f.openHuf("/tmp/test.huf");

StringBuffer str = new StringBuffer("0100010");
byte[] bytes = new byte[2];
StringByte.encodebool(str, bytes);
System.out.println(bytes[0]);
System.out.println(StringByte.toString(bytes));

//byte b = 2;
//System.out.println(StringByte.toString(new byte[]{2}));
	}

}
