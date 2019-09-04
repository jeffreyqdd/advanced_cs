package confused;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Confused {

	ArrayStack<String> stack;
	public Confused() { //default constructor
		stack = new ArrayStack<String>();
	}


	String solve(String[] split)
	{	
		try
		{
			stack.clear();
			for(int i = 0; i < split.length; i++)
			{
				String item = split[i];
				
				
		
				if(item.equals("(") || item.equals("["))
				{
					System.out.println(item+ ": push");
					stack.push(item);
				}
				//if close pop and check
				if(item.equals(")") || item.equals("]"))
				{
					String prev = stack.pop();
					System.out.println(item+ ": pop");
					//System.out.println(stack.size());
					
					if(prev.equals("(") && item.equals("]") )
						return "No";
					else if(prev.equals("[") && item.equals(")"))
						return "No";

				}
						
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
			return "No";
		}
		
		if(stack.size() > 0)
			return "No";
		return "Yes";
		
	}
	
	public static void main(String[] args) throws IOException{
		Scanner sc = null;
		FileWriter writer = null;
		
		sc = new Scanner(new File("confused.dat"));
		writer = new FileWriter(new File("out.txt"));
		
		var ConfusedObj = new Confused();
		
		//read
		int kItems = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < kItems; i++)
		{
			String expression = sc.nextLine();
			String[] split = expression.split("");
			
			for(int j = 0; j < split.length; j++)
				System.out.print(split[j] + " ");
			System.out.print('\n');
			
			String answer = ConfusedObj.solve(split);
			
			writer.write(answer + "\n");
		}
		
		writer.flush();
		
		sc.close();
		writer.close();

	}
	

}
