package HyperHuff;

import java.util.ArrayList;
import java.util.Arrays;

public class StringByte {
	/*public static int encodebool(StringBuffer arr, byte[] res)
	 {
		 int nbBytes = (int)Math.ceil((float)(arr.length())/8.f);
		 
		 int supp = 0;
		 
		 for(int i=0; i<nbBytes; i++)
		 {
			 int start = i*8;
			 int end = ((i+1)*8 < arr.length()) ? (i+1)*8 : arr.length();
			 
			 String substring = arr.substring(start, end);
			 
			 //System.out.println(substring);
			 
			 while(substring.length() <7)
			 {
				 substring=substring+"0";
				 supp++;
			 }
			 //String prefix = (arr.charAt(start-1) == '1') ? "-" : "+";
			 
			 res[i] = (byte) Integer.parseInt(substring, 2);
		 }
		 
		 return nbBytes*8-supp;
	 }*/
	
	
	public static int encodebool(StringBuffer arr, byte[] res)
	{
		int currentByte = -1;
		int currentBit;
		for(int i=0; i<arr.length(); i++)
		{
			currentBit = i%8;
			if(currentBit == 0)
				currentByte++;
			
			if(arr.charAt(i) == '1')
				res[currentByte] = (byte) (res[currentByte] | (1 << currentBit));
			else
				res[currentByte] = (byte) (res[currentByte] & ~(1 << currentBit));
			
		}
		
		return arr.length();
	}
	
	
	/*public static StringBuffer toString(byte[] bytes)
	{
		StringBuffer buffer = new StringBuffer();
		int i = 0;
		//System.out.println(bytes.length);
		for(byte b : bytes)
		{
			//System.out.println((double)i/(double)bytes.length);
			StringBuffer buffer2 = new StringBuffer(String.format("%8s", Integer.toBinaryString((b) & 0xFF)).replace(" ", ""));
			
			while(buffer2.length() < 8)
				buffer2.insert(0, '0');
			
			buffer.append(buffer2.toString());
			
			//buffer.append(""+(int)(b & 0xFF));
			//if(i%1000 == 0)
				//System.out.println(i);
			i++;
		}
		return buffer;
	}*/
	
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
