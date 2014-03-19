package HyperHuff;

import image.GreyImage;
import image.HuffFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class HyperHuff {

	public static void main(String[] args) {
		
		try 
		{
			GreyImage img = GreyImage.loadPGM("D:\\workspace\\repositorygit\\HyperHuff\\HyperHuff\\res\\barbara.pgm");
			
			HuffFile f = new HuffFile();
			f.save(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		

	}

}
