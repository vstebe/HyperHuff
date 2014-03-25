package HyperHuff;

import image.GreyImage;
import image.HuffFile;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HyperHuff {

	public static void main(String[] args) {
		
		
		if(args.length != 2)
		{
			System.out.println("Usage : file.pgm file.huf (compression");
			System.out.println("	ou  file.huf file.pgm (décompression)");
			return;
		}
		
		String filename = args[0];
		String filenameOut = args[1];
		
		if(!(new File(filename)).exists())
		{
			System.out.println("Le fichier "+ filename + " n'existe pas");
			return;
		}
		
		if(args[0].endsWith(".pgm"))
		{
			
			
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
		
	}

}
