import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class HashTableDriver
{
	public static void main(String[] args)
	{
		HashTable table = new HashTable(17);
		

		
		// collisions - 1 probe
		table.put(48235250, "KENNITH GRASSMYER");
		table.put(31850991, "WANETA DEWEES");
		
		System.out.println(table + " " + table.probes);
		System.out.println(table + " " + table.probes);
		
		// insertions - no collisions
		/*table.put(25428367, "DUSTY BANNON");
		table.put(24248685, "FRANCE COELLO");
		table.put(23331143, "JUSTIN ADKIN");
		
		// collisions - multiple probes
		table.put(68682774, "MALIK TULLER");
		table.put(59245514, "LESLEE PHIFER");
		
		// 2nd put for this key - replace FRANCE COELLO
		// simple overwrite, no collisions
		table.put(24248685, "ISAAC GENEY");
		// remove DUSTY BANNON, simple, no collisions
		table.remove(25428367);
		// remove MALIK TULLER, collisions & invalid entry
		table.remove(68682774);
		// collisions  & invalid entry
		table.put(54657809, "MARTY ENOCHS");
		// remove JUSTIN ADKIN, simple, no collisions
		table.remove(23331143);
		
		// 2nd put for this key - replace LESLEE PHIFER
		// collisions, overwrite invalid entry, &
		// invalidate original entry
		table.put(59245514, "GENARO QUIDER");*/
	}
	
	/*
	public static void main(String[] args) throws IOException
	{
		Scanner sc1 = new Scanner(new File("chonk/Large Data Set.txt"));
		Scanner sc2 = new Scanner(new File("chonk/Successful Search.txt"));
		Scanner sc3 = new Scanner(new File("chonk/Unsuccessful Search.txt"));
		
		//create records to store file.
		ArrayList<Entry> records = new ArrayList<>();
		ArrayList<Entry> successfulSearch = new ArrayList<>();
		ArrayList<Entry> unsuccessfulSeach = new ArrayList<>();
		
		while(sc1.hasNextLine()) //read everything
		{
			String[] split = sc1.nextLine().split(" ");
			records.add(new Entry(Integer.parseInt(split[0]),
					split[1] + " " + split[2]));
		}
		while(sc2.hasNextLine()) //read everything
		{
			String[] split = sc2.nextLine().split(" ");
			successfulSearch.add(new Entry(Integer.parseInt(split[0]),
					split[1] + " " + split[2]));
		}
		while(sc3.hasNextLine()) //read everything
		{
			String[] split = sc3.nextLine().split(" ");
			unsuccessfulSeach.add(new Entry(Integer.parseInt(split[0]),
					split[1] + " " + split[2]));
		}
		
		
		
		//create a hash table of size htSize
		int[] sizes = {5000000, 1000000, 625000, 555555, 500010};
		
		
		for(int n : sizes)
		{
			long start, stop;
			HashTable ht = new HashTable(n);
			
			//start the put time
			start = System.currentTimeMillis();
			for (Entry e : records)
				ht.put(e);
			stop = System.currentTimeMillis();
			long insertProbes = ht.probes;
			ht.probes = 0;
			long timeToLoad = stop - start;
			
			//start the succ search time
			start = System.currentTimeMillis();
			for (Entry e : successfulSearch)
				ht.get(e);
			stop = System.currentTimeMillis();
			long timeToSuccSearch = stop - start;
			long successfulProbe = ht.probes;
			ht.probes = 0;
			
			//start the unsucc search time
			start = System.currentTimeMillis();
			for (Entry e : unsuccessfulSeach)
				ht.get(e);
			stop = System.currentTimeMillis();
			long timeToUnsuccSearch = stop - start;
			long unsuccessfulProbe = ht.probes;
			ht.probes = 0;
			
			System.out.println(
					"INSERTION TIME-------\n" +
							"Load Factor: " + ((double) ht.size() / (double) n) + "\n" +
							"Avg Probes: " + insertProbes / records.size() + "\n" +
							"Time: " + timeToLoad);
			
			System.out.println(
					"SUCCESSFUL SEARCH TIME-------\n" +
							"Time: " + timeToSuccSearch + '\n' +
							"Avg Probes: " + successfulProbe / successfulSearch.size());
			
			System.out.println(
					"UNSUCCESSFUL SEARCH TIME-------\n" +
							"Time: " + timeToUnsuccSearch + '\n' +
							"Avg Probes: " + unsuccessfulProbe / unsuccessfulSeach.size());
		
			System.out.println("\n\n\n");
		
		}
		
		
	}
	
	*/
	
}
