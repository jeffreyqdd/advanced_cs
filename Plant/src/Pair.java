
public class Pair implements Comparable<Pair>
{
	public String first;
	public double second;
	
	public Pair(String first, double second)
	{
		this.first = first;
		this.second = second;
	}
	
	
	@Override
	public int compareTo(Pair o2)
	{
		return first.compareTo(o2.first);
	}
}
