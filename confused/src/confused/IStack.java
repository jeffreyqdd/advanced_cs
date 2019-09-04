package confused;

public interface IStack<T> {

	
	public void push(T value);
	
	public T pop();
	
	public T peek();
	
	public int size();
	
	public boolean isEmpty();
	
	public boolean isFull();
	
	public void clear();
	
	public String toStr();
	
	public String arrayToString();
}
