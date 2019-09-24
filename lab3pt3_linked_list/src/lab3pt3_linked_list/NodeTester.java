package lab3pt3_linked_list;

public class NodeTester {

	public static void main(String[] args){
		
		Actor[] actorArr = new Actor[5];
		
		actorArr[0] = new Actor("Jeb");
		actorArr[1] = new Actor("Bob");
		actorArr[2] = new Actor("Phil");
		actorArr[3] = new Actor("Bill");
		actorArr[4] = new Actor("Karen");
		

		Node n = new Node(actorArr[0]);
		for(int i = 1; i < 5; i++)
		{
			Node newHead = new Node(actorArr[i]);
			newHead.setNextPtr(n);
			n = newHead;	
		}
		
		Node currentPtr = n;
		
		for(boolean flag = true; flag; currentPtr = currentPtr.getNextPtr())
		{
			System.out.println(currentPtr.get());
			
			if(currentPtr.getNextPtr() == null) flag = false;
		}
		
		Actor nHead = (Actor) n.get();
		System.out.println("head is:" + nHead.getName());

			
		
		

	}

}
