package lab3pt4_linked_list;

public class Movie {
	private String m_title;
	private int m_date;
	LinkedList m_actors;
	LinkedList m_directors;
	
	
	
	
	Movie(){}
	Movie(int date, String title, LinkedList actors, LinkedList directors)
	{
		m_title = title;
		m_date = date;
		m_actors = actors;
		m_directors = directors;
	}
	
	
	int getDate()
	{
		return m_date;
	}
	
	void setDate(int date) 
	{
		m_date = date;
	}
	
	String getTitle()
	{
		return m_title;
	}
	
	void setTitle(String title)
	{
		m_title = title;
	}
	
	LinkedList getActors()
	{
		return m_actors;
	}
	
	void setActors(LinkedList actors)
	{
		m_actors = actors;
	}
	
	LinkedList getDirectors()
	{
		return m_directors;
	}
	
	void setDirectors(LinkedList directors)
	{
		m_directors = directors;
	}
	
	public String toString()
	{
		String sDate = Integer.toString(m_date);
		String sTitle = m_title;
		String sActors = "";
		String sDir = "";
		for(int i = 0; i < m_actors.size(); i++)
		{
			Actor casted = (Actor) m_actors.get(i);
			sActors += casted.toString() + ", ";
		}
		
		for(int i = 0; i < m_directors.size(); i++)
		{
			sDir += m_directors.get(i) + ", ";
		}
		
		return sDate + " " + sTitle + " ACTORS: " + sActors + "   DIR: " + sDir;
	 }
	
	
	
}






