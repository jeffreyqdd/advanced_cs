import java.util.*;
import java.io.*;

public class FileReader {

	Scanner sc = null;
	
	public FileReader() {}

	
	public HashSet<String> readSet(String inFile) throws IOException
	{
		sc = new Scanner(new File(inFile));
		
		HashSet<String> ret = new HashSet<String>();
		
		while(sc.hasNextLine())
			ret.add(sc.nextLine());
		
		sc.close();
		
		return ret;
	}
	
	public ArrayList<Pair> readPair(String inFile) throws IOException
	{
		sc = new Scanner(new File(inFile));
		
		ArrayList<Pair> ret = new ArrayList<Pair>();
		
		while(sc.hasNextLine())
		{
			String s = sc.nextLine();
			String split[] = s.split(" ");
			
			ret.add(new Pair(split));
		}
		
		return ret;
	}

}
