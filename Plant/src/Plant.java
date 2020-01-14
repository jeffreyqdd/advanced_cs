import java.util.*;
import java.io.*;

public class Plant
{
	
	public static final int MAX_TREES = 1000000;
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("src/plant.dat"));
		
		int numTestCases = Integer.parseInt(sc.nextLine());
		
		HashMap<String, Integer> mapFreq = new HashMap<>(MAX_TREES * 2);
		
		
		int count = 0;
		int currentCase = 1;
		
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			
			if(line.length() == 0)
			{
				//System.out.println(mapFreq);
				//System.out.println(count);
				ArrayList<Pair> calculated = new ArrayList<>();
				
				for(String key : mapFreq.keySet())
				{
					int value = mapFreq.get(key);
					
					calculated.add(new Pair(key, ((double) value / (double) count) * 100) );
				}
				
				Collections.sort(calculated);
				
				for (Pair p : calculated)
				{
					System.out.println(p.first + " " + Math.round(p.second * 10000.0) / 10000.0);
				}
				
				count = 0;
				if(currentCase == numTestCases)
					break;
				currentCase++;
				mapFreq.clear();
			}
			
			
			if(mapFreq.containsKey(line))
			{
				mapFreq.put(line, mapFreq.get(line) + 1);
			}
			else
			{
				mapFreq.put(line, 1);
			}
			count++;
		}
		
		//System.out.println(mapFreq);
		//System.out.println(count);
		ArrayList<Pair> calculated = new ArrayList<>();
		
		for(String key : mapFreq.keySet())
		{
			int value = mapFreq.get(key);
			
			calculated.add(new Pair(key, ((double) value / (double) count) * 100) );
		}
		
		Collections.sort(calculated);
		
		for (Pair p : calculated)
		{
			System.out.println(p.first + " " + Math.round(p.second * 10000.0) / 10000.0);
		}
		mapFreq.clear();
	}
	
}
