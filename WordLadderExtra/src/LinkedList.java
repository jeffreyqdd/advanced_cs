
public class LinkedList<T>  {

	private Node<T> front;
	private int size;
	
	
	public LinkedList()
	{
		front = null;
		size = 0;
	}
	public LinkedList(T data)
	{
		front = new Node<T>(data);
		size = 1;
	}

	public void addFront(T data)
	{
		front = new Node<T>(data, front);
		size++;
	}
	public T getFront()
	{
		if(front == null ) return null; //TODO illegal
		return front.get();
	}
	public T popFront()
	{
		T temp = getFront();
		front = front.next();
		size--;
		return temp;
	}

	public void addBack(T data)
	{
		if(front == null) addFront(data);
		else
		{
			Node<T> temp = front;
			while(temp.next() != null) temp = temp.next();
			
			temp.setNext(new Node<T>(data, null) );
			
		}
		size++;
	}
	public T getBack()
	{
		if(front == null) return null; //TODO illegal
		
		Node<T> temp = front;
		while(temp.next() != null) temp = temp.next();
		
		return temp.get();
	}
	public T popBack()
	{
		if(front == null )return null; //TODO illegal
		
		Node<T> temp = front;
		
		if(temp.next() == null)//if only one left
		{
			T ret = temp.get();
			clear();
			return ret;
		}
		
		while(temp.next().next()  != null) temp = temp.next(); //iterate to second last
		
		T ret = temp.next().get(); // return last
		
		temp.setNext(null); //cut off ties and make it a orphan
		
		size--;
		
		return ret;
	}
	
	public void clear()
	{
		front = null;
		size = 0;
	}
	
	public T get(int index) //TODO
	{
		if(front == null) return null; //TODO illegal
		
		Node<T> temp = front;	
		for(int k = 0; k < index; k++) temp = temp.next();
		
		return temp.get();
	}
	int size()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return size == 0;
	}
	public String toString()
	{
		String s = new String();
		
		for(int i = 0; i < size ; i++)
		{
			s += (String) get(i) + " ";
		}
			
		
		return s;
	}
	
	public String rtoString()
	{
		String s = new String();
		
		for(int i = size - 1; i >= 0  ; i--)
		{
			s += (String) get(i) + " ";
		}
			
		
		return s;
	}
	
	
	//NODE CLASS ---------------------------------------------------------------------
	
	private class Node<U>
	{
		public U data;
		public Node<U> next;
		
		public Node(U data, Node<U> next)
		{
			this.data = data; this.next = next;
		}
		public Node(U data) {this.data = data;}
		
		public Node<U> next()
		{
			return next;
		}
		public U get()
		{
			return data;
		}
		public void setNext(Node<U> next) {this.next = next;}
	}
}


