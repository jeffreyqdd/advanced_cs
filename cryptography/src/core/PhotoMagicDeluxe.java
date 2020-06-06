package core;

import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class PhotoMagicDeluxe
{
	private static PhotoMagic photoMagic = new PhotoMagic();
	private static HashMap<Character, Integer> base64Values = new HashMap<>(); //Key = letter, value = base64 integer value
	private static Scanner sc = new Scanner(System.in);
	
	private static String binaryPassword = "";
	private static int tap = 0;
	
	private static File inFile;
	
	
	
	private static void generateHashMap() {
		String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
		
		for(int i = 0; i < base64.length(); i++)
		{
			base64Values.put(base64.charAt(i), i);
		}
	}
	
	private static void getPassword() {
		//let user choose password type
		System.out.println("(A)lphanumeric or (B)inary format? ");
		String ans = sc.nextLine();
		
		if(ans.equals("A"))
		{
			System.out.println("Enter in password: ");
			
			
			String password64 = sc.nextLine();
			for (int i = 0; i < password64.length(); i++)
			{
				
				String temp = Integer.toBinaryString(base64Values.get(password64.charAt(i)));
				binaryPassword += ("000000" + temp).substring(temp.length()); //adds leading zeroes
			}
			
			System.out.println("Enter in tap: ");
			tap = Integer.parseInt(sc.nextLine());
		}
		else {
			System.out.println("Enter in password: ");
			binaryPassword = sc.nextLine();
			System.out.println("Enter in tap: ");
			tap = Integer.parseInt(sc.nextLine());
		}
	}
	
	private static void getFile() {
		System.out.println("enter file name: ");
		inFile = new File("assets/" + sc.nextLine());
	}
	
	private static void processData() {
		LFSR lfsr = new LFSR(binaryPassword, tap);
		System.out.println(binaryPassword + " " + tap);
		Picture finalPic = PhotoMagic.transform(new Picture(inFile), lfsr);
		finalPic.show();
	}
	public static void main(String[] args) throws FileNotFoundException
	{

		//generate the map to convert base 64 into numbers
		generateHashMap();
		//get file
		getFile();
		//get password, tap, and convert to binary.
		getPassword();
		//process and display
		processData();
	}
	
	
}
