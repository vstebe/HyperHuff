package lb;

import java.util.ArrayList;

import HyperHuff.Reader;
import HyperHuff.StringByte;

public class Binary {

	public static void main(String[] args) {
		
Reader r = new Reader("/tmp/test.huf");
System.out.println(r.getArbre().length);

	}

}
