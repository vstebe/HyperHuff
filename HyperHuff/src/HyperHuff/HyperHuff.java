package HyperHuff;

import image.GreyImage;
import image.HuffFile;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HyperHuff {

	public static void main(String[] args) {
		
		

		System.out.println("Compression de Huffman");
		System.out.println("1. Compression PGM -> HUF");
		System.out.println("2. Décompression HUF -> PGM");
		System.out.println("Choix ?");

		Scanner s = new Scanner(System.in);
		
		
		int res = s.nextInt();
		
		if(res == 1)
		{
			System.out.println("Adresse du PGM :");
			String filename = s.next();
			String filenameOut = filename.substring(0,filename.lastIndexOf('.')) + ".huf";
			
			GreyImage img;
			try {
				img = GreyImage.loadPGM(filename);
				HuffFile h = new HuffFile();
				h.save(img, filenameOut);
			
				
				System.out.println("Enregistré en " + filenameOut);
				File pgmFile = new File(filename);
				File hufFile = new File(filenameOut);
				
				System.out.println("Taux de compression : " + (100-100*hufFile.length()/pgmFile.length()) + "%");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			
			System.out.println("Adresse du HUF :");
			String filename = s.next();
			String filenameOut = filename.substring(0,filename.lastIndexOf('.')) + ".pgm";
			
			GreyImage img;
			try {

				HuffFile h = new HuffFile();
				img = h.openHuf(filename);
			
				img.save(filenameOut);
				
				System.out.println("Enregistré en " + filenameOut);
				File pgmFile = new File(filenameOut);
				File hufFile = new File(filename);
				
				System.out.println("Taux de compression : " + (100-100*hufFile.length()/pgmFile.length()) + "%");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		s.close();
	}

}
