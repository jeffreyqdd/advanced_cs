import java.util.HashSet;
import java.util.ArrayList;
import java.io.IOException;

//bc I can..static classes
public class ExtraCredit {
	
	
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
	
	
	
	public static ArrayList<LinkedList<String>> solve(Pair currP,
																										HashSet<String> dictionary,
																										int ladderLength){
	
		ArrayList<LinkedList<String>> solution = new ArrayList<LinkedList<String>>();
		
		LinkedList<LinkedList<String>> queue = new LinkedList<LinkedList<String>>();
		HashSet<String> seen= new HashSet<String>();
		
		queue.addFront(new LinkedList<String>(currP.start));
		
		
		while(!queue.isEmpty())
		{
		}
		
		return solution;
	}

	public static void main(String[] args) throws IOException{
		//read
		FileReader betterScanner = new FileReader();
		
		ArrayList<Pair> queries = betterScanner.readPair("input.txt");
		HashSet<String> dictionary = betterScanner.readSet("dict.txt");
		

		
		ArrayList<LinkedList<String>> test = solve(new Pair("sail","ruin"), dictionary, 5);
		System.out.println("------");
		System.out.println(test.size());
	}

}
