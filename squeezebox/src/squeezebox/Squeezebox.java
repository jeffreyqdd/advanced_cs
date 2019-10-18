package squeezebox;

import java.util.*;
import java.io.*;


public class Squeezebox {
	
	public static LinkedList<Node> table = new LinkedList<Node>();
	
	/*display*/
	public static void displaySolution(LinkedList<Node> solution) {
		
		if(solution.size() == 1)
		{
			System.out.println("1 pile remaining: 52");
			return;
		}
		
		
		
		System.out.print(solution.size());
		System.out.print(" piles remaining: ");
		
		for(int i = 0; i < solution.size(); i++)
			System.out.print(solution.get(i).stack.size() + " ");
		System.out.println();
		
	}
	
	/*returns true it same rank or same suit*/
	public static boolean isMatching(String card1, String card2)
	{
		String rank1 = card1.substring(0,1), rank2 = card2.substring(0,1);
		String suit1 = card1.substring(1,2), suit2 = card2.substring(1,2);
		
		if(rank1.equals(rank2)) return true;
		if(suit1.equals(suit2)) return true;
		return false;
	}
	
	/*
	 * Moves start node's top to destinations node's top
	 * deletes start node if size = 0 
	 */
	public static void combine(int destination, int start)
	{

		Node ptr = table.get(start);
		String value = ptr.stack.removeFirst(); //remove
	

		//add
		Node newPtr = table.get(destination);
		
		//System.out.println(Integer.toString(destination) +  " "  + Integer.toString(start));
		//System.out.println(value + "--" + newPtr.getFront());
		
		
		newPtr.add(value);
		

		
		//remove
		if(ptr.stack.size() == 0) table.remove(start);
	}
	
	public static LinkedList<Node> solve(String[] deck) {
		//reset
		table.clear();
		
		//initialize and deal the cards

		for(String s : deck) table.addLast(new Node(s));
		
		boolean change = true;
		
		while(change) //over when pile has not been reduced
		{
			change = false; 
			
			for(int i = 0; i < table.size(); i++)
			{
				System.out.print(table.get(i).toString() + " ");
			}
			System.out.println();
			
			
			//simulate, we can't look left of 1
			for(int i = 1; i < table.size(); i++) 
			{
				if(i >= 3)
				{
					//can look left 3
					String c1 = table.get(i).getFront(), c2 = table.get(i - 3).getFront();
					
					if(isMatching(c1,c2))
					{
						//combine and shrink;
						combine(i - 3, i);
						change = true;
						break;
					}
				}
				
				//look normally
				String c1 = table.get(i).getFront(), c2 = table.get(i - 1).getFront();
				if(isMatching(c1,c2))
				{
					//combine and shrink;
					combine(i - 1, i);
					change = true;
					break;
				}
			}
			
		}
		
		return table;
	}
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new File("squeezebox.dat"));
		
		String line  = sc.nextLine() + " " + sc.nextLine();
		
		while(true)
		{	
			String[] split = line.split(" ");
			
			LinkedList<Node> solution = solve(split);
			
			displaySolution(solution);
			
			line = sc.nextLine();

			if(line.equals("#")) break;
			
			line += " " + sc.nextLine();
		}
		
		sc.close();

	}

}
