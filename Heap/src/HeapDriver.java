public class HeapDriver
{
	public static void main(String[] args)
	{
		Heap heap = new Heap(10);
		
		int[] arr = {2,5,8,2,5,7,0,0,0,3,4};

		for(int n : arr)
		{
			heap.add(n);
		}
		
		while(!heap.isEmpty())
		{
			System.out.println(heap.removeMin());
		}


		
	}
}
