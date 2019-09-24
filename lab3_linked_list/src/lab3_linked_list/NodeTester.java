package lab3_linked_list;

public class NodeTester {

	public static void main(String[] args)
	{
		Actor bob = new Actor("Bob");
		
		Actor joe = new Actor("Joe");
		bob.setNextPtr(joe);
		
		Actor phil = new Actor("Phil");
		joe.setNextPtr(phil);
		
		Actor jebadiah = new Actor("Jebadiah");
		phil.setNextPtr(jebadiah);
		
		Actor karen = new Actor("Karen");
		jebadiah.setNextPtr(karen);
		
		
		//print ---
		System.out.println("Printing--------");
		Actor ptr = bob;
		
		while(ptr.getNextPtr() != null)
		{
			System.out.println(ptr.getName());
			ptr = ptr.getNextPtr();
		} 
		System.out.println(ptr.getName());
		
		
		System.out.println("\nTo string method --------");
		System.out.println(bob.toString());
		
	}
}
