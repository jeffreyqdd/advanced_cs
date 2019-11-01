public class HashTable {

	Object[] arr = null;
	int size = 0;
	
	HashTable() // Sets default table size to 101
	{
		arr = new Object[101];
	}
	HashTable(int initCap)
	{
		arr = new Object[initCap];
	}
	// Returns the previous value associated with key,
	 // or null if there was no mapping for key
	Object put(Object key, Object value)
	{
		int idx = key.hashCode() % arr.length;
		
		
		Entry e = (Entry) arr[idx];
		
		arr[idx] =  new Entry(key, value);
		
		if(e == null)
			return null;
		
		return e.value;
	}
	
	// Returns the value to which the specified key is mapped,
	// or null if this map contains no mapping for the key
	Object get(Object key) 
	{
		int idx= key.hashCode() % arr.length;
		
		Entry e = ((Entry)arr[idx]);
		
		if(e == null)
			return null;
		
		return e.value;
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
				
				s += idx + ": " + entry.key  + " " + entry.value + "\n";
			}
		}
		return s;
	}
	
	private class Entry
	{
		Object key, value;

		@SuppressWarnings("unused")
		Entry()
		{
			this.key = null;
			this.value = null;
		}
		Entry(Object key, Object value)
		{
			this.key = key;
			this.value = value;
		}
	}
}
