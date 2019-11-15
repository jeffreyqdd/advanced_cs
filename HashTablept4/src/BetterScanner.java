public class BetterScanner
{
	//take off excess string
	public static String trim(String s)
	{
		
		int i;
		for(i = s.length() - 1; i >= 0 && s.substring(i, i + 1).equals(" "); i--);
		
		return s.substring(0, i + 1);
	}
	
	
	public static Entry createNextEntry(String split)
	{
		//split
		String id = split.substring(0, 16);
		int date = Integer.parseInt(split.substring(18,22));
		String make = split.substring(23,66);
		String country = split.substring(67);
		
		Car car = new Car(date, make, country); // year make origin
		VIN vin = new VIN(id); //create vin class
		
		return new Entry(vin, car);
	}
	
	
}
