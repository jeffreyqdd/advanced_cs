package confused;


public class ArrayStack<T> implements IStack<T> {

	public Object[] arr;
	int pointer; //initialize to -1 b/c there are no items in the stack
	
	
	ArrayStack() //default
	{
		arr = new Object[200];
		pointer = -1;
	}
	

	
	@Override
	public void push(T value){
		// push value onto front of the stack, if full, does nothing
		
		if(isFull())
			throw new IllegalStateException();
		else
		{
			int s = size();
			arr[s] = value; //fill
			pointer += 1; //update
		}
		
	}


	@Override
	public T pop() {
		// return first value at the front of the stack and remove it.
		// if there are not items, return 0.	
		T value = peek();
		
		//remove item from stack
		if(!isEmpty()) pointer -= 1;
		
		//return
		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peek(){
		//verify if there is a valid value. Returns that value, returns 0 otherwise.
		
		if(isEmpty())
			throw new IllegalStateException();
		
		return (T) arr[pointer];

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
		return size() == arr.length;
	}

	@Override
	public void clear() {
		pointer = -1;
	}

	@Override
	public String toStr() {
		String s = "a";
		for(int i = 0; i <= pointer; i++)
			s += String.valueOf(arr[i]) + " ";
		return s;
	}

	@Override
	public String arrayToString() {
		String s = "a";
		for(int i = 0; i < 10; i++)
			s += String.valueOf(arr[i]) + " ";
		return s;
	}

}