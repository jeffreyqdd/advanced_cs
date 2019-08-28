package rpn_calc;




public interface IStack<T> {

	
	void push(T value);
	
	T pop();
	
	T peek();
	
	int size();
	
	boolean isEmpty();
	
	public boolean isFull();
	
	void clear();
	
	public String toStr();
	
	public String arrayToString();
}
