package rpn_calc;

public class ArrayStack implements IStack {

	public double arrStack[];
	int pointer; //initialize to -1 b/c there's no item in the stack
	int size;
	
	//default
	ArrayStack()
	{
		arrStack = new double[10];
		pointer = -1;
		size = 0;
		
		System.out.println("successfully started program");
	}
	
	
	@Override
	public void push(double value) {
		// TODO: push value onto front of the stack, if full, does nothing
		arrStack[size] = value;
	}

	@Override
	public double pop() {
		// TODO return first value at the front of the stack and remove it.
		// TODO if there are not items, return 0.
				
		
		return 0;
	}

	@Override
	public double peek() {
		// TODO verify if there is a valid value. Returns that value, returns 0 otherwise.
		//
		
		return 0;
	}

	@Override
	public int size() {
		// TODO returns the size of the stack
		return 0;
	}

	@Override
	public boolean is_empty() {
		// TODO returns true if stack is empty
		return false;
	}

	@Override
	public boolean is_full() {
		// TODO returns true if stack is full
		return false;
	}

	@Override
	public void clear() {
		// TODO clear the stack

	}

	@Override
	public String to_string() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String array_to_string() {
		// TODO return a string of the array
		return null;
	}

}

