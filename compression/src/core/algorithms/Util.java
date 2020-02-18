package core.algorithms;

import java.io.File;
import java.util.Scanner;

public class Util
{
	public static String readFile(String fileName) throws Exception
	{
	
		Scanner fileSc = new Scanner(new File(fileName));
		
		StringBuilder conc = new StringBuilder();

		//System.out.println(fileName);

		while(fileSc.hasNextLine())
		{
			String s = fileSc.nextLine();
			conc.append(s);
			conc.append('\n');
		}
		return conc.toString();
	}

	
	
}
