package rpn_calc;

public class ArrayStack implements IStack {

	public double arrStack[];
	
	int pointer; //initialize to -1 b/c there are no items in the stack
	
	
	ArrayStack() //default
	{
		arrStack = new double[10];
		pointer = -1;
	}
	
	
	
	@Override
	public void push(double value) {
		// push value onto front of the stack, if full, does nothing
		
		if(isFull())
		{
			//full
			System.out.println("Warning: Array list is full. Possible loss of data");
		}
		else
		{
			int s = size();
			arrStack[s] = value; //fill
			
			pointer += 1; //update
		}
		
	}

	@Override
	public double pop() {
		// return first value at the front of the stack and remove it.
		// if there are not items, return 0.
				
		double value = peek();
		
		//remove item from stack
		if(!isEmpty()) pointer -= 1;
		
		//return
		return value;
	}

	@Override
	public double peek() {
		//verify if there is a valid value. Returns that value, returns 0 otherwise.
		
		if(!isEmpty()) return arrStack[pointer];
		
		return 0;
	}

	@Override
	public int size() {
		return pointer + 1;
	}

	@Override
	public boolean isEmpty() {
		//returns true if stack is empty
		return size() == 0;
	}

	@Override
	public boolean isFull() {
		//returns true if stack is full
		return size() == 10;
	}

	@Override
	public void clear() {
		pointer = -1;
	}

	@Override
	public String toString() {
		//return the actual array
		String s = new String();
		
		for(int i = 0; i < size(); i++)
		{
			s = s + Double.toString(arrStack[i]) + " ";
		}
		
		return s;
	}

	@Override
	public String arrayToString() {
		//return the entire array
		String s = new String();
		
		//concatenate
		for(int i = 0; i < 10; i++)
		{
			s = s + Double.toString(arrStack[i]) + " ";
		}
		
		return s;
	}

}



