package rpn_calc;


import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class RpnCalculator
{
	ArrayStack<Double> stack;
	
	RpnCalculator() //default
	{
		stack = new ArrayStack<Double>();
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
				num1 = stack.pop();
				num2 = stack.pop();
				
				//check if / 0
				if(num1 == 0)
					throw new Exception("Error: attempt to divide by 0");
				
				stack.push(num2 / num1);
				break;
			
			case "*":
				num1 = stack.pop();
				num2 = stack.pop();
				
				stack.push(num2 * num1);
				
				break;
				
			case "+":
				num1 = stack.pop();
				num2 = stack.pop();
				
				stack.push(num2 + num1);
				
				break;
				
			case "-":
				num1 = stack.pop();
				num2 = stack.pop();
				
				stack.push(num2 - num1);
				
				break;
				
			default:
				stack.push(Double.parseDouble(s));
				
				break;
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return true;
		}
		
		return false;
	}
	
	
	public static void main(String args[]) throws Exception
	{	
		
		
		//instance
		var calculator = new RpnCalculator();
		

		
		Scanner sc = new Scanner(new File("expressions.txt"));
		FileWriter writer = new FileWriter(new File("output.txt"));
			
		int kItems = Integer.parseInt(sc.nextLine());
		for(int items = 0; items < kItems; items++) //for each line 
		{
			//read line
	
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
				writer.write("Invalid\n");
			else if(calculator.stack.size() != 1)
				writer.write("Invalid: Numbers are still left in the stack\n");

			else //valid 
			{
				double d = calculator.stack.pop();
				System.out.println("Valid"); 
				writer.write(Double.toString(d) + '\n');
			}
			//reset
			calculator.stack.clear();
				
		}
		
		sc.close();
		writer.close();
	}
}