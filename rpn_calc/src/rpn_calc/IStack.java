package rpn_calc;


public interface IStack {
	void push(double value);
	
	double pop();
	
	double peek();
	
	int size();
	
	boolean is_empty();
	
	public boolean is_full();
	
	void clear();
	
	public String to_string();
	
	public String array_to_string();
}
