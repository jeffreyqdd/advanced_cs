package lab3_Guitar_Hero;

public class GuitarDriver {

	public static void main(String[] args) {
		
		GuitarHero player = new GuitarHero();
		GuitarHeroVisualizer image = new GuitarHeroVisualizer();
		
		Thread sounds = new Thread(player);
		Thread waves = new Thread(image);
		
		
		sounds.start();
		
		
		
		
		waves.start();
		
		while(true)
		{
			double[] cp = player.get_sample();
			
			image.data = cp;
		}
			

	}

}
