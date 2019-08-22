package hello;

class ExtraClass{
	public void empty()
	{
		System.out.println("here");
	}
}

public class HelloWorld { //class

	
	String s = new String("hello"); //property
	int n = 5;
	double d = 1.23;
	float f = 1.23f;
	char c = 'c';
	
	
	public static void main(String[] args) { //method
		System.out.println("Hello World."); //statement
		
		
		var x = new HelloWorld(); //object	
		x.tacos();
		
		
		var y = new ExtraClass();
		y.empty();
		
		
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
