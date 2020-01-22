public class Pair implements Comparable<Pair>
{
	String first; int second;
	
	public Pair(String first, int second)
	{
		this.first = first;
		this.second = second;
	}
	
	@Override
	public int compareTo(Pair p)
	{
		if(second == p.second)
			return first.compareTo(p.first);
		return p.second - second;
	}
}
