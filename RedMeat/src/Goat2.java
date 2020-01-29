import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Goat2
{
	private static int kNum;
	private static int kGoat;
	private static int collisions;
	
	public static void solve(ArrayList<Node> goat)
	{
		
		while(true)
		{
			ArrayList<Node> lowest = new ArrayList<Node>();
			
			for(Node n : goat)
			{
				if(lowest.size() == 0)
				{
					lowest.add(n);
				}
				else if(n.milk() <= lowest.get(0).milk())
				{
					lowest.add(n);
				}
			}
			
			if(lowest.size() == 1)
			{
				goat.remove(lowest.get(0));
				System.out.println("remove");
			}
			
			for(Node n : goat)
				n.nextDay();
			
			
		}
	}
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("goat.dat"));
		
		kNum = Integer.parseInt(sc.nextLine());
		
		
		for(int num = 0; num < kNum; num++)
		{
			kGoat = Integer.parseInt(sc.nextLine());
			
			ArrayList<Node> goats = new ArrayList<>();
			HashMap<ArrayList<Integer>, Integer> seen = new HashMap<>();
			
			for (int i = 0; i < kGoat; i++)
			{
				String[] arr =  sc.nextLine().split(" ");
				
				ArrayList<Integer> arrInt = new ArrayList<>();
				
				for(int j = 1; j < arr.length; j++)
					arrInt.add(Integer.parseInt(arr[j]));
				
				
				goats.add(new Node(arrInt, i));
			}
			
			solve(goats);
		}
	}
}

class Node
{
	int id;
	int cycleCnt;
	ArrayList<Integer> cycle;
	
	Node()
	{
		id = -1;
		cycleCnt = 0;
		
		cycle = new ArrayList<>();
		cycle.add(0);
		
	}
	Node(ArrayList<Integer> cycle, int id)
	{
		this.id = id;
		this.cycle = cycle;
		cycleCnt = 0;
	}
	
	public void nextDay()
	{
		cycleCnt++;
		cycleCnt %= cycle.size();
	}
	
	public int milk()
	{
		return cycle.get(cycleCnt);
	}
	
	
	public String toString()
	{
		return Integer.toString(milk());
	}
}