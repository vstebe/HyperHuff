package HyperHuff;

import java.util.ArrayList;
import java.util.Arrays;

public class StringByte {
	public static int encodebool(StringBuffer arr, byte[] res)
	 {
		 int nbBytes = (int)Math.ceil((float)(arr.length())/8.f);
		 
		 int supp = 0;
		 
		 for(int i=0; i<nbBytes; i++)
		 {
			 int start = i*8+1;
			 int end = ((i+1)*8 < arr.length()) ? (i+1)*8 : arr.length();
			 
			 String substring = arr.substring(start, end);
			 
			 //System.out.println(substring);
			 
			 while(substring.length() <7)
			 {
				 substring+="0";
				 supp++;
			 }
			 String prefix = (arr.charAt(start-1) == '1') ? "+" : "-";
			 
			 res[i] = Byte.parseByte(prefix + substring,2);
		 }
		 
		 return nbBytes*8-supp;
	 }
	
	public static ArrayList<Byte> encodebool(String[] arr)
	 {
		 ArrayList<Byte> bytes = new ArrayList<Byte>();
		 
		 /*int currentStringIndex = 0;
		 String str = arr[0];
		 while(currentStringIndex < arr.length)
		 {
			 
			 while(str.length() < 8 && currentStringIndex < arr.length -1)
			 {
				 currentStringIndex++;
				 str += arr[currentStringIndex];
				 
				 
			 }
			 
			 System.out.println("str : " + str + "   c:" + currentStringIndex);
			 
			 int nbBytes = str.length()/8;
			 
			 
			 for(int i=0; i<nbBytes; i++)
			 {
				 int start = i*8;
				 int end = (i+1)*8;
				 
				 
				 if(end > str.length())
					 end = str.length();
				 
				 System.out.println("start : " + start);
				 
				 String substring = str.substring(start, end);
				 
				 
				 while(substring.length() <8)
					 substring+="0";
				 
				 bytes.add(Byte.parseByte(substring,2));
			 }
			 
			 str = str.substring(0, nbBytes*8);
			 
			 System.out.println("shift : ");
			 
			 
		 }*/
		
		 return bytes;
	 }
}
