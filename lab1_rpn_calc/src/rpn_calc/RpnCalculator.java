package rpn_calc;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RpnCalculator
{
	ArrayStack stack;
	
	RpnCalculator()
	{
		stack = new ArrayStack();
	}

	
	
	//take string s, may be double, may be operation
	//return true if invalid
	boolean process (String s)
	{
		//simply switch statement for case handling
		try
		{
			double num1, num2;
			switch(s)
			{

			case "/":
				//System.out.println("divide");
				
				//check if enough elements in stack
				if(stack.size() < 2)
					throw new Exception("Error: not enough numbers in stack");
				
				num1 = stack.pop();
				num2 = stack.pop();
				
				//check if / 0
				if(num1 == 0)
					throw new Exception("Error: attempt to divide by 0");
				
				stack.push(num2 / num1);
				break;
			
			case "*":
				//System.out.println("multi");
				
				if(stack.size() < 2)
					throw new Exception("Error: not enough numbers in stack");
				
				num1 = stack.pop();
				num2 = stack.pop();
				
				stack.push(num2 * num1);
				
				break;
				
			case "+":
				//System.out.println("add");
				
				if(stack.size() < 2)
					throw new Exception("Error: not enough numbers in stack");
				
				num1 = stack.pop();
				num2 = stack.pop();
				
				stack.push(num2 + num1);
				
				break;
				
			case "-":
				//System.out.println("sub");
				
				if(stack.size() < 2)
					throw new Exception("Error: not enough numbers in stack");
				
				num1 = stack.pop();
				num2 = stack.pop();
				
				stack.push(num2 - num1);
				
				break;
				
			default:
				//System.out.println("num");
				stack.push(Double.parseDouble(s));
				
				break;
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			return true;
		}
		
		return false;
	}
	
	
	public static void main(String args[])
	{	
		
		
		//instance
		var calculator = new RpnCalculator();
		
		Scanner sc = null;
		FileWriter writer = null; 
		
		
		try
		{
			//try to open file
			sc = new Scanner(new File("expressions.txt"));
			writer = new FileWriter(new File("output.txt"));
			
			int kItems = Integer.parseInt(sc.nextLine());
			for(int items = 0; items < kItems; items++) //for each line 
			{
				//read line\
				String expression = sc.nextLine();
				
				//split line by space
				String[] split = expression.split(" ");
				
				//for each item
				boolean flag = false;
				
				for(int i = 0; i < split.length; i++)
				{
					flag = calculator.process(split[i]);
					
					if(flag)
						break;
				}
				//check if invalid
				if(flag)
				{
					writer.write("Invalid\n");
					//writer.flush();
				}
				else if(calculator.stack.size() != 1)
				{
					writer.write("Invalid: Numbers are still left in the stack\n");
					//writer.flush();
				}
				else
				{
					//pop
					double d = calculator.stack.pop();
					
					//display
					System.out.println("Valid");
					
					//write
					writer.write(Double.toString(d) + '\n');
					//writer.flush();

				}
				
				//clear
				calculator.stack.clear();
				
			}
		
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//close files
			if(sc != null) sc.close();
			if(writer != null)
				try {
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

		}

	}
}