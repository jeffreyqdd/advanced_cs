import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.lang.System;


public class DriverClass
{
	
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
		
		//System.out.println(entries.get(0).getKey().hashCode());
		
		
		runSimulation();
	}
	
	
	public static void runSimulation()
	{
		//create a hash table of size htSize
		int[] sizes = {5000000, 1000000, 625000, 555555, 500000};
		
		//for(int n : sizes)
		//{
			long start, stop;
			HashTable ht = new HashTable(1000000);
			
			//start the put time
			start = System.currentTimeMillis();
			for (Entry e : entries)
				ht.put(e.getKey(), e.getValue());
			stop = System.currentTimeMillis();
			long insertProbes = ht.probes;
			ht.probes = 0;
			long timeToLoad = stop - start;
		
		//SARDC81ITQW968387
			
			System.out.println(successful.get(0).getKey());
			System.out.println(successful.get(0).getValue());
			System.out.println(ht);
			
			//System.out.println(ht.get());
			
			//start the succ search time
			/*start = System.currentTimeMillis();
			for (Entry e : unsuccesesful)
				ht.get(e.getKey());
			stop = System.currentTimeMillis();
			long timeToSuccSearch = stop - start;
			long successfulProbe = ht.probes;
			ht.probes = 0;
			
			//start the unsucc search time
			start = System.currentTimeMillis();
			for (Entry e : unsuccesesful)
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
							"Avg Probes: " + unsuccessfulProbe / unsuccesesful.size());
			
			System.out.println("\n\n\n");*/
			
		//}
		
		
	}
	
}
