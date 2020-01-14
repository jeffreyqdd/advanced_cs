import java.util.*;
import java.io.*;

public class Open
{
	public static final int MAX_N = 10000;
	public static int solve(ArrayList<String> arr)
	{
		HashMap<String, String> signedUp = new HashMap<>(MAX_N * 2);
		HashSet<String> seen = new HashSet<>(MAX_N * 2), seenAgain = new HashSet<>(MAX_N * 2);
		HashMap<String, Integer> counts = new HashMap(200);
		
		String CurrentProject = "";
		
		HashSet<String> recentProjectRoll = new HashSet<>(200);
		
		for(String s : arr)
		{
			if(s.charAt(0) > 96)
			{
				//lowercase
				

				if(seen.contains(s) && !recentProjectRoll.contains(s))
					seenAgain.add(s);
				seen.add(s);
				recentProjectRoll.add(s);
				signedUp.put(s, CurrentProject);
			}
			else
			{
				CurrentProject = s;
				counts.put(CurrentProject, 0);
				
				recentProjectRoll.clear();
				
			}
		}
		
		for(String s : seenAgain)
		{
			seen.remove(s);
		}
		
	
		
		for(String s : seen)
		{
			String key = s, value = signedUp.get(s);
			
			if(counts.containsKey(value))
			{
				System.out.println(key + " " + value);
				counts.put(value, counts.get(value) + 1);
			}

		}
		
		for(String key : counts.keySet())
		{
			System.out.println(key + counts.get(key));
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("src/open.dat"));
		
		ArrayList<String> arr = new ArrayList<>();
		
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			
			if(line.equals("1"))
			{
				solve(arr);
				arr.clear();
			}
			if(line.equals("0"))
			{
				break;
			}
			arr.add(line);
			
		}
		
	}
}
