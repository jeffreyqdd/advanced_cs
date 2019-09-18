package lab3_Guitar_Hero;





public class RingBuffer {


	double[] arr;
	int first, last, size;
	
	RingBuffer(int capacity)
	
	{
		arr = new double[capacity];
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
	void enqueue(double x)
	{
		
		if(isFull())
			throw new IllegalStateException();
		
		arr[last] = x;
		
		size++;
		last++;
		
		last = last % arr.length;

	

		
	}
	
	//pop front
	double dequeue()
	{
		double top = peek();
	
		first++;
		size--;
		first = first % arr.length;
		

		
		return top;
	}
	//peek front
	double peek()
	{	
		if(isEmpty()) throw new IllegalStateException();
		return arr[first];
	}
	
	//debugging
	String toStr()
	{
		String s = "";
		
		for(int i = 0; i < arr.length; i++)
		{
			s += Double.toString(arr[i]) + ", ";
		}
		return s;
	}
}