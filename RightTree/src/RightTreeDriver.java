import java.util.*;
import java.io.*;

public class RightTreeDriver
{
	
	public static String solve(String input, int n)
	{
		BinaryTree bt = new BinaryTree(input);
		
		//System.out.println("solving: " + input);
		//System.out.println(bt.isRightSideTree());
		
		return bt.isRightSideTree() ? ("Tree " + n + " is a right-tree.")
				:
				("Tree " + n + " is not a right-tree.");
	}
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner(new File("righttree.dat"));
		
		int N = Integer.parseInt(sc.nextLine());
		
		String arr[] = new String[N];
		
		for(int i = 0; i < N; i++)
			arr[i] = sc.nextLine();
		
		sc.close();
		
		
		for(int i = 0; i < arr.length; i++)
		{
			System.out.println(solve(arr[i], i + 1));
		}

	}
}
