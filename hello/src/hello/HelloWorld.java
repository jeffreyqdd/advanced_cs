package hello;


public class HelloWorld { //class

	String s = new String("hello"); //property
	
	public static void main(String[] args) { //method
		System.out.println("Hello World."); //statement
		
		
		var x = new HelloWorld(); //object
		
		x.tacos();
		
		
		//Scanner scanner = new Scanner(System.in); 
	}
	
	public void tacos()
	{
		System.out.println("Tacos are good for you.");
	}

	//comment
	//class - contains everything, has members
	//methods - do something
	//statements - tell cpu to do something
	//properties - store something
	//access modifier (public, private)
	//object - instance of a class
	//args - what you pass to a method
	//parameter - variable that stores the data in a method
}
