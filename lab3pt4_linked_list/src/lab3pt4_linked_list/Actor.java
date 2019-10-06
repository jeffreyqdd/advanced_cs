package lab3pt4_linked_list;


 class Actor {

	private String m_name;
	
	Actor() {m_name = null;}
	
	Actor(String name) { m_name = name; }
	
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
	
	public String toString()
	{
		return m_name;
	}
	

}
