package lb;

import java.util.ArrayList;
import java.util.Arrays;

import HyperHuff.Reader;
import HyperHuff.StringByte;

public class Binary {

	public static void main(String[] args) {
		
Reader r = new Reader("D:\\workspace\\repositorygit\\HyperHuff\\HyperHuff\\res\\test.huf");
System.out.println(r.getSizeX());
System.out.println(r.getSizeY());
r.getArbre();
r.getSequence();

	}

}
