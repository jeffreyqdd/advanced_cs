public class Pair implements Comparable
{
	String first; int second;
	
	public Pair(String first, int second)
	{
		this.first = first;
		this.second = second;
	}
	
	@Override
	public int compareTo(Object o)
	{
		return 0;
	}
}
