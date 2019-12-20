import datapackage.Coord;
import datapackage.Pair;

import java.io.IOException;
import java.util.ArrayList;

public class PlotStarsDriver
{
	private static final int SCALE = 1000;
	
	public static Coord normalize(Coord c)
	{
		int half = SCALE/2;
		return new Coord((c.x * half) + half, (c.y * half) + half);
	}
	
	public static void plotStarsPlain(HashBucket coordinates, ArrayList<Integer> list)
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		for(Integer key : list)
		{
			Coord cNorm = normalize((Coord) coordinates.get(key));
			//System.out.println("drawing: " + cNorm);
			StdDraw.filledRectangle(cNorm.x, cNorm.y, 1, 1);
		}
	}
	public static void plotStarsMagnitude(HashBucket coordinates, HashBucket magnitudes, ArrayList<Integer> list)
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		for(Integer key : list)
		{
			Coord cNorm = normalize((Coord) coordinates.get(key));
			double starSize = 10.0 / ((double) magnitudes.get(key) + 2.0);
			//System.out.println("drawing: " + cNorm);
			StdDraw.filledRectangle(cNorm.x, cNorm.y, starSize / 2.0, starSize / 2.0);
		}
	}
	
	public static void plotConstellations(HashBucket coordinates, HashBucket names, ArrayList<Pair<String, String>> constellationPairs)
	{
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.setPenRadius(0.0005);
		for(Pair<String, String> p : constellationPairs)
		{

			int henryD1 = (int) names.get(p.first);
			int henryD2 = (int) names.get(p.second);
			
			Coord coordOfHenryDraperNumber = (Coord) coordinates.get(henryD1);
			Coord coordOriginalStar = (Coord) coordinates.get(henryD2);
			
			Coord norm1 = normalize(coordOfHenryDraperNumber);
			Coord norm2 = normalize(coordOriginalStar);
			StdDraw.line(norm1.x, norm1.y, norm2.x, norm2.y);
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		StdDraw.setScale(0, SCALE);
		StdDraw.clear(StdDraw.BLACK);
		
		HashBucket[] hb = PlotStars.Scan();
		
		HashBucket coordinates = hb[0];
		HashBucket magnitudes = hb[1];
		HashBucket names = hb[2];
		ArrayList<Integer> henryDraperList = PlotStars.getHenryDraper();
		ReadConstellations.ScanConstellationFiles();
		ArrayList<Pair<String, String>> constellationPairs = ReadConstellations.constellationPairs;

		//System.out.println(constellationConnections.size());
		//plotStarsPlain(coordinates, henryDraperList);
		plotStarsMagnitude(coordinates, magnitudes, henryDraperList);
		plotConstellations(coordinates, names, constellationPairs);
	}
}
