package lab3_Guitar_Hero;

public class GuitarHeroVisualizer{

	RingBuffer image;
	GuitarHeroVisualizer()
	{

        
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLUE);
        image = new RingBuffer(40);
	}
	void update(double sample)
	{
		if(image.isFull())
			image.dequeue();
		image.enqueue(sample);
	}
	
	void render()
	{
		StdDraw.clear();
		for(double i = 0; i < image.size()-1;i++)
		{
			int scale = image.length();

			
			
			StdDraw.line((i)/scale, image.arr[(int)i]/4 + 0.5,
					(i+1)/scale, image.arr[(int)i + 1]/4 + 0.5);

		}
	}


}