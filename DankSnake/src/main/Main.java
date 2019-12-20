package main;

import core.Apple;
import core.Global;
import core.Snake;
import render.RenderFrame;
import render.StdDraw;

public class Main
{
	
	public static RenderFrame render = new RenderFrame();
	public static void begin()
	{
		int n = 0;
		boolean isE = false;
		
		long loopTime = System.currentTimeMillis();
		long delta = 30;
		while(true)
		{
			if(System.currentTimeMillis() - loopTime > delta)
			{
				StdDraw.clear();
				render.renderBeginningUI(n);
				StdDraw.show();
				loopTime = System.currentTimeMillis();
			}
			
			char currentKey = '~';
			if (StdDraw.hasNextKeyTyped())
				currentKey = StdDraw.nextKeyTyped();
			if (currentKey == 'w' && n > 0)
				n -= 1;
			if (currentKey == 's' && n < Global.UI_TEXT.length - 1)
				n += 1;
			if (currentKey == 'y')
				break;
			
			if (currentKey == 'e')
				isE = true;
			else
				isE = false;
			
			
			render.updateUI(n, isE);
			
			
			delta = 1000 / Global.FRAME_CAP;
		}
	}
	
	public static void game()
	{
		
		Snake snake = new Snake();
		Apple apple = new Apple();
		RenderFrame render = new RenderFrame();
		
		
		System.out.println(snake);
		int dir = 1;
		int frames = 0;
		int startImmunity = 0;
		long beforeTime = 0;
		
		long lastLogicTick = 0;
		long lastDirectionTick = 0;
		long lastRenderTick = 0;
		long delta = 1000 / Global.FRAME_CAP;
		int flushFrames = 1;
		for(;true;)
		{
			long currentTime = System.currentTimeMillis();
			/* frame rate counter */

			
			if (currentTime - beforeTime >= 1000)
			{
				flushFrames = frames;
				//System.out.println(frames);
				frames = 0;
				beforeTime = currentTime;
				
			}
			
			/* logic time */
			if (currentTime - lastLogicTick >= 50)
			{
				snake.moveSnake();
				apple.checkCollision(snake);
				
				if (startImmunity < 10)
					startImmunity++;
				else if (snake.checkCollision())
					break;
				
				lastLogicTick = currentTime;
			}
			
			
			if (currentTime - lastDirectionTick >= 50)
			{
				char currentKey = '~';
				if (StdDraw.hasNextKeyTyped())
					currentKey = StdDraw.nextKeyTyped();
				if (currentKey == 'w' && dir != 3)
					dir = 1;
				if (currentKey == 'a' && dir != 2)
					dir = 4;
				if (currentKey == 's' && dir != 1)
					dir = 3;
				if (currentKey == 'd' && dir != 4)
					dir = 2;
				if (currentKey == 'h')
					snake.addLength(100);
				snake.updateDir(dir);
				
				lastDirectionTick = currentTime;
			}
			if(currentTime - lastRenderTick >= delta)
			{
				frames++;
				StdDraw.clear();
				render.renderSnake(snake);
				render.renderApple(apple);
				
				int x = -Global.CANVAS_X + Global.CANVAS_X / 5 ;
				int y = (int) (Global.CANVAS_Y * 0.8) - (Global.CANVAS_Y / Global.UI_TEXT.length);
				
				StdDraw.text(x,y, "fps: " + flushFrames );
				StdDraw.show();
				
				lastRenderTick = currentTime;
			}
		}
	}
	public static void main(String[] args)
	{
		while(true)
		{
			begin();
			game();
		}
		
	}
}
