package lab3_Guitar_Hero;


public class GuitarString {
	
    private static final double SAMPLE_RATE = 44100;
    private static final double DECAY_RATE =  0.97;
    private static final double AMPLITUDE = 1;
    
    RingBuffer rb;
    int totalTick;
	// create a guitar string of the given frequency, using a sampling rate of 44,100
	GuitarString(double frequency)
	{
		int qLen =  (int) Math.round( SAMPLE_RATE / frequency );
		rb = new RingBuffer(qLen);
		totalTick = 0;
		
		for(int i = 0; i < qLen; i++)
			rb.enqueue(0);
			
	}
	
	// create a guitar string whose size and initial values are given by the array
	GuitarString(double[] init)
	{
		rb = new RingBuffer(init.length);
		totalTick = 0;
		
		for(int i = 0; i < init.length; i++)
			rb.enqueue(init[i]);
		System.out.println("doing");
	}
	
	// set the buffer to white noise
	void pluck()
	{
		for(int i = 0; i < rb.length(); i++)
		{
			double random = (Math.random() - 0.5) * AMPLITUDE; //[-0.5,0.5)
			rb.dequeue();
			rb.enqueue(random);
		}
	}
	// advance the simulation one time step
	void tic()
	{
		//pop 2, avg, multi decay, push
		
		if(!rb.isEmpty())
		{
			double a = rb.dequeue(), b = rb.peek();

			double newItem = DECAY_RATE * 0.5 * (a+b);
		
			rb.enqueue(newItem);
		}
	}
	// return the current sample
	double sample()
	{
		return rb.peek();
	}
	// return number of tics
	int time()
	{
		return totalTick;
	}

}
