package lab3_Guitar_Hero;



public class RingBuffer {


	double[] arr;
	int first, last;
	
	RingBuffer(int capacity)
	
	{
		arr = new double[capacity];
		first = 0;
		last = 0;
	}
	// return number of items currently in the buffer	
	int size()
	{
		return last - first;
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
		return size() == arr.length;
	}
	
	//shifts first back to index 0
	void wrap()
	{
		int newPos, oldPos;
		for(newPos = 0, oldPos = first; oldPos < last; newPos++, oldPos++)
			arr[newPos] = arr[oldPos];
	
		
		first = 0;
		last = newPos;
	}
	//add to queue
	void enqueue(double x)
	{
		if(last >= arr.length && first > 0) {
			wrap();
		}
		
		if(isFull())
			throw new IllegalStateException();
		
		arr[last] = x;
		last++;
		
	

		
	}
	
	//pop front
	double dequeue()
	{
		double top = peek();
		
		//debug only
		//arr[first] = 0;
		
		first++;
		
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

