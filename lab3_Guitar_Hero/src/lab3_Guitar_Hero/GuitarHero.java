package lab3_Guitar_Hero;


public class GuitarHero{
	GuitarString[] notes;
	GuitarHeroVisualizer image;
	char currentKey;
	GuitarHero()
	{
		notes = new GuitarString[37];
		image = new GuitarHeroVisualizer();
		
		
		currentKey = '~';
		
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
	
	//plucks the strings
	void play(char str)
	{
		int toInt = convert(str);
		if(toInt != -1) System.out.println(toInt);
		if(toInt == -1)
			return;
		
		notes[toInt].pluck();
	}
	
	public static void main(String[] args) {
		 	var player = new GuitarHero();
		 	
		 	
	        while (true) 
	        {
			 	player.currentKey = '~';
				if(StdDraw.hasNextKeyTyped()) player.currentKey = StdDraw.nextKeyTyped();

				player.play(player.currentKey);


	            // compute the superposition of the samples
	            double sample = 0;
	            for(int i = 0; i < player.notes.length; i++)
	            	sample += player.notes[i].sample();

	            // send the result to standard audio
	            StdAudio.play(sample);

	            // advance the simulation of each guitar string by one step
	            for(int i = 0; i < player.notes.length; i++)
	            {
	            	player.notes[i].tic();
	            }
	            
	            
	   
	        }
	}
}