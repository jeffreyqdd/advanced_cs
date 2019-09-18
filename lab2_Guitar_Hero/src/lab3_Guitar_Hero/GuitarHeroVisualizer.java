package lab3_Guitar_Hero;

public class GuitarHeroVisualizer implements Runnable{


	double[] data;
	//double[] copied;
 	GuitarHeroVisualizer()
	{
        StdDraw.setPenRadius(0.005);
        StdDraw.enableDoubleBuffering();
	}
	
	public void run()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while(true)
		{
			//first copy
			
			double[] copied = data;
			
			
			//draw
			StdDraw.show();
			StdDraw.clear();
			for(double i = 0; i < copied.length-1; i++)
			{
				
				StdDraw.setPenColor(StdDraw.BLUE);
				
				StdDraw.line(i/copied.length, (copied[(int) i] / 2) + 0.5,
						(i+1)/copied.length, (copied[(int) (i+1)] / 2) + 0.5);
			}
			
		}
	}
}