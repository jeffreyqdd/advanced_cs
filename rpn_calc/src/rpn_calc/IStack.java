package rpn_calc;




public interface IStack {
	void push(double value);
	
	double pop();
	
	double peek();
	
	int size();
	
	boolean isEmpty();
	
	public boolean isFull();
	
	void clear();
	
	public String toString();
	
	public String arrayToString();
}
