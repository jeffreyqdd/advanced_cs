import java.util.*;
import java.io.*;

public class Play
{
	private static int solve(HashMap<Integer, LinkedList<Integer>> field, ArrayList<Integer> knocked) {
		int totalKnocked = 0;
		
		HashSet<Integer> visited = new HashSet<>(); //keep track of visited nodes
		
		//queue, stack, doesn't matter...O(V + E);
		Queue<Integer> q = new LinkedList<>(knocked);
		
		while(!q.isEmpty())
		{
			int currNode = q.remove();
			
			if(visited.contains(currNode)) continue; //check visited
			else visited.add(currNode); //mark visited
			
			//process node
			totalKnocked++;
			
			//search children
			if(field.get(currNode) != null) q.addAll(field.get(currNode));
		}
		
		return totalKnocked;
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("play.dat"));
		int TOTAL_CASES = sc.nextInt();
		
		//solve each case;
		for(int i = 0; i < TOTAL_CASES; i++) {
			HashMap<Integer, LinkedList<Integer>> field = new HashMap<>(); //<domino, children knocked>
			ArrayList<Integer> knocked = new ArrayList<>(); //arraylist of starting nodes
			/* read constraints
			 * n is the number of # of dominos
			 * m is the number of (x,y) pairs that represent connected dominos
			 * l is the number of dominos that are manually knocked down
			 */
			int n = sc.nextInt(), m = sc.nextInt(), l = sc.nextInt();
			//System.out.println(n + " " + m + " " + l);
			for(int pair = 0; pair < m; pair++) { //uni-directional.
				int x = sc.nextInt(), y = sc.nextInt();
				field.computeIfAbsent(x, k -> new LinkedList<>());
				field.get(x).add(y);
			}
			
			for(int start = 0; start < l; start++) {
				knocked.add(sc.nextInt());
			}
			
			System.out.println(solve(field, knocked));

		}
	}
}
