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

	
	
	public BinaryTree(String s)
	{
		HashSet<Integer> toRemove = new HashSet<>();
		
		root = new Node(1);
		
		Queue<Node> stk = new LinkedList<>();
		stk.add(root);
		
		int cnt = 2;
		while(stk.size() > 0)
		{
			Node curr = stk.remove();
			
			if(cnt > s.length()) break;
			if(s.charAt(cnt - 1) == '0') toRemove.add(cnt);
			curr.left = new Node(cnt);
			stk.add(curr.left);
			cnt++;
			
			
			if(cnt > s.length()) break;
			if(s.charAt(cnt - 1) == '0') toRemove.add(cnt);
			curr.right = new Node(cnt);
			stk.add(curr.right);
			cnt++;
		}
		
		root = remove(root, toRemove);
	}
	
	private Node remove(Node node, HashSet<Integer> id)
	{
		if(node == null) return null;
		
		if(id.contains(node.data))
		{
			return null;
		}
		else
		{
			node.left = remove(node.left, id);
			node.right = remove(node.right, id);
		}
		
		return node;
	
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
	
	public ArrayList<Integer> getLeaves()
	{
		if(root == null)
			return new ArrayList<Integer>();
		
		Stack<Node> stk = new Stack<>();
		stk.add(root);
		
		ArrayList<Integer> leaves = new ArrayList<>();
		
		while(stk.size() > 0)
		{
			Node curr = stk.pop();
			
			if(curr.left != null) stk.add(curr.left);
			if(curr.right != null) stk.add(curr.right);
			
			if(curr.left == null && curr.right == null)
				leaves.add(curr.data);
		}
		
		return leaves;
	}
	public boolean isRightSideTree()
	{
		if(root == null)
			return true;
		
		Stack<Node> stk = new Stack<>();
		stk.add(root);
		
		int ret = 0;
		while(stk.size() > 0)
		{
			Node curr = stk.pop();
			
			//System.out.println("	checking: " + curr.data);
			//System.out.println("		underLeft: " + areaUnder(curr.left));
			//System.out.println("		underRight: " + areaUnder(curr.right));
			
			
			if(curr.left != null) stk.add(curr.left);
			if(curr.right != null) stk.add(curr.right);
			
			ret++;
			
			if(areaUnder(curr.left) > areaUnder(curr.right))
				return false;
		}
		
		return true;
	}
	
	public int areaUnder(Node node)
	{
		if(node == null)
			return 0;
		
		Stack<Node> stk = new Stack<>();
		stk.add(node);
		
		int ret = 0;
		while(stk.size() > 0)
		{
			Node curr = stk.pop();
			
			if(curr.left != null) stk.add(curr.left);
			if(curr.right != null) stk.add(curr.right);
			
			ret++;
		}
		
		return ret;
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
