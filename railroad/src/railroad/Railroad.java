package railroad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/*
 * greedy approach to this problem: Look at top in stack and see if
   it is the desired number
   
 * relatively efficient algorithm with O(n) complexity
 * ArrayStack works in O(1) time complexity with O(n) memory cmpx.
 
 * Why shouldn't worry about illegal state exceptions? because only
   pop if # is desired and stack size is != 0
 */

public class Railroad {

	
	String solve(int[] numbers, int N)
	{
		ArrayStack<Integer> stack = new ArrayStack<Integer>();

		//complete one extra iteration for N due to ordering of code
		for(int i = 0, coach = 1; i < numbers.length && coach <= N + 1;)
		{
			int wanted = numbers[i];
			//check if wanted = top
			//shortcut behavior
			System.out.println(stack.toStr());
			
			if(stack.size() > 0 && stack.peek() == wanted)
			{
				stack.pop();
				i++;
				continue;
			}
			//add
			stack.push(coach);
			coach++;
		}
		if(!stack.isEmpty())
			return "No";
		
		return "Yes";
	}

	public static void main(String[] args) throws IOException{
		
		Scanner sc = new Scanner(new File("railroad.dat"));
		FileWriter writer = new FileWriter(new File("railroad.out"));
		
		var RObj = new Railroad(); 
		
		//read until double null termination
		while(true)
		{
			//get num of coaches
			int kNumCoaches = Integer.parseInt(sc.nextLine());

			if(kNumCoaches == 0) //has to be double 0
				break;
				
			
			//for each permutation under certain coach
			while(true)
			{
				String[] perm = sc.nextLine().split(" ");

				if(perm[0].equals("0"))
				{
					writer.write("\n");
					break;
				}
				
				//convert to integer
				int[] split = new int[perm.length];
				
				for(int i = 0; i < perm.length; i++)
					split[i] = Integer.parseInt(perm[i]);
				
				//solve
				String result = RObj.solve(split, kNumCoaches);
				writer.write(result + '\n');
			}
			
			
			
		}
		
		
		//close
		//writer.flush();
		writer.close();
		sc.close();

	}

}
