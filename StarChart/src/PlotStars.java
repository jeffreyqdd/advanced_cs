import datapackage.Coord;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class PlotStars
{
	public static ArrayList<Integer> henryD = new ArrayList<>();
	
	public static ArrayList<Integer> getHenryDraper()
	{
		return henryD;
	}
	
	public static String clean(String s)
	{
		int i;
		for(i = 0; i < s.length(); i++)
		{
			String temporary = s.substring(i, i+1);
			if(!temporary.equals(" "))
				break;
		}
		return s.substring(i);
	}
	
	public static HashBucket[] Scan() throws IOException
	{
		Scanner sc = new Scanner(new File("data/stars.txt"));
		
		HashBucket coordinates = new HashBucket(10000);
		HashBucket magnitudes = new HashBucket(10000);
		HashBucket names = new HashBucket(10000);
		
		while(sc.hasNextLine())
		{
			String[] line = sc.nextLine().split(" ");

			double x = Double.parseDouble(line[0]);
			double y = Double.parseDouble(line[1]);
			double z = Double.parseDouble(line[2]);
			int henryDraperNumber = Integer.parseInt(line[3]);
			double magnitude = Double.parseDouble(line[4]);
			double harvardRevisedNumber = Double.parseDouble(line[5]);
			
			//System.out.println(henryDraperNumber);
			henryD.add(henryDraperNumber);
			
			coordinates.put(henryDraperNumber, new Coord(x,y));
			magnitudes.put(henryDraperNumber, magnitude);
			
			
			
			String conc = "";
			for(int i = 6, cnt = 0; i < line.length; i++, cnt++)
			{
				if (cnt > 0) conc += " ";
				conc += line[i];
			}
			String[] temporary = conc.split(";");
			
			for(String name : temporary)
			{
				names.put(clean(name), henryDraperNumber);
			}
	
		}
		
		HashBucket[] result = {coordinates, magnitudes, names};
		return result;
	}
}
