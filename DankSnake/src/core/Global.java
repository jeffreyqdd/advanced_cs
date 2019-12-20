package core;

import wrapper.Pair;

public class Global
{
	
	public static String[] UI_TEXT = {"Screen Size: ", "Map Size: ", "Map Resolution: ",
									  "Frame Cap: "};
	public static Pair[] SCREEN_SIZE = {new Pair(1000,300), new Pair(400,400), new Pair(500,500), new Pair(750, 750), new Pair(1500,900)};
	public static int SCREEN_SIZE_INDEX =  0;
	
	public static int RESOLUTION_X = SCREEN_SIZE[SCREEN_SIZE_INDEX].x;
	public static int RESOLUTION_Y = SCREEN_SIZE[SCREEN_SIZE_INDEX].y;
	
	public static Pair[] MAP_SIZES = {new Pair(10, 10), new Pair(20, 20), new Pair(30, 30), new Pair(50, 50), new Pair(100, 100)};
	public static int MAP_SIZE_INDEX = 2;
	public static int GRID_X = MAP_SIZES[MAP_SIZE_INDEX].x;
	public static int GRID_Y = MAP_SIZES[MAP_SIZE_INDEX].y;
	
	public static int CANVAS_X = 500;
	public static int CANVAS_Y = 500;
	
	
	
	
	public static int[] FRAME_OPTIONS = {3, 30, 60, 120, 144, 165, 200, 240, 1000};
	public static int FRAME_INDEX = 5;
	public static int FRAME_CAP = FRAME_OPTIONS[FRAME_INDEX];
	
	

	

	
	public static void update()
	{
		RESOLUTION_X = SCREEN_SIZE[SCREEN_SIZE_INDEX].x;
		RESOLUTION_Y = SCREEN_SIZE[SCREEN_SIZE_INDEX].y;
		FRAME_CAP = FRAME_OPTIONS[FRAME_INDEX];
		GRID_X = MAP_SIZES[MAP_SIZE_INDEX].x;
		GRID_Y = MAP_SIZES[MAP_SIZE_INDEX].y;
	}
	

}
