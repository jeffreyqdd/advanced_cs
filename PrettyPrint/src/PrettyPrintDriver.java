import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PrettyPrintDriver
{
	public static void main(String[] args) throws IOException
	{

		
		Scanner sc = new Scanner(new File("pretty.dat"));
		
		while(sc.hasNextLine())
		{
			String[] strArr = sc.nextLine().split(", ");
			
			int[] intArr = new int[strArr.length];
			
			for(int i = 0; i < strArr.length; i++)
			{
				intArr[i] = Integer.parseInt(strArr[i]);
			}
			
			BinaryTree bt = new BinaryTree();
			for(int n : intArr)
			{
				bt.addNode(n);
			}
			
			bt.print();
			
		}
	}
}
