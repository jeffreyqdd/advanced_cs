public class Car
{
	int year;
	String make;
	String origin;
	
	public Car()
	{
		year = 0;
		make = null;
		origin = null;
	}
	
	public Car(int year, String make, String origin)
	{
		this.year = year;
		this.make = make;
		this.origin = origin;
	}
	
	@Override
	public String toString()
	{
		return year + " " + make + " " + origin;
	}
}
