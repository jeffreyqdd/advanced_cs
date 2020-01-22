import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Rake
{
	public static int count = 1;
	public static void solve(int[] arr)
	{
		BinaryTree bt = new BinaryTree(arr);
		
		//bt.print();
		
		bt.recurseSolve(bt.root, 0);
		
		System.out.println("Case " + count + ":");
		bt.leafHeight();
		System.out.println();
		count++;
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("rake.dat"));
		
		while(true)
		{
			String line = sc.nextLine();
			
			//if at end
			if(line.equals("-1")) break;
			
			String[] split = line.split(" ");
			int[] treeNum = new int[split.length];
			
			for(int i = 0; i < split.length; i++)
			{
				treeNum[i] = Integer.parseInt(split[i]);
			}
			
			solve(treeNum);
		}
		
	}
}
