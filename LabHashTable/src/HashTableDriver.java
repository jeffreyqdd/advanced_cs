import java.util.*;
import java.io.*;

public class HashTableDriver {

	public static void main(String[] args) throws IOException
	{
		//File inFile = new File("Sample Data Part 1/Sample Data Part 1 - Array Size 17.txt");
		File inFile = new File("Sample Data Part 1/Sample Data Part 1 - Array Size 37.txt");
		//File inFile = new File("Sample Data Part 1/Sample Data Part 1 - Array Size 101.txt");
		
		Scanner sc = new Scanner(inFile);
		HashTable table = new HashTable(37);
		
		while(sc.hasNextLine())
		{
			String item = sc.nextLine();
			String[] split = item.split(" ");

			table.put(Integer.parseInt(split[0]),
							   split[1] + " " + split[2]);
		}
		sc.close();

		System.out.println(table);
	}

}

