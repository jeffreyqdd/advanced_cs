package war;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

class War {

	public static final int MAX_TURN = 100000;
	
	
	ArrayQueue<String> q1;
	ArrayQueue<String> q2;
	String result;
	
	War() {
		q1 = new ArrayQueue<String>(52); //p1 deck
		q2 = new ArrayQueue<String>(52); //p2 deck
		result = "";
		
	}
	
	//turns strings of card vals to integer...essentially a hash
	int convert(String s)
	{
		String[] card =
		{
				"2c", "3c", "4c", "5c", "6c", "7c", "8c", "9c", "Tc", "Jc", "Qc", "Kc", "Ac",
				"2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "Ts", "Js", "Qs", "Ks", "As",
				"2d", "3d", "4d", "5d", "6d", "7d", "8d", "9d", "Td", "Jd", "Qd", "Kd", "Ad",
				"2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "Th", "Jh", "Qh", "Kh", "Ah"		
		};
		
		for(int i = 0; i < card.length; i++)
		{
			if(card[i].equals(s))
				return i % 13;
		}

		//mod by 13 since suits don't matter.
		
		return -1;
	}

	//runs the game
	String run_simulation()
	{
		ArrayQueue<String> pile = new ArrayQueue<String>(56);
		
		
		for(int turn = 0; turn < MAX_TURN; turn++)
		{
			
			if(q1.isEmpty()) return "P2 Wins!";
			else if(q2.isEmpty()) return "P1 Wins";
			
			
			String q1Card = q1.dequeue(), q2Card = q2.dequeue();
			
			//add to pile
			pile.enqueue(q1Card);
			pile.enqueue(q2Card);
			

			
			//if same..burn 1 card, compare next
			while(convert(q1Card) == convert(q2Card))
			{
				
				if(q1.isEmpty()) return "P2 Wins!";
				else if(q2.isEmpty()) return "P1 Wins";
				
				q1Card = q1.dequeue();
				q2Card = q2.dequeue();
				
				pile.enqueue(q1Card);
				pile.enqueue(q2Card);
				
				
				if(q1.isEmpty()) return "P2 Wins!";
				else if(q2.isEmpty()) return "P1 Wins";
				
				q1Card = q1.dequeue();
				q2Card = q2.dequeue();
				
				pile.enqueue(q1Card);
				pile.enqueue(q2Card);
				
				
			}
			
			//give
			if(convert(q1Card) > convert(q2Card))
			{
				//give to p1
				int size = pile.size();
				
				for(int i = 0; i < size; i++)
					q1.enqueue(pile.dequeue());
				
			}
			else
			{
				int size = pile.size();
				
				for(int i = 0; i < size; i++)
					q2.enqueue(pile.dequeue());
			}
			
		}
		
		
	
		
		//after MAX_TURN 
		return "The game stopped at " + Integer.toString(MAX_TURN) + " plays.";
	}
	
	public static void main(String args[]) throws IOException
	{
		
		
		//read --------------------------------------------------------
		String inFile = "war.dat", outFile = "war.out";
		
		Scanner sc =  new Scanner( new File(inFile));
		Writer wr = new FileWriter(new File(outFile));
		
		while(sc.hasNextLine()) //assume has 2 lines
		{
			String p1Hand = sc.nextLine();
			String p2Hand = sc.nextLine();
			
			String[] p1HandSplit = p1Hand.split(" ");
			String[] p2HandSplit = p2Hand.split(" ");
			
			var Game = new War(); //implies that game is reset
			
			for(int i = 0; i < p1HandSplit.length; i++)
			{
				Game.q1.enqueue(p1HandSplit[i]);
				Game.q2.enqueue(p2HandSplit[i]);
			}
			
			
			
			
			//System.out.println(Game.q1.toStr());
			//System.out.println(Game.q2.toStr());
			//System.out.println();
			
			String result = Game.run_simulation();
			
			System.out.println(result);
		}
		
		
		
		
		
		
		wr.close();
		sc.close();
	}

}
