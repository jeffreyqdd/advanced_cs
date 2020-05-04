package core.algorithms;

import core.stream.BitInputStream;
import core.stream.BitOutputStream;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCore
{
	/**
	 * Hashmap that stores character and its frequency
	 */
	private HashMap<Character, Integer> freq;
	
	/**
	 * Hashmap stores character and its traversal path as string
	 */
	private HashMap<Character, String> huffmanCodeString;
	
	/**
	 * Hashmap stores character and a integer representation of the bits.
	 */
	private HashMap<Character, Integer> huffmanCodeBit;
	
	/**
	 * stores raw text of the text file
	 */
	private String text;
	
	/**
	 * Root of the huffman tree
	 */
	public Node root;
	
	
	/**
	 * default constructor
	 */
	public HuffmanCore()
	{
		huffmanCodeString = new HashMap<>();
		huffmanCodeBit = new HashMap<>();
		freq = new HashMap<>();
	}
	
	//-----------------------------------------------------------------------------ENCODE----
	
	/**
	 * takes a file path, and stores the string data into text
	 * @param filePath path to the text file
	 * @throws Exception exception handling should be done one level up from this
	 */
	public void loadFile(String filePath) throws Exception
	{
		text = Util.readFile(filePath);
	}
	
	/**
	 * creates a frequency table <char, frequency> from text
	 */
	public void generateFrequencyTable()
	{
		freq = new HashMap<>();
		
		for(int i = 0; i < text.length(); i++)
		{
			Character c = text.charAt(i);
			if(!freq.containsKey(c))
			{
				freq.put(c, 1);
			}
			else
			{
				freq.put(c, freq.get(c) + 1);
			}
		}
	}
	
	/**
	 * Generates the huffman tree
	 */
	public void generateRoot()
	{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(Character key : freq.keySet())
		{
			int value = freq.get(key);
			
			pq.add(new Node(key, value, null, null));
		}
		//add eof char
		pq.add(new Node('\0', 1, null, null));
		//build the "binary" tree
		while(pq.size() > 1)
		{
			Node left = pq.poll();
			Node right = pq.poll();
			
			int sum = left.freq + right.freq;
			
			pq.add(new Node('\0', sum, left, right));
		}
		
		root = pq.poll();
	}
	
	/**
	 * Calls the other encode
	 */
	public void encode()
	{
		encode(root, "", 0);
	}
	
	/**
	 * Generates huffmanCodeString and huffmanCodeBit to speed up reading and writing
	 * @param root root of the huffman tree
	 * @param str string representation of traversal
	 * @param bit integer representation of traversal
	 */
	private void encode(Node root, String str, int bit)
	{
		if(root == null)
		{
			return;
		}
		
		if(root.left == null && root.right == null)
		{
			huffmanCodeString.put(root.data, str);
			huffmanCodeBit.put(root.data, bit);
		}
		
		int leftBit = bit << 1;
		int rightBit = (bit << 1) | 1;
		
		encode(root.left, str + "0", leftBit);
		encode(root.right, str + "1", rightBit);
		
	}
	
	/**
	 * Writes the binary text file
	 * @param fileName fileName with desired filepath
	 */
	public void writeBitCodeToFile(String fileName)
	{
		BitOutputStream fout = new BitOutputStream(fileName);
		
		for(int i = 0; i < text.length(); i++)
		{
			//System.out.println("entering");
			
			char c = text.charAt(i);
			
			fout.writeBits(huffmanCodeString.get(c).length(), huffmanCodeBit.get(c));

		}
		
		fout.writeBits(huffmanCodeString.get('\0').length(), huffmanCodeBit.get('\0'));
		
		
		fout.flush();
		
		fout.close();
	}
	
	
	/**
	 * writes the frequency table to file
	 * @param fileName desired file name with file path
	 * @throws Exception exception handling should be done one level up
	 */
	public void writeFreqTableToFile(String fileName) throws Exception
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		for(char c : freq.keySet())
		{
			if(c == 10)
			{
				//new line
				writer.write("[newline]");
				writer.write('\n');
			}
			else if(c == 32)
			{
				//space
				writer.write("[space]");
				writer.write('\n');
			}
			else
			{
				//non new line
				writer.write(Character.toString(c));
				writer.write('\n');
			}
			writer.write(Integer.toString(freq.get(c)));
			writer.write('\n');
			
			
		}
		
		writer.close();
		
	}
	
	
	//-----------------------------------------------------------------------------DECODE----
	
	/**
	 * reads frequency table into memory
	 * @param fileName path to file
	 * @throws Exception should be handled one level up
	 */
	public void readFreqTableToMemory(String fileName) throws Exception
	{
		Scanner sc = new Scanner(new File(fileName));

		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			int count = Integer.parseInt(sc.nextLine());
			
			
			//System.out.println(line + " " + count);
			if (line.equals("[newline]"))
			{
				//newline
				//System.out.println("newline");
				freq.put('\n', count);
			}
			else if (line.equals("[space]"))
			{
				//space
				freq.put(' ', count);
			}
			else
			{
				//System.out.println("3ssss");
				freq.put(line.charAt(0), count);
			}

		}
	}
	
	/**
	 * rebuild huffman tree from frequency table
	 */
	public void recover()
	{
		generateRoot();
		encode();
	}
	
	/**
	 * turn the binary file back into text
	 * @param fileName path to binary file
	 * @throws Exception should be handled one level up
	 */
	public void decode(String fileName) throws Exception
	{
		BitInputStream fin = new BitInputStream(fileName);
		
		StringBuilder decodedString = new StringBuilder();
		
		while (decode(root, fin, decodedString))
		{
			int i = 0; //placeholder
		}
		
		//System.out.println();
		//System.out.println(decodedString.toString());
		text = decodedString.toString();
	}
	
	/**
	 *
	 * @param root root of huffman tree
	 * @param fin BitInputStream to read bits from
	 * @param str reconstructed text file
	 * @return returns !eof
	 * @throws IOException do not catch. if one is thrown, something is wrong
	 */
	private boolean decode(Node root, BitInputStream fin, StringBuilder str) throws IOException
	{
		if(root.left == null && root.right == null)
		{
			if(root.data == '\0')
			{
				return false;
			}
			else
			{
				str.append(root.data);
				return true;
			}
		}
		
		int bit = fin.readBits(1);
		
		if(bit == 0)
		{
			//System.out.print("0");
			return decode(root.left, fin, str);
		}
		else
		{
			//System.out.print("1");
			return decode(root.right, fin, str);
		}
		
	}
	
	/**
	 * writes decompressed text to file
	 * @param fileName file name and path to file
	 * @throws Exception handle exception one level up
	 */
	public void writeFinalToFile(String fileName) throws Exception
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		writer.write(text);
		writer.flush();
		writer.close();
	}
	
	
	public HashMap<Character, String> getHuffmanCodeString()
	{
		return huffmanCodeString;
	}
	
	/**
	 * Node class in huffman tree
	 */
	private class Node implements Comparable
	{
		char data;
		
		int freq;
		Node left, right;
		
		boolean isEOF;
		
		Node(){}

		Node(char data, int freq, Node left, Node right)
		{
			this.data = data;
			this.freq = freq;
			this.left = left;
			this.right= right;
		}
		@Override
		public int compareTo(Object o)
		{
			return freq - ((Node) o).freq;
		}
	}
	
}
