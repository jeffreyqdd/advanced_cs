package lab3pt3_linked_list;


public class Node {


	private Object m_data;
	private Node m_next; 
	
	Node()
	{
		m_data = null;
		m_next = null;
	}
	
	Node(Object data)
	{
		m_data = data;
		m_next = null;
	}
	//return name
	Object get() 
	{
		return m_data;
	}
	
	//set name
	void set(Object data)
	{
		m_data = data;
	}
	
	Node getNextPtr()
	{
		return m_next;
	}
	
	void setNextPtr(Node next)
	{
		m_next = next;
	}
	
	public String toString()
	{
		String output = m_data.toString();
		Node ptr = m_next;
		
		while(ptr.getNextPtr() != null)
		{
			output += "," + ptr.get();
			ptr = ptr.getNextPtr();
			
		}
		
		return output;
	}

}
