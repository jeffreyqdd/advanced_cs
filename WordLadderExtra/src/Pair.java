/*
 * Pair is a wrapper class.
 */

public class Pair {
	public String start, end;
	
	
	public Pair() {start = null; end = null;}
	public Pair(String s1, String s2) {
		start = s1; end = s2;
	}
	public Pair(String[] arr)
	{
		start = arr[0]; end = arr[1];
	}
	
	public String toString()
	{
		return "{" + start + "," + end + "}";
	}

}