package core;

import wrapper.Pair;

import java.util.ArrayList;
import java.util.Queue;

public class Snake
{
	private ArrayList<Pair> coordinates;
	private int currDir;
	private int toAdd;
	
	
	public Snake()
	{
		coordinates = new ArrayList<>();
		coordinates.add(new Pair(0,0));
		coordinates.add(new Pair(0,1));
		coordinates.add(new Pair(0,2));
		
		currDir = 1;
		
		toAdd = 0;
	}
	public boolean checkCollision()
	{
		Pair head = coordinates.get(0);
		
		for(int i = 1; i < coordinates.size(); i++)
		{
			Pair current = coordinates.get(i);
			if(current.x == head.x && current.y == head.y)
				return true;
		}
		
		if(Math.abs(head.x) > Global.GRID_X || Math.abs(head.y) > Global.GRID_Y)
			return true;
		
		return false;
	}
	public void moveSnake()
	{
		Pair prevLoc = new Pair(coordinates.get(0).x, coordinates.get(0).y);
		
		if(currDir == 1) //north
			coordinates.get(0).y += 1;
		if(currDir == 2) //east
			coordinates.get(0).x += 1;
		if(currDir == 3) //south
			coordinates.get(0).y -= 1;
		if(currDir == 4) //west
			coordinates.get(0).x -= 1;
		
		Pair before;
		for(int i = 1; i < coordinates.size(); i++)
		{
			before = new Pair(coordinates.get(i).x, coordinates.get(i).y);
			coordinates.set(i, prevLoc);
			prevLoc = before;
		}
		
		if(toAdd > 0)
		{
			toAdd --;
			coordinates.add(prevLoc);
		}
		
	}
	
	public void addLength(int n)
	{
		toAdd = n;
	}
	
	public void updateDir(int dir)
	{
		currDir = dir;
	}
	public ArrayList<Pair> getCoord()
	{
		return coordinates;
	}
	
	public String toString()
	{
		String s = "";
		for(Pair p : coordinates)
			s += p + " ";
		
		return s;
	}

}
