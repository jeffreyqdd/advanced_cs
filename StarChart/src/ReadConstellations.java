import datapackage.Pair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadConstellations
{
	public static ArrayList<Pair<String, String>> constellationPairs = new ArrayList<>();
	
	public static void  ScanConstellationFiles() throws IOException
	{
		Scanner sc = new Scanner(new File("data/constellations.txt"));
		

		
		while(sc.hasNextLine())
		{
			String[] line = sc.nextLine().split(",");

			//System.out.println(line[0] + " : "+  line[1]);
			constellationPairs.add(new Pair(line[0], line[1]));
		}
	}
}
