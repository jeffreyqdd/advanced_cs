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
		
		LinkedList<Node> queue = new LinkedList<Node>();
	
		Node startNode = new Node();
		startNode.value = new LinkedList<String>(currP.start);
		startNode.seen = new HashSet<String>();
		
		queue.addFront(startNode);
		
		int cnt = 0;
		while(!queue.isEmpty())
		{

			Node currNode = queue.popBack();
			
			
			cnt += 1;
			if(cnt >= 2000)
			{
				System.out.println(currNode.value.toString() + " --- " +
						  Integer.toString(queue.size()));
				cnt= 0;
			}
			
			//checl base cases
			if(currNode.value.size() > ladderLength) continue;
			if(currNode.value.size() == ladderLength &&
				currNode.value.getFront().equals(currP.end))
			{
				System.out.println("found solution");
				solution.add(currNode.value);
				continue;
			}
			
			
			if(currNode.value.size() == ladderLength) continue;
			
			LinkedList<String> nodeList = currNode.value;
			HashSet<String> nodeSeen = currNode.seen;
			ArrayList<String> children = generateChildren(nodeList.getFront(), dictionary);
			
			for(String child : children)
			{
			
			
				if(nodeSeen.contains(child)) continue;
				
				Node newNode = new Node();
				
				newNode.seen = new HashSet<String>(nodeSeen);
				newNode.value = new LinkedList<String>();
				
				for(int i = nodeList.size() - 1; i >= 0; i--)
					newNode.value.addFront(nodeList.get(i));

				//System.out.println("ree");
				newNode.value.addFront(child);
				newNode.seen.add(child);
				
				queue.addFront(newNode);
			}
			
			
		}
		
		return solution;
	}

	public static void main(String[] args) throws IOException{
		//read
		FileReader betterScanner = new FileReader();
		
		ArrayList<Pair> queries = betterScanner.readPair("input.txt");
		HashSet<String> dictionary = betterScanner.readSet("dict.txt");
		

		
		ArrayList<LinkedList<String>> test = solve(new Pair("sail","ruin"), dictionary,5);
		System.out.println("------");
		System.out.println(test.size());
	}

}
