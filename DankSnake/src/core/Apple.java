package core;

import wrapper.Pair;

import java.util.Random;

public class Apple
{
	private int x, y;
	private int size;
	Random random;
	public Apple()
	{
		random = new Random(0);
		
		x = random.nextInt() % Global.GRID_X;
		y = random.nextInt() % Global.GRID_Y;
		
		size = 100;
	}
	
	public boolean checkCollision(Snake s)
	{
		Pair p = s.getCoord().get(0);
		if(p.x == x && p.y == y)
		{
			x = random.nextInt() % Global.GRID_X;
			y = random.nextInt() % Global.GRID_Y;
			
			s.addLength(size);
			return true;
		}
		return false;
	}
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
