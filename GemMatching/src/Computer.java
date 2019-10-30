
public class Computer {

	public  int row = 0;
	public  int index = 0;
	
	public void makeDecision(GemList<Gem> p1, GemList<Gem> p2, Gem currG)
	{
		int bestDecrease = -10000;
		int bestDecreaseIdx = 0;
		int bestIncrease = -10000;
		int bestIncreaseIdx = 0;
		
		
		for(int i = 0; i <= p1.size(); i++) //add to opponent..hopefully decrease
		{
			GemList<Gem> p1Cpy = p1.createCopy();
			p1Cpy.insertBefore(currG, i);
			int points = p1.score() - p1Cpy.score(); //before should be > after
			if(points > bestDecrease)
			{

				bestDecrease = points;
				bestDecreaseIdx = i;
			}
		}

		for(int i = 0; i <= p2.size(); i++)
		{
			GemList<Gem> p2Cpy = p2.createCopy();
			p2Cpy.insertBefore(currG, i);
			int points = p2Cpy.score() - p2.score(); //after should be > before
			System.out.println("pts: " + Integer.toString(points));
			if(points > bestIncrease)
			{

				bestIncrease = points;
				bestIncreaseIdx = i;
			}
		}
		System.out.println("Best sabatoge: " + Integer.toString(bestDecrease));
		System.out.println("Best Increase: " + Integer.toString(bestIncrease));
		//add to computer
		if(bestIncrease >= bestDecrease)
		{
			row = 2;
			index = bestIncreaseIdx;
			
		}
		else
		{
			row = 1;
			index = bestDecreaseIdx;
		}
	}
	

}
