package mazecore;

import file.FileDirectory;
import file.FileFinder;

import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) {
	    Scanner cin = new Scanner(System.in);
	
	    //choose map
		String mapFile = whichOne(cin);
		System.out.println("you have chosen: " + mapFile);
		
		//process map
		
	   
    }
	
	public static String whichOne(Scanner cin)
	{
		//find files
		ArrayList<String> imgFiles = FileFinder.findFile(FileDirectory.RAW_IMAGES);
		
		
		System.out.println("Enter in the number of the img you want to process:\n");
		for(int i = 0; i < imgFiles.size(); i++)
			System.out.println(i + ": " + imgFiles.get(i));
		int choice = Integer.parseInt(cin.nextLine());
		
		return imgFiles.get(choice);
	}
}
