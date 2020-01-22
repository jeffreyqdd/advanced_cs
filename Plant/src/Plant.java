import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class Plant
{
	public static int N;
	
	public static void solve(ArrayList<String> trees)
	{
		//HashMap treeCnt:
		//key: tree name
		//value: quantity
		HashMap<String, Integer> treeCnt = new HashMap<>();
		int totalTrees = 0;
		//cnt
		for(String tree : trees)
		{
			//System.out.println("entering: " + tree);
			//increment trees
			totalTrees++;
			
			//add to treeCnt
			
			if(treeCnt.containsKey(tree))
			{
				//item exists;
				treeCnt.put(tree, treeCnt.get(tree) + 1);
			}
			else
			{
				//item does not exist;
				treeCnt.put(tree, 1);
			}
		}
		
		ArrayList<Pair> treePercent = new ArrayList<>();
		
		for(String key : treeCnt.keySet())
		{
			int value = treeCnt.get(key);
			treePercent.add(new Pair(key, (double) value / (double) totalTrees * 100.0) );
		}
		
		Collections.sort(treePercent);
		
		for (Pair p : treePercent)
		{
			DecimalFormat decFor = new DecimalFormat("0.0000");
			p.second = Math.round(p.second * 10000.0) /10000.0;
			
			String name = p.first;
			String percent = decFor.format(p.second);
			
			System.out.println(name + " " + percent);
		}
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("plant.dat"));
		
		N = Integer.parseInt(sc.nextLine());
		
		//read out the newline
		sc.nextLine();
		
		ArrayList<String> trees = new ArrayList<>();
		
		int cnt = 0;
		while(cnt < N)
		{
			String next = sc.nextLine();
			
			if(next.length() != 0)
				trees.add(next);

			//if at end of test case
			if(next.length() == 0 || !sc.hasNextLine())
			{
				solve(trees);
				System.out.println();
				
				//reset
				trees.clear();
				
				
				//counter
				cnt++;
			}
		}
	}
}
