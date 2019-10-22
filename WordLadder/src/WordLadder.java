import java.util.*;
import java.io.*;

public class WordLadder {
	public static  boolean  validStringDist(String s1, String s2)
	{
		int a, b;
		int difference = Math.abs(s1.length() - s2.length());
		
		for(int i = 0; i < Math.min(s1.length(), s2.length()); i++)
		{
			a = s1.charAt(i);
			b = s2.charAt(i);
			
			if(a != b) difference++;
		}
		
		return difference == 1;
	}
	public static ArrayList<String> generateChildren(String curr, HashSet<String> dict)
	{
		ArrayList<String> validChildren = new ArrayList<String>();
		String letters = "qwertyuiopasdfghjklzxcvbnm";

		for(int i = 0; i < curr.length(); i++)
		{
			for(int k = 0; k < 26; k++)
			{
				String cpy = curr.substring(0,i)
									 + letters.substring(k,k+1)
									 + curr.substring(i+1, curr.length());
				
				if(dict.contains(cpy)) validChildren.add(cpy);
			}
		}
		
		return validChildren;
	}
	public static LinkedList<String> solve(Pair currP, HashSet<String> dict)
	{
		//System.out.println(currP.toString());
		if(currP.start.length() != currP.end.length()) return null;
		
		/* SOME TIME COMPLEXITIES TO TAKE INTO CONSIDERATION
		 * the insert front and pop front has O(1) complexity
		 * insert back and pop back has O(n) complexity
		 */
		
		/*
		 * Solving approach. BFS.
		 * One could call it flood fill.
		 * pop front
		 * check front
		 * mark front
		 * search
		 * push children
		 */
		
		LinkedList<LinkedList<String>> queue = new LinkedList<LinkedList<String>>();
		HashSet<String> seen= new HashSet<String>();
		
		queue.addFront(new LinkedList<String>(currP.start));
		
		while(!queue.isEmpty())
		{
			LinkedList<String> node = queue.popBack();
			String previous = node.getFront();
			
			if(previous.equals(currP.end) && node.size() == 1)
			{
				node.addFront(currP.end);
				return node;
			}
			if(previous.equals(currP.end)) return node;
			
			ArrayList<String> children = generateChildren(previous, dict);
			
			//System.out.println(node.toString());
			
			for(String child : children)
			{
				
				if(seen.contains(child)) continue;
				seen.add(child);
				
				//System.out.println(child);
				//copy bc references suck
				LinkedList<String> cpyNode = new LinkedList<String>();
				
				for(int k =  node.size() - 1; k >= 0; k--)
					cpyNode.addFront(node.get(k));
				cpyNode.addFront(child);
				
				//System.out.println(cpyNode.toString());

				queue.addFront(cpyNode);
				
				
			}
		}
		
		
		return null;
	}

	public static void main(String[] args) throws IOException
	{
		/*
		 * create node for easier handling
		 * read input text into ArrayList
		 * read dictionary text into HashSet
		 */
		//read------------------------------------------------
		File inFile = new File("input.txt");
		File dictFile = new File("dict.txt");
		
		Scanner inputSc = new Scanner(inFile);
		Scanner dictSc = new Scanner(dictFile);
		
		ArrayList<Pair> problem = new ArrayList<Pair>();
		HashSet<String> dictionary = new HashSet<String>();
		

		while(inputSc.hasNextLine())
		{
			String s = inputSc.nextLine();
			String[] split = s.split(" ");
			problem.add(new Pair(split));
		}
		while(dictSc.hasNextLine())
			dictionary.add(dictSc.nextLine());
		//System.out.println(dictionary.size());
		inputSc.close();
		dictSc.close();
		

		//Solve--------------------------------------------
		for(Pair p : problem)
		{
			LinkedList<String> solution  = solve(p, dictionary);
			
			if(solution == null) System.out.println("No Solution");
			else System.out.println(solution.rtoString());
		}
		
	
	}
}
