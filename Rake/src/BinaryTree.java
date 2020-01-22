import java.util.*;

public class BinaryTree
{
	public Node root;
	private static final int COUNT = 3;
	
	private int[] arr;
	private int index;
	private int low = 10000;
	private int high = -10000;
	
	//key: pos, value: height
	private HashMap<Integer, Integer> map = new HashMap<>();
	
	public BinaryTree()
	{
		root = null;
	}
	public BinaryTree(int t)
	{
		root = new Node(t);
	}
	
	public void recurseSolve(Node node, int pos)
	{
		
		if(node == null)
			return;
		//process
		if(map.containsKey(pos))
			map.put(pos, map.get(pos) + node.data);
		else
			map.put(pos, node.data);
		
		low = Math.min(low, pos);
		high = Math.max(high, pos);
		
		recurseSolve(node.left, pos - 1);
		recurseSolve(node.right, pos + 1);
	}
	
	public void leafHeight()
	{
		for(int i = low; i <= high; i++){
			System.out.print(map.get(i));
			if (i == high)
			{
				System.out.println();
			}
			else
			{
				System.out.print(" ");
			}
		}
	}
	
	
	public BinaryTree(int[] arr)
	{
		this.arr = arr;
		index = 0;
		
		
		root = addNode(0);
	}
	
	public Node addNode(int pos)
	{
		//System.out.println("entering pos: " + pos);
		
		if(arr[index] == -1)
		{
			return null;
		}
		
		Node node = new Node(arr[index]);
		
		index++;
		node.left = addNode(pos - 1);
		
		index++;
		node.right = addNode(pos + 1);
		
		
		return node;
	}
	
	public void print()
	{
		printTree(root, 0);
	}
	
	public int getDepth(int value)
	{
		return getDepth(root, value, 1);
	}
	
	private int getDepth(Node node, int data, int level)
	{
		if(node == null) return 0;
		if(node.data == data) return level;
		
		int downLevel = getDepth(node.left, data, level + 1);
		
		if(downLevel != 0)
			return downLevel;
		
		return getDepth(node.right, data, level + 1);
	}
	
	
	private void printTree(Node node, int space)
	{
		// Base case
		if (node == null)
			return;
		
		// Increase distance between levels
		space += COUNT;
		
		// Process right child first
		printTree(node.right, space);
		
		// Print current node after space
		// count
		System.out.print("\n");
		for (int i = COUNT; i < space; i++)
			System.out.print(" ");
		System.out.print(node.data + "\n");
		
		// Process left child
		printTree(node.left, space);
	}

	public class Node
	{
		public int data;
		public Node left;
		public Node right;
		
		public Node(int data)
		{
			this(data, null, null);
		}
		
		public Node(int data, Node left, Node right)
		{
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
}
