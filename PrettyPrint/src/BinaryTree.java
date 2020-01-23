import java.util.*;

public class BinaryTree
{
	public Node root;
	private static final int COUNT = 3;
	
	public BinaryTree()
	{
		root = null;
	}
	public BinaryTree(int t)
	{
		root = new Node(t);
	}

	
	public void addNode(int val)
	{
		Node nNode = new Node(val);
		
		if (root == null) root = nNode;
		else
		{
			Node tNode = root;
			while (tNode != null)
			{
				if (tNode.data > val)
				{
					if (tNode.left == null)
					{
						tNode.left = nNode;
						break;
					}
					
					tNode = tNode.left;
					
				}
				else
				{
					if (tNode.right == null)
					{
						tNode.right = nNode;
						break;
					}
					tNode = tNode.right;
				}
			}
		}
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
		
		space += COUNT;
		
		printTree(node.right, space);
		
		for (int i = COUNT; i < space; i++)
			System.out.print(" ");
		System.out.print(node.data + "\n");
		
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
