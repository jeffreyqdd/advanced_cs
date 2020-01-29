import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Goat
{
	private static int kNum;
	private static int kGoat;
	private static int collisions;
	
	public static void solve(ArrayList<Node> goats)
	{
		HashSet<Node> currGoat = new HashSet<>();
		
		for(Node n : goats)
			currGoat.add(n);
		System.out.println("made edits");
		int day = 0;
		//System.out.println("collisions: " + collisions);
		while(currGoat.size() > collisions)
		{
			//System.out.println("--------------");
			//add
			Heap heap = new Heap();
			
			for(Node n : currGoat)
				heap.add(n);
			
			//System.out.println(heap);
			
			//remove
			ArrayList<Node> toEat = new ArrayList<>();
			
			toEat.add(heap.removeMin());
			
			while(true)
			{
				Node toAdd = heap.removeMin();
				
				if(toAdd.milk() == toEat.get(0).milk())
					toEat.add(toAdd);
				else
					break;
			}
			
			//eat, update
			if(toEat.size() == 1)
			{
				currGoat.remove(toEat.get(0));
				//System.out.println("ate: " + day);
			}
			
			for(Node n : currGoat)
				n.nextDay();
			
			day++;
		}
		
		System.out.println(collisions + " " + day);
	}
	
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("goat.dat"));
		
		kNum = Integer.parseInt(sc.nextLine());

		
		for(int num = 0; num < kNum; num++)
		{
			kGoat = Integer.parseInt(sc.nextLine());
			collisions = 0;
			
			ArrayList<Node> goats = new ArrayList<>();
			HashMap<ArrayList<Integer>, Integer> seen = new HashMap<>();
			
			for (int i = 0; i < kGoat; i++)
			{
				String[] arr =  sc.nextLine().split(" ");
				
				ArrayList<Integer> arrInt = new ArrayList<>();
				
				for(int j = 1; j < arr.length; j++)
				{
					arrInt.add(Integer.parseInt(arr[j]));
				}
				
				if(seen.containsKey(arrInt))
					seen.put(arrInt, seen.get(arrInt) + 1);
				else
					seen.put(arrInt, 1);
				
				for(ArrayList<Integer> n : seen.keySet())
				{
					if(seen.get(n) > 1) collisions += seen.get(n);
				}
				
				goats.add(new Node(arrInt, i));
			}
			
			solve(goats);
		}
	}
}
