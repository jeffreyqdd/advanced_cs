import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.lang.System;


public class DriverClass
{
	//it passes all the validation testing.
	/*
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("test/put50.txt"));
		
		HashTable ht = new HashTable();
		while(sc.hasNextLine())
		{
			String[] s = sc.nextLine().split(" ");
			
			Entry e = new Entry(Integer.parseInt(s[2]), s[0] + "- " + s[3] +" " +s[4]);
			ht.put(e);
			
		}
		
		
		sc.close();
		
		sc = new Scanner(new File("test/remove10.txt"));

		while(sc.hasNextLine())
		{
			String[] s = sc.nextLine().split(" ");
			
			Entry e = new Entry(Integer.parseInt(s[2]), s[0] + "- " + s[3] +" " +s[4]);
			ht.remove(e);
			
		}
		
		sc = new Scanner(new File("test/overwrite3.txt"));
		while(sc.hasNextLine())
		{
			String[] s = sc.nextLine().split(" ");
			
			Entry e = new Entry(Integer.parseInt(s[2]), s[0] + "- " + s[3] +" " +s[4]);
			ht.put(e);
			
		}
		
		sc = new Scanner(new File("test/put10again.txt"));
		while(sc.hasNextLine())
		{
			String[] s = sc.nextLine().split(" ");
			
			Entry e = new Entry(Integer.parseInt(s[2]), s[0] + "- " + s[3] +" " +s[4]);
			ht.put(e);
			
		}
		
		System.out.println(ht);
	}
	
	*/
	
	
	public static ArrayList<Entry> entries = new ArrayList<>();
	public static ArrayList<Entry> successful = new ArrayList<>();
	public static ArrayList<Entry> unsuccessful = new ArrayList<>();
	
	public static void main(String[] args) throws IOException
	{

		
		
		Scanner sc1 = new Scanner(new File("data/Large Data Set.txt"));
		Scanner sc2 = new Scanner(new File("data/Successful Search.txt"));
		Scanner sc3 = new Scanner(new File("data/Unsuccessful Search.txt"));
		
		while(sc1.hasNextLine())
			entries.add(BetterScanner.createNextEntry(sc1.nextLine()));
		
		while(sc2.hasNextLine())
			successful.add(BetterScanner.createNextEntry(sc2.nextLine()));
		
		while(sc3.hasNextLine())
			unsuccessful.add(BetterScanner.createNextEntry(sc3.nextLine()));
		
		sc1.close();
		sc2.close();
		sc3.close();
		
		/*for(Entry e : entries)
			System.out.println(e.getKey() + " " + e.hashCode());*/
		
		//FileWriter wr =  new FileWriter(new File("oof.txt"));
		
		//wr.write(ht.toString());

		//wr.close();
		
		//System.out.println(ht);
		//System.out.println(ht.get(successful.get(0)));
		
		runSimulation();
	}
	
	
	public static void runSimulation()
	{
		//create a hash table of size htSize
		int[] sizes = {500000, 100000, 62500, 55555, 50000};
		
		for(int n : sizes)
		{
			long start, stop;
			HashTable_Linear ht = new HashTable_Linear(n);
			
			//start the put time
			start = System.currentTimeMillis();
			for (Entry e : entries)
				ht.put(e);
			stop = System.currentTimeMillis();
			long insertProbes = ht.probes;
			ht.probes = 0;
			long timeToLoad = stop - start;
			
			
			//start the succ search time
			start = System.currentTimeMillis();
			for (Entry e : successful)
				ht.get(e.getKey());
			stop = System.currentTimeMillis();
			long timeToSuccSearch = stop - start;
			long successfulProbe = ht.probes;
			ht.probes = 0;
			
			//start the unsucc search time
			start = System.currentTimeMillis();
			for (Entry e : unsuccessful)
				ht.get(e.getKey());
			stop = System.currentTimeMillis();
			long timeToUnsuccSearch = stop - start;
			long unsuccessfulProbe = ht.probes;
			ht.probes = 0;
			
			System.out.println(
					"INSERTION TIME-------\n" +
							"Load Factor: " + ((double) ht.size() / (double) n) + "\n" +
							"Avg Probes: " + insertProbes / entries.size() + "\n" +
							"Time: " + timeToLoad);
			
			System.out.println(
					"SUCCESSFUL SEARCH TIME-------\n" +
							"Time: " + timeToSuccSearch + '\n' +
							"Avg Probes: " + successfulProbe / successful.size());
			
			System.out.println(
					"UNSUCCESSFUL SEARCH TIME-------\n" +
							"Time: " + timeToUnsuccSearch + '\n' +
							"Avg Probes: " + unsuccessfulProbe / unsuccessful.size());
			
			System.out.println("\n\n\n");
			
		}
		
		
	}
	
}
