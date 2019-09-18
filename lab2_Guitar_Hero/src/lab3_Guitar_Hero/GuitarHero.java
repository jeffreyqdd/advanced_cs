package lab3_Guitar_Hero;


public class GuitarHero implements Runnable{
	GuitarString[] notes;
	RingBuffer data;
	
	GuitarHero()
	{
		notes = new GuitarString[37];
		data = new RingBuffer(500);
		
		//fill with empty sounds;
		for(int i = 0; i < notes.length; i++)
			notes[i] = new GuitarString(440 * Math.pow(1.05956, i-24));
	}

	int convert(char key)
	{
		char[] keyboard = {'q','2','w','e','4','r','5','t','y','7','u','8',
                'i','9','o','p','-','[','=','z','x','d','c','f',
                'v','g','b','n','j','m','k',',','.',';','/',
                '\'',' ' };
		
		for(int i = 0; i < keyboard.length; i++)
		{
			if(keyboard[i] == key)
				return i;
		}
		
		return -1; //not found
	}
	
	//Pluck the strings
	void play(char str)
	{
		int toInt = convert(str);
		//if(toInt != -1) System.out.println(toInt);
		if(toInt == -1)
			return;
		
		notes[toInt].pluck();
	}
	//gets values
	

	double[] get_sample()
	{
		double[] arr = new double[data.length()];
		
		for(int i = 0; i < data.length();i++)
		{
			arr[i] = data.arr[i];
		}
		
		return arr;
	}
	
	
	
	//multithreading
	public void run()
	{	
		while(true)
		{
			
			char currentKey = '~';
			
			if(StdDraw.hasNextKeyTyped()) currentKey = StdDraw.nextKeyTyped();
	
			play(currentKey);
	
	
		    // compute the super position of the samples
		    double sample = 0;
		    for(int i = 0; i < notes.length; i++)
		    	sample += notes[i].sample();
	
		   
		    // send the result to standard audio
		    StdAudio.play(sample);
		    
		    //update data
		    if(data.isFull())
		    	data.dequeue();
		    data.enqueue(sample);
		         
		    // advance the simulation of each guitar string by one step
		    for(int i = 0; i < notes.length; i++)
		    {
		        notes[i].tic();
		    }
	    
		}
	           
	}
}