import java.util.*;
import java.io.*;

public class Main
{
	//num of test cases
	private static int N;
	
	//create adjacency list for a bi-direction graph
	//Key: current node, Value: ArrayList of children nodes
	private static HashMap<String, LinkedList<String>> nodes = new HashMap<>();
	
	//method that traverses uses a bfs to determine if the said end node is reachable
	//O(V + E) which is more than enough to solve this problem
	private static String bfs(String start, String end) {
		HashSet<String> visited = new HashSet<>(); //checks if the node has been visited
		Queue<String> q = new LinkedList<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			String node = q.remove();
			
			visited.add(node); //mark visited
			
			if(node.equals(end)) return "yes"; //process
			
			if(nodes.get(node) == null) continue; //catches if the entrance doesn't exist
			
			for(String child : nodes.get(node)) { // search children
				if(!visited.contains(child))
					q.add(child);
			}
			
		}
		
		return "no"; //nothing found
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File("scooby.dat"));
		
		N = Integer.parseInt(sc.nextLine());
		
		//solve N cases
		for(int i = 0; i < N; i++)
		{
			//reset
			nodes.clear();
			//read
			String connections = sc.nextLine();
			String[] query = sc.nextLine().replaceAll(" ", "").split("");
			String[] processed = connections.replaceAll(" ","").split("");
			
			//go in pairs of two
			for(int j = 0; j < processed.length - 1; j+= 2) {
				String a = processed[j], b = processed[j + 1];
				nodes.computeIfAbsent(a, k -> new LinkedList<>());
				nodes.computeIfAbsent(b, k -> new LinkedList<>());
				nodes.get(a).add(b);
				nodes.get(b).add(a);
			}
			//System.out.println(nodes);
			//compute
			System.out.println(bfs(query[0], query[1]));
		}
	}
}
