package lab3pt2_linked_list;

public class ListTester {
	public static void main(String[] args) {
		
		ActorLinkedList actors = new ActorLinkedList();
		
		
		Actor a = new Actor("bob");
		Actor b = new Actor("joe");
		Actor c = new Actor("phil");
		Actor d = new Actor("karen");
		Actor e = new Actor("bill");
		
		
		actors.add(a);
		actors.add(b);
		actors.add(c);
		actors.add(d);
		actors.add(e);
		
		//System.out.println("here");
		
		for(int i = 0; i < actors.size(); i++)
		{
			System.out.println(actors.get(i).getName());
		}
		
		
		
		System.out.println("Head: " + actors.get(0).getName());
	}
}
