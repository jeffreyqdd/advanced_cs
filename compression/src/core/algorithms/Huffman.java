package core.algorithms;

import core.stream.BitInputStream;
import core.stream.BitOutputStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Huffman {

	private HashMap<Character, Integer> freq;
	private HashMap<Character, String> huffmanCodeString;
	private HashMap<Character, Integer> huffmanCodeBit;
	private HashMap<Integer, Character> huffmanCodeChar;
	
	private String text;
	
	public Node root;
	
	
	public Huffman()
	{
		huffmanCodeString = new HashMap<>();
		huffmanCodeBit = new HashMap<>();
		huffmanCodeChar = new HashMap<>();
	}
	//create the binary tree from a string of text
	//generates root and frequency
	public Huffman(String data)
	{
		this.text = data;
		
		generateFreqTable();
		generateRoot();
		
		huffmanCodeString = new HashMap<>();
		huffmanCodeBit = new HashMap<>();
		huffmanCodeChar = new HashMap<>();
		
		encode(root, "", 0);
		
		for(Character c : huffmanCodeString.keySet())
		{
			System.out.println(c + "->" + huffmanCodeString.get(c));
		}
		
	}
	
	public void writeBitCodeToFile(String fileName)
	{
		BitOutputStream fout = new BitOutputStream(fileName);
		StringBuilder stringFormat = new StringBuilder();
		
		for(int i = 0; i < text.length(); i++)
		{
			char c = text.charAt(i);
			
			fout.writeBits(huffmanCodeString.get(c).length(), huffmanCodeBit.get(c));
			
			stringFormat.append(huffmanCodeString.get(c) + " ");
		}
		
		fout.writeBits(huffmanCodeString.get('\0').length(), huffmanCodeBit.get('\0'));
		
		
		fout.flush();
		
		fout.close();
		
		//System.out.println(stringFormat.toString());
		
	}
	
	public void decode(String fileName) throws IOException
	{
		BitInputStream fin = new BitInputStream(fileName);
		
		StringBuilder decodedString = new StringBuilder();
		
		while (decode(root, fin, decodedString))
		{
			int i = 0;
		}
		
		System.out.println();
		System.out.println(decodedString.toString());
		
	}
	
	public void writeRootSave(String fileName) throws IOException
	{
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		
		Queue<Node> q = new LinkedList<>();
		
		q.add(this.root);
		
		while(!q.isEmpty())
		{
			Node n = q.poll();
			
			if(n == null)
				continue;
			
			writer.write(n.data);
			
			q.add(n.left);
			q.add(n.right);
			
		}
		
		writer.flush();
		writer.close();
	}
	
	public void loadRootSave(String fileName) throws IOException
	{
		Scanner sc = new Scanner(new File(fileName));
		
		String uncompress = sc.nextLine() + "\n" + sc.nextLine();
		
		int index = 0;
		
		Queue<Node> q = new LinkedList<>();
		
		this.root = new Node();
		
		q.add(root);
		
		System.out.println(uncompress);
		
		while(index < uncompress.length())
		{
			Node n = q.poll();
			
			if(n == null)
			{
				n = new Node();
			}
			
			n.data = uncompress.charAt(index);
			System.out.print(n.data);
			index++;
			
			q.add(n.left);
			q.add(n.right);
		}
		
		encode(root, "", 0);
		
		System.out.println("--ewr-w-ef-we-rfw----");
		System.out.println(root.data);
		for(Character c : huffmanCodeString.keySet())
		{
			System.out.println(c + "->" + huffmanCodeString.get(c));
		}
		
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
			System.out.print("0");
			return decode(root.left, fin, str);
		}
		else
		{
			System.out.print("1");
			return decode(root.right, fin, str);
		}
		
		
	}
	
	private void generateFreqTable()
	{
		
		//System.out.println(data);
		//create frequency table
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
			
			//System.out.println("iter: " + i);
		}
	}
	
	private void generateRoot()
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
	
	
	//generates huffmanCode
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
			System.out.println(root.data + ": " + str + " -> " + Integer.toBinaryString(bit));
		}
		
		int leftBit = bit << 1;
		int rightBit = (bit << 1) | 1;
		
		encode(root.left, str + "0", leftBit);
		encode(root.right, str + "1", rightBit);
		
	}
	
	
	static class Node implements Comparable
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
