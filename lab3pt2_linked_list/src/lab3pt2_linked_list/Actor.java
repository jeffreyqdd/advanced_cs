package lab3pt2_linked_list;

class Actor {

	private String m_name;
	private Actor m_next;
	
	Actor(String name)
	{
		this.m_name = name;
		this.m_next = null;
	}
	
	//return name
	String getName() 
	{
		return m_name;
	}
	
	//set name
	void setName(String name)
	{
		m_name = name;
	}
	
	Actor getNextPtr()
	{
		return m_next;
	}
	
	void setNextPtr(Actor next)
	{
		m_next = next;
	}
	
	public String toString()
	{
		String output = m_name;
		Actor ptr = m_next;
		
		while(ptr.getNextPtr() != null)
		{
			output += "," + ptr.getName();
			ptr = ptr.getNextPtr();
			
		}
		
		return output;
	}
}

