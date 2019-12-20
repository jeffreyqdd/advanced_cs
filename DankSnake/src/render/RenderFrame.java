package render;

import core.Apple;
import core.Global;
import core.Snake;
import wrapper.Pair;

import java.util.ArrayList;

public class RenderFrame
{
	public RenderFrame()
	{
		update();
	}
	public void updateUI(int n, boolean isE)
	{
		if(Global.UI_TEXT[n].equals("Screen Size: ") && isE)
		{
			Global.SCREEN_SIZE_INDEX += 1;
			Global.SCREEN_SIZE_INDEX %= Global.SCREEN_SIZE.length;
			Global.update();
			update();
		}
		
		else if(Global.UI_TEXT[n].equals("Frame Cap: ") && isE)
		{
			Global.FRAME_INDEX += 1;
			Global.FRAME_INDEX %= Global.FRAME_OPTIONS.length;
			Global.update();
			update();
		}
		
		else if(Global.UI_TEXT[n].equals("Map Size: ") && isE)
		{
			Global.MAP_SIZE_INDEX += 1;
			Global.MAP_SIZE_INDEX %= Global.MAP_SIZES.length;
			Global.update();
			update();
		}
	}
	
	public void renderBeginningUI(int n)
	{
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(0,0, Global.CANVAS_X, Global.CANVAS_Y);
		
		
		
		for(int i = 0; i < Global.UI_TEXT.length; i++)
		{
			/* same for everything*/
			if(i == n)
				StdDraw.setPenColor(StdDraw.GREEN);
			else
				StdDraw.setPenColor(StdDraw.WHITE);
			
			int x = -Global.CANVAS_X + Global.CANVAS_X / 5 ;
			int y = (int) (Global.CANVAS_Y * 0.8) - (Global.CANVAS_Y / Global.UI_TEXT.length) * i;
			
			
			//good stuff
			String text = Global.UI_TEXT[i];
			
			if(Global.UI_TEXT[i].equals("Screen Size: "))
				text += Global.RESOLUTION_X + " x " + Global.RESOLUTION_Y;
			if(Global.UI_TEXT[i].equals("Frame Cap: "))
				text += "MAX " + Global.FRAME_CAP;
			if(Global.UI_TEXT[i].equals("Map Size: "))
				text += Global.GRID_X + " x " + Global.GRID_Y;
			StdDraw.textLeft(x,y , text);
		}
	}
	public void renderSnake(Snake snake)
	{
		StdDraw.setPenColor(StdDraw.GREEN);
		
		ArrayList<Pair> coord = snake.getCoord();
		
		for(Pair p : coord)
		{
			Pair normalizedP = normalize(p);
		
			int hw = Global.CANVAS_X / Global.GRID_X / 2;
			int hh = Global.CANVAS_X / Global.GRID_Y / 2;
			//System.out.println(normalizedP);
			StdDraw.filledRectangle(normalizedP.x, normalizedP.y, hw, hh);
		}
	}
	public void renderApple(Apple apple)
	{
		StdDraw.setPenColor(StdDraw.RED);
		Pair normP = normalize(new Pair(apple.getX(), apple.getY()));
		int hw = Global.CANVAS_X / Global.GRID_X / 2;
		int hh = Global.CANVAS_X / Global.GRID_Y / 2;
		StdDraw.filledRectangle(normP.x, normP.y, hw, hh);
		
	}
	
	private Pair normalize(Pair p)
	{
		int scaleX = Global.CANVAS_X / Global.GRID_X;
		int scaleY = Global.CANVAS_X / Global.GRID_Y;
		
		return new Pair(p.x * scaleX, p.y * scaleY);
	}
	private void update()
	{
		StdDraw.enableDoubleBuffering();
		StdDraw.setCanvasSize(Global.RESOLUTION_X, Global.RESOLUTION_Y);
		StdDraw.setXscale(-Global.CANVAS_X, Global.CANVAS_Y);
		StdDraw.setYscale(-Global.CANVAS_X, Global.CANVAS_Y);
		
	}
}
