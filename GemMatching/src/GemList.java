import java.util.ArrayList;

public class GemList<T>
{
	private Node<T> front;
	private int size;
	
	
	public GemList()
	{
		front = null;
		size = 0;
	}
	public GemList(T data)
	{
		front = new Node<T>(data);
		size = 1;
	}
	public void addFront(T data)
	{
		front = new Node<T>(data, front);
		size++;
	}
	public void addBack(T data)
	{
		if(front == null) addFront(data);
		else
		{
			Node<T> temp = front;
			while(temp.next() != null) temp = temp.next();
			
			temp.setNext(new Node<T>(data, null) );
			size++;
		}

	}
	public T get(int index)
	{
		if(front == null) return null;
		
		Node<T> temp = front;	
		for(int k = 0; k < index; k++) temp = temp.next();
		
		return temp.get();
	}
	public void insertBefore(T gem, int index)
	{
		if(index <= 0) addFront(gem);
		else if(index >= size) addBack(gem);
		else
		{
			//insert
			Node<T> newNode = new Node<T>(gem);
			
			//iterate to one before
			Node<T> ptr= front;
			for(int i = 0; i < index - 1; i++)
				ptr = ptr.next();
			
			newNode.setNext(ptr.next());
			ptr.setNext(newNode);
			
			size++;
			
		}
		
	}
	private class Node<U>
	{
		private Node<U> next;
		private U data;
		
		public Node(U data, Node<U> next)
		{
			this.data = data; this.next = next;
		}
		public Node(U data) {this.data = data; this.next = null;}
		
		public Node<U> next()
		{
			return next;
		}
		@SuppressWarnings("unused")
		public U get()
		{
			return data;
		}
		public void setNext(Node<U> next) {this.next = next;}
	}
	
	public int size()
	{
		return size;
	}
	
	boolean isSame(Gem a, Gem b)
	{
		return a.equals(b);
	}
	public int score()
	{		
		int score = 0;
		
		ArrayList<ArrayList<Gem>> board = new ArrayList<ArrayList<Gem>>();
		
		board.add(new ArrayList<Gem>());
		for(int i = 0, j = 0; i < size; i++)
		{
			Gem curr = (Gem) get(i);
			
			ArrayList<Gem> currArr = board.get(j);
			
			if(currArr.size() == 0)
			{
				currArr.add(curr);
				continue;
			}
			if((currArr.get(currArr.size() - 1).getColor()).equals(curr.getColor()))
			{
				currArr.add(curr);
			}
			else
			{
				ArrayList<Gem> newArr = new ArrayList<Gem>();
				newArr.add(curr);
				board.add(newArr);
				j++;
			}
		}
		
		for(ArrayList<Gem> arr : board)
		{
			int temp = 0;
			for(Gem g : arr)
			{
				temp += g.getPoints();
			}
			score += temp * arr.size();
		}
		
		return score;
	}
	public void draw(double y)
	{
		for(int i = 0; i < size; i++)
		{
			Gem g = (Gem) get(i);
			g.draw(i * 0.07, y);
		}
	}
	public String toString()
	{
		if(size == 0) return "<none>";
		String s = "";
		
		for(int i = 0; i < size; i++)
		{
			Gem g = (Gem) get(i);
			s += g.toString() + ((i < size - 1)? "->" : "");
		}
		return s;
	}
	
	public GemList<T> createCopy()
	{
		GemList<T> g  = new GemList<T>();
		
		for(int i = 0; i < size;  i++)
		{
			g.addBack(get(i));
		}
		
		return g;
	}
	
	/*public static void main(String [] args)
	{
		GemList<Gem> list = new GemList<Gem>();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);

		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		 System.out.println("\n" + list);
    	System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);	
	}*/
}
