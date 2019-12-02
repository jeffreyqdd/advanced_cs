import java.util.Random;

public class HashTable_Random
{
	private Object[] arr;
	private int size;
	private int capacity;
	
	public long probes;
	
	HashTable_Random() // Sets default table size to 101
	{
		arr = new Object[101];
		capacity = 101;
		size = 0;
		probes = 0;
	}
	HashTable_Random(int initCap)
	{
		arr = new Object[initCap];
		capacity = initCap;
		probes = 0;
		size = 0;
	}
	//removes a certain entry
	public Object remove(Entry e) {return remove(e.getKey());}
	public Object remove(Object key)
	{
		int idx = key.hashCode();
		Random r = new Random(idx);
		
		
		for (int nextProbe = idx % capacity; ; nextProbe = Math.abs(idx + r.nextInt()) % capacity)
		{
			Entry e = (Entry) arr[nextProbe];
			if (e == null) return null;
			
			if (e.getKey().equals(key))
			{
				((Entry) arr[nextProbe]).markRemoved();
				size--;
				return e.getValue();
			}
		}
	}
	
	public Object put(Entry e) {return put(e.getKey(), e.getValue());}
	
	public Object put(Object key, Object value) throws IllegalStateException
	{
		//check if full
		if(size == arr.length) throw new IllegalStateException("Out of memory");
		
		int idx = key.hashCode();
		Random r = new Random(idx);
		
		for (int nextProbe = idx % capacity; ; nextProbe = Math.abs(idx + r.nextInt()) % capacity)
		{
			//System.out.println(nextProbe);
			
			Entry e = (Entry) arr[nextProbe];
			
			probes++;
			
			if(e == null) //if loc is empty, replace, return null, and increment size
			{
				arr[nextProbe] = new Entry(key, value);
				size++;
				return null;
			}
			
			if(e.getKey().equals(key))
			{
				//System.out.println("overwrite");
				arr[nextProbe] = new Entry(key, value);
				return e.getValue();
			}
			
			//if removed
			if(e.isRemoved())
			{
				arr[nextProbe] = new Entry(key, value);
				size++;
				
				//System.out.println();
				
				//remove repeats if any
				for(;;nextProbe = Math.abs((idx + r.nextInt()) % capacity))
				{
					Entry possible = (Entry) arr[nextProbe];
					
					if(possible == null) return null;
					if(possible.getKey().equals(key))
					{
						((Entry) arr[nextProbe]).markRemoved();
						return possible.getValue();
					}
				}

			}
		}
		
	}
	
	Object get(Entry e){return get(e.getKey());}
	Object get(Object key)
	{
		int idx = key.hashCode();
		Random r = new Random(idx);
		
		for(int nextProbe = idx % capacity; ; nextProbe = Math.abs(idx + r.nextInt()) % capacity)
		{
			Entry e = ((Entry) arr[nextProbe]);
			
			//System.out.println("Probing: " + e);
			probes++;
			
			if(e == null) return null;
			if(e.getKey().equals(key))
				return e.getValue();

		}
	}
	public String toString() // Returns a formatted string, ordered by bucket index
	{
		String  s = "";
		
		for(int i = 0; i < arr.length; i++)
		{
			Entry entry = (Entry) arr[i];
			
			if(entry != null)
			{
				String idx = Integer.toString(i);
				
				for(int j = 0, size = idx.length(); j < 3 - size; j++ )
					idx = "0" + idx;
				
				s += idx + ": " + entry + "\n";
			}
		}
		return s;
	}
	
	
	public int size()
	{
		return size;
	}
	
	
	
}
