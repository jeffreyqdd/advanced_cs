package core.algorithms;

import java.io.File;
import java.util.Scanner;

public class Util
{
	public static String readFile(String fileName) throws Exception
	{
	
		Scanner fileSc = new Scanner(new File(fileName));
		
		StringBuilder conc = new StringBuilder();
		
		while(fileSc.hasNextLine())
		{
			conc.append(fileSc.nextLine());
			conc.append('\n');
			
		}
		return conc.toString();
	}

	
	
}
