package lb;

import java.util.ArrayList;

import HyperHuff.StringByte;

public class Binary {

	public static void main(String[] args) {
		
String str = "000000001";
byte[] bytes = new byte[(int)Math.ceil(((double)str.length())/8.f)];
System.out.println(StringByte.encodebool(new StringBuffer(str), bytes));

System.out.println(bytes.length);

	}

}
