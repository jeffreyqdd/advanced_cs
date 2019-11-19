public class HashTable_Linear
{
	private Object[] arr;
	private int size;
	
	public long probes;
	
	HashTable_Linear() // Sets default table size to 101
	{
		arr = new Object[101];
		probes = 0;
	}
	HashTable_Linear(int initCap)
	{
		arr = new Object[initCap];
		probes = 0;
	}
	//removes a certain entry
	public Object remove(Entry e) {return remove(e.getKey());}
	public Object remove(Object key)
	{
		int idx = key.hashCode() % arr.length;
		
		
		
		for(int i = idx, first= 0;i != idx || first == 0;)
		{
			
			Entry e = (Entry) arr[i];
			
			if(e == null) return null;
			
			//System.out.println(i + " looking at" + e);
			
			if(e.getKey().equals(key))
			{
				((Entry) arr[i]).markRemoved();
				size--;
				return e.getValue();
			}
			
			i = (i + 1) % arr.length; //wrap around
			first = 1;
		}
		return null;
	}
	
	
	
	// Returns the previous value associated with key,
	// or null if there was no mapping for key
	public Object put(Entry e){return put(e.getKey(), e.getValue());}
	public Object put(Object key, Object value) throws IllegalStateException
	{
		//check if full
		if(size == arr.length) throw new IllegalStateException("Out of memory");

		int idx = key.hashCode() % arr.length;
		
		
		for(int i = idx, first= 0;i != idx || first == 0;)
		{
			
			Entry e = (Entry) arr[i];
			
			probes++;
			
			if(e == null) //if loc is empty, replace, return null, and increment size
			{
				arr[i] = new Entry(key, value);
				size++;
				return null;
			}
			//System.out.println("probs: " + key + " " +value);
			//check duplicate, override
			if(e.getKey().equals(key))
			{
				System.out.println("overwrite");
				arr[i] = new Entry(key, value);
				return e.getValue();
			}
			
			//if removed
			if(e.isRemoved())
			{
				arr[i] = new Entry(key, value);
				size++;
				
				//System.out.println();
				
				//remove repeats if any
				for(int j = i + 1, first2 = 0 ;j != idx || first2 == 0;)
				{
					Entry possible = (Entry) arr[j];
					
					if(possible == null) return null;
					if(possible.getKey().equals(key))
					{
						((Entry) arr[j]).markRemoved();
						return possible.getValue();
					}
					
					j = (j + 1) % arr.length; //wrap around
					first2 = 1;
				}
				
				
				return null;
			}
			
			
			i = (i + 1) % arr.length; //wrap around
			first = 1;
		}
		
		return null;
	}
	
	// Returns the value to which the specified key is mapped,
	// or null if this map contains no mapping for the key
	Object get(Entry e){return get(e.getKey());}
	Object get(Object key)
	{
		int idx= key.hashCode() % arr.length;
		
		for(int i = idx, first= 0;i != idx || first == 0;)
		{
			Entry e = ((Entry) arr[i]);
			
			//System.out.println("Probing: " + e);
			probes++;
			
			if(e == null) return null;
			if(e.getKey().equals(key))
				return e.getValue();
			
			i = (i + 1) % arr.length;
			first = 1;
		}
		return null;
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
