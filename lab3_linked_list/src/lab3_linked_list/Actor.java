package lab3_linked_list;

class Actor {

	private String m_name;
	private Actor m_next;
	
	Actor(String name)
	{
		m_name = name;
		m_next = null;
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
		String ouput = m_name;
		
		Actor ptr = m_next;
		while(ptr.getNextPtr() != null)
		{
			ouput += "," +
		}
}

