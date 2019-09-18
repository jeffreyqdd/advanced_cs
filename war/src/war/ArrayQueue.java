package war;



public class ArrayQueue <T>{


	Object[] arr;
	int first, last, size;
	
	ArrayQueue(int capacity)
	
	{
		arr = new Object[capacity];
		
		first = 0;
		last = 0;
		size = 0;
	}
	void clear()
	{
		first = 0;
		last = 0;
		size = 0;
	}
	
	// return number of items currently in the buffer	
	int size()
	{
		return size;
	}
	
	int length()
	{
		return arr.length;
	}
	//return true if empty
	boolean isEmpty()
	{
		return size() == 0;
	}
	//return true if full
	boolean isFull()
	{
		return size == arr.length;
	}
	
	//add to queue
	T enqueue(T x)
	{
		
		if(isFull())
			throw new IllegalStateException();
		
		arr[last] = x;
		
		last++;
		size++;
		
		last = last % arr.length;
		

		return x;

		
	}
	
	//pop front
	T dequeue()
	{
		T top = peek();
	
		first++;
		size--;
		
		
		first = first % arr.length;
		
		return top;
	}
	//peek front
	@SuppressWarnings("unchecked")
	T peek()
	{	
		if(isEmpty()) throw new IllegalStateException();
		return (T) arr[first];
	}
	
	//debugging
	String toStr()
	{
		String s = "";
		
		for(int i = first; i < size + first; i++)
		{
			s += String.valueOf(arr[i % length()]) + ", ";
		}
		return s;
	}
}