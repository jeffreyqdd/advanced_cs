package lab3pt2_linked_list;

public class ActorLinkedList {

	Actor m_head;
	int m_count;
	
	ActorLinkedList()
	{
		m_head = null;
		m_count = 0;
	}
	
	//adds an item to the head of the linked list
	void add(Actor actor)
	{
		//empty
		if(m_head == null)
			m_head = actor;
		
		
		Actor newHead = new Actor(actor.getName());
		newHead.setNextPtr(m_head);
		
		m_head = newHead;
		
		//System.out.println(m_head.toString());

		m_count++;
	}
	
	//get the actor at index index
	Actor get(int index)
	{
		Actor result = null;
		
		if(index < m_count)
		{
			result = m_head;
			
			for(int i = 0; i < index; i++)
				result = result.getNextPtr();
		}
		
		
		return result;
	}
	
	//returns # of actors
	int size()
	{
		return m_count;
	}
}
