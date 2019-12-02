import java.util.Scanner;
import java.io.*;
import java.lang.System;
import java.util.ArrayList;


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
		
		/*HashBucket hb = new HashBucket();
		
		hb.put(new Entry(123, "joe"));
		hb.put(new Entry(22, "bob"));
		
		
		hb.put(new Entry(25, "bob"));
		hb.put(new Entry(25, "bob"));
		
		hb.remove(new Entry(25, "bob"));
		
		System.out.println(hb);
		*/
		
		
		runSimulation();
	}
	
	
	public static void runSimulation() throws IOException
	{
		//create a hash table of size htSize
		double[] loadF = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0};
		
		
		double[] graphLoadF = new double[10];
		double[] graphAvgLen = new double[10];
		double[] graphMaxLen = new double[10];
		double[] graphInsertionT = new double[10];
		
		double[] graphSuccSearchT = new double[10];
		double[] graphUnsuccSearchT = new double[10];
		
		
		
		int index = 0;
		for(double n : loadF)
		{
			
			int arrSize = (int) (50000.0 / n);
			
			long start, stop;
			HashBucket ht = new HashBucket(arrSize);
			
			
			
			//start the put time
			start = System.currentTimeMillis();
			for (Entry e : entries)
				ht.put(e);
			stop = System.currentTimeMillis();
			
			graphLoadF[index] = n;
			graphAvgLen[index] = ht.avgLen();
			graphMaxLen[index] = ht.maxLen();
			graphInsertionT[index] = stop - start;
			
			
			
			
			//start the succ search time
			start = System.currentTimeMillis();
			for (Entry e : successful)
				ht.get(e.getKey());
			stop = System.currentTimeMillis();
			
			graphSuccSearchT[index] = stop - start;
			
			
			//start the unsucc search time
			start = System.currentTimeMillis();
			for (Entry e : unsuccessful)
				ht.get(e.getKey());
			stop = System.currentTimeMillis();
			
			graphUnsuccSearchT[index] = stop - start;

			index++;
		}
		/*
		
		//write
		FileWriter wrCSV = new FileWriter(new File("RandomCollisionAvoidanceUSINGBUCKETS.csv"));
		
		
		
		//write insertion
		wrCSV.write("Insertion\n");
		wrCSV.write("Load Factor,Insertion Time(ms)\n");
		for(int i = 0; i < 10; i++)
			wrCSV.write(graphLoadF[i] + "," +graphInsertionT[i] + "\n");
		wrCSV.write("\n\n\n");
		
		wrCSV.write("Load Factor,Average Bucket Length\n");
		for(int i = 0; i < 10; i++)
			wrCSV.write(graphLoadF[i] + "," +graphAvgLen[i] + "\n");
		wrCSV.write("\n\n\n");
		
		wrCSV.write("Load Factor,Max Bucket Length\n");
		for(int i = 0; i < 10; i++)
			wrCSV.write(graphLoadF[i] + "," +graphMaxLen[i] + "\n");
		wrCSV.write("\n\n\n");
		
		
		wrCSV.write("Successful Searches\n");
		wrCSV.write("Load Factor,Searching Time(ms)\n");
		for(int i = 0; i < 10; i++)
			wrCSV.write(graphLoadF[i] + "," +graphSuccSearchT[i] + "\n");
		wrCSV.write("\n\n\n");
		
		wrCSV.write("Unsuccessful Searches\n");
		wrCSV.write("Load Factor,Searching Time(ms)\n");
		for(int i = 0; i < 10; i++)
			wrCSV.write(graphLoadF[i] + "," +graphUnsuccSearchT[i] + "\n");
		wrCSV.write("\n\n\n");
		
		
		
		wrCSV.close();*/
	}
	
	
	
}
