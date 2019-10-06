package lab3pt4_linked_list;

class Node{
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
	
	Object get()
	{
		return m_data;
	}
	
	Node getNextPtr()
	{
		return m_next;
	}
	
	void setNextPtr(Node next)
	{
		m_next = next;
	}
	
	
}

public class LinkedList {

	private Node m_head;
	private int size;
	
	LinkedList()
	{
		m_head = null;
		size = 0;
	}
	
	LinkedList(Node data)
	{
		m_head = data;
		size = 1;
	}
	
	void add(Object data)
	{
		if(m_head == null)
			m_head = new Node(data);
		else
		{
			Node nNode = new Node(data);
			nNode.setNextPtr(m_head);
			
			m_head = nNode;

		}
		
		size++;
	}
	
	Object get(int index)
	{
		Node ptr = m_head;
		
		if(index > size)
			return null;
			
		
		for(int i = 0; i < index; i++)
			ptr = ptr.getNextPtr();
		
		return ptr.get();
	}
	
	int size()
	{
		return size;
	}
}
