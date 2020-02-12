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
	private HashMap<Character, Integer> freq;
	private HashMap<Character, String> huffmanCodeString;
	private HashMap<Character, Integer> huffmanCodeBit;
	private HashMap<Integer, Character> huffmanCodeChar;
	
	private String text;
	
	public Node root;
	
	//default constructor
	public HuffmanCore()
	{
		huffmanCodeString = new HashMap<>();
		huffmanCodeBit = new HashMap<>();
		huffmanCodeChar = new HashMap<>();
		freq = new HashMap<>();
	}
	
	//-----------------------------------------------------------------------------ENCODE----
	//read in text file
	//should include path
	public void loadFile(String filePath) throws Exception
	{
		text = Util.readFile(filePath);
	}
	
	//creates a frequency table of all the chars
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
	
	//creates the binary huffman tree
	public void generateRoot()
	{
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(Character key : freq.keySet())
		{
			int value = freq.get(key);
			
			pq.add(new Node(key, value, null, null));
		}
		//add eof char
		pq.add(new Node('\0', 1, null, null, true));
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
	
	//creates the hash tables used to speed up the process
	public void encode()
	{
		encode(root, "", 0);
	}
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
			huffmanCodeChar.put(bit, root.data);
		}
		
		int leftBit = bit << 1;
		int rightBit = (bit << 1) | 1;
		
		encode(root.left, str + "0", leftBit);
		encode(root.right, str + "1", rightBit);
		
	}
	
	//writes the binary file to a file
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
	
	public void writeFreqTableToFile(String fileName) throws Exception
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		for(char c : freq.keySet())
		{
			if(c == 10)
			{
				//new line
				writer.write(Character.toString(c));
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
	public void readFreqTableToMemory(String fileName) throws Exception
	{
		Scanner sc = new Scanner(new File(fileName));

		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			int count = Integer.parseInt(sc.nextLine());


			//System.out.println(line + " " + count);
			if(line.equals(""))
			{
				//newline
				//System.out.println("newline");
				freq.put('\n', count);
			}
			else
			{
				//System.out.println("3ssss");
				freq.put(line.charAt(0), count);
			}

		}
	}
	
	public void recover()
	{
		generateRoot();
		encode();
	}
	
	public void decode(String fileName) throws Exception
	{
		BitInputStream fin = new BitInputStream(fileName);
		
		StringBuilder decodedString = new StringBuilder();
		
		while (decode(root, fin, decodedString))
		{
			int i = 0;
		}
		
		//System.out.println();
		//System.out.println(decodedString.toString());
		text = decodedString.toString();
	}
	
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
	
	public void writeFinalToFile(String fileName) throws Exception
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		writer.write(text);
		writer.flush();
		writer.close();
	}
	
	
	
	
	
	private class Node implements Comparable
	{
		char data;
		
		int freq;
		Node left, right;
		
		boolean isEOF;
		
		Node(){}
		Node(char data, int freq, Node left, Node right)
		{
			this(data, freq, left, right, false);
		}
		Node(char data, int freq, Node left, Node right, boolean isEOF)
		{
			this.data = data;
			this.freq = freq;
			this.left = left;
			this.right= right;
			this.isEOF = isEOF;
		}
		
		@Override
		public int compareTo(Object o)
		{
			return freq - ((Node) o).freq;
		}
	}
	
}
