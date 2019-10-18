package squeezebox;

import java.util.*;

public class Node {

	public LinkedList<String> stack;
	
	public Node() {
		stack = null;
	}
	public Node(String data){
		stack = new LinkedList<String>();
		stack.addFirst(data);
	}
	
	public void add(String data) {
		stack.addFirst(data);
	}
	public String getFront() {
		return stack.getFirst();
	}
	
	public String popTop(){
		return stack.removeFirst();
	}
	
	public String toString()
	{
		String s = new String("{");
		
		
		for(int i = 0; i< stack.size(); i++)
		{
			s += stack.get(i) + " ";
		}
		
		s += "}";
		
		
		return s;
	}
	

}
