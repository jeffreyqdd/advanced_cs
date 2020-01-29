public class Heap
{
	private Node[]  arr;
	private int size;
	
	
	public Heap()
	{
		this(20);
	}
	
	public Heap(int initialSize)
	{
		arr = new Node[initialSize];
		this.size = 0;
		
		arr[0] = new Node();
	}
	
	public void add(Node input)
	{
		if(size == arr.length - 1)
			resize();
		
		size++;
		arr[size] = input;
		
		int i = size;
		while(i != 0 && arr[getParent(i)].milk() > arr[i].milk())
		{
			swap(i, getParent(i));
			i = getParent(i);
		}
	}
	
	public Node removeMin()
	{
		Node ret = arr[1];
		arr[1] = arr[size];
		size--;
		
		
		if(size == 0)
			return ret;
		
		minHeapify(1);
		
		
		return ret;
	}
	
	public String toString()
	{
		return convert();
	}
	
	private String convert()
	{
		StringBuilder sb = new StringBuilder();
	
		
		for(int i = 1, level = 1; i <= size; i++)
		{
			if(i == Math.pow(2, level))
			{
				level++;
				sb.append("\n");
			}
			
			
			sb.append(arr[i] + " ");
		}
		
		
		return sb.toString();
	}
	
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	private void minHeapify(int node)
	{
		int leftChild = getLeftChild(node);
		int rightChild = getRightChild(node);
		
		if(leftChild <= size && arr[leftChild].milk() < arr[node].milk())
		{
			if(rightChild <= size && arr[rightChild].milk() < arr[leftChild].milk())
			{
				swap(node, rightChild);
				minHeapify(rightChild);
			}
			else
			{
				swap(node, leftChild);
				minHeapify(leftChild);
			}
		}
		else if(rightChild <= size && arr[rightChild].milk() < arr[node].milk())
		{
			swap(node, rightChild);
			minHeapify(rightChild);
		}
		
		
	}
	
	private void resize()
	{
		Node[] newArr = new Node[arr.length * 2];
		
		for(int i = 0; i < arr.length; i++)
			newArr[i] = arr[i];
		
		arr = newArr;
		
	}
	
	
	private void swap(int i, int j)
	{
		Node intermediate = arr[j];
		arr[j] = arr[i];
		arr[i] = intermediate;
	}
	private int getParent(int i)
	{
		return i / 2;
	}
	
	private int getLeftChild(int i)
	{
		return (2 * i);
	}
	
	private int getRightChild(int i)
	{
		return (2 * i) + 1;
	}
	
	
	
	
}
