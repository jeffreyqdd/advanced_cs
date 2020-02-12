package core.algorithms;

import java.io.File;
import java.util.Scanner;

public class Util
{
	public static String readFile(String fileName) throws Exception
	{
	
		Scanner fileSc = new Scanner(new File(fileName));
		
		StringBuilder conc = new StringBuilder();
		
		while(fileSc.hasNextLine())
		{
			conc.append(fileSc.nextLine());
			conc.append('\n');
			
		}
		return conc.toString();
	}
	/*
	public static void runHuffmanCompression(String fileName) throws Exception
	{
		String data = readFile(fileName);
		String fileHeader = (fileName.split(".txt"))[0];
		
		
		Huffman huffman = new Huffman(data);
		huffman.writeBitCodeToFile("text/intermediate/" + fileHeader + ".lmao");
		huffman.writeRootSave("text/intermediate/" + fileHeader + "-bin.txt");
	}
	
	public static void runHuffmanUnpacking(String fileName) throws Exception
	{
		Huffman huffman = new Huffman();
		String fileHeader = (fileName.split(".lmao"))[0];
		
		huffman.loadRootSave("text/intermediate/" + fileHeader + "-bin.txt");
		huffman.decode("text/intermediate/" + fileHeader + ".lmao");
		
		
	}*/
	
	
}
