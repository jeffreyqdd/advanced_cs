import java.util.*;
import java.io.*;

public class Open
{
	
	public static HashMap<String, HashSet<String>> clean(HashMap<String, HashSet<String>> map)
	{
		//avoid the concurrent modification exception
		
		HashMap<String, HashSet<String>> ret = new HashMap<>();

		for(String s : map.keySet())
		{
			if(map.get(s).size() <= 1)
			{
				ret.put(s, map.get(s));
			}
		}
		
		return ret;
	}
	
	public static void solve(ArrayList<String> arr)
	{
		//create HashMap
		//Key: person
		//Value: HashSet of projects
		HashMap<String, HashSet<String>> enrollment = new HashMap<>();
		ArrayList<String> allProjects = new ArrayList<>();
		
		String currPerson = "";
		String currProject = "";
		
		for(String item : arr)
		{
			//check caps via ascii
			if(item.charAt(0) < 96)
			{
				currProject = item;
				allProjects.add(currProject);
			}
			else
			{
				//person
				currPerson = item;
				
				//System.out.println("adding " + currPerson + " to " + currProject);
				
				if(enrollment.containsKey(currPerson))
				{
					//student is already in the system
					//System.out.println("	success");
					enrollment.get(currPerson).add(currProject);
				}
				else
				{
					//System.out.println("	new profile");
					//new student, create empty hashset
					enrollment.put(currPerson, new HashSet<>());
					enrollment.get(currPerson).add(currProject);
				}
			}
		}
		
		//clean out students who have enrolled in more than one project
		enrollment = clean(enrollment);
		
		//for(String key : enrollment.keySet())
			//System.out.println(key + " " + enrollment.get(key) );
		
		//hashmap
		//key: project
		//value: num of signups
		//students are now unique
		HashMap<String, Integer> signups = new HashMap<>();
		
		//add all projects
		for(String project : allProjects)
		{
			signups.put(project, 0);
		}
		
		for(String student : enrollment.keySet())
		{
			//we know there is only one project in each;
			String project = enrollment.get(student).toString();
			//trim project
			project = project.substring(1, project.length() - 1);
			
			if(signups.containsKey(project))
				signups.put(project, signups.get(project) + 1);
			else
				signups.put(project, 1);
		}
		
		//put into pair
		
		ArrayList<Pair> ret = new ArrayList<>();
		for(String key : signups.keySet())
		{
			int value = signups.get(key);
			
			ret.add(new Pair(key, value));
		}
		
		Collections.sort(ret);
		
		for (Pair p : ret)
		{
				System.out.println(p.first + " " + p.second);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("open.dat"));
		
		ArrayList<String> arr = new ArrayList<>();
		
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			
			if(line.equals("1"))
			{
				//System.out.println("entering: "+ arr);
				solve(arr);
				System.out.println();
				arr.clear();
				continue;
			}
			if(line.equals("0"))
			{
				break;
			}
			arr.add(line);
			
		}
		
	}
}
