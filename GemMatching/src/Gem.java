
import java.awt.Font;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
	
	public String getString()
	{
		return this.toString();
	}
}

public class Gem 
{	
	private GemType type = null;
	private int points = 0;

	Gem(){ //default
		
		switch((int)(Math.random() * ((2 - 0) + 1)))
		{
		case 0:
			this.type =  GemType.GREEN;
			break;
		case 1:
			this.type =  GemType.BLUE;
			break;
		case 2:
			this.type =  GemType.ORANGE;
			break;
		}
		
		this.points = (int)(Math.random() * ((10 - 0) + 0)) * 5;
		
	} 
	Gem(GemType type, int points) //constructor
	{
		this.type = type;
		this.points = points;
	}
	
    // get the type of the gem
	public GemType getType()  
	{
		return type;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	void draw(double x, double y)
	{
		StdDraw.setFont(new Font("Arial", Font.PLAIN, 16));
		StdDraw.setPenColor(StdDraw.WHITE);
		
		String name = null;
		if(type == GemType.GREEN) 		 name = "images/gem_green.png";
	    else if(type == GemType.BLUE)	 name = "images/gem_blue.png";
	    else														 name = "images/gem_orange.png";
		
			
		StdDraw.picture(x,y, name);
		StdDraw.text(x, y, Integer.toString(points));
	}
	
	public String toString()
	{
		return type.getString();
	}
	public String getColor()
	{
		return type.getString();
	}
	/** Tester main method */
	/*public static void main(String [] args)
	{
		final int maxGems = 16;
		
		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());		
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);
		
     	// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}*/
}
