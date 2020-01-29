package botcore.utilities;

import java.text.DecimalFormat;
import java.util.*;

//import org.apache.commons.lang3.ArrayUtils;

public class Utils
{
	
	public static DecimalFormat df2 = new DecimalFormat("#.##");
	
	/**
	 * Determines whether a scalar is small enough to be treated as zero
	 *
	 * @param d: double value
	 * @return: true or false
	 */
	public static boolean nearZero(double d)
	{
		
		return Math.abs(d) < 1e-3;
	}
	
	public static boolean nearSame(double d1, double d2)
	{
		
		return nearZero(d1 - d2);
	}
	
	public static double[][] to2DArrayRow(double[] oneD)
	{
		double[][] twoDRow = {oneD};
		return twoDRow;
	}
	
	
	/*public static double[][] to2DArrayColumn(List<Double> oneDList)
	{
		return to2DArrayColumn(dListToArray(oneDList));
	
	public static double[][] to2DArrayColumn(double[] oneD)
	{
		
		double[][] twoDCol = new double[oneD.length][1];
		
		for (int i = 0; i < oneD.length; i++)
		{
			twoDCol[i][0] = oneD[i];
		}
		
		return twoDCol;
	}
	
	public static boolean sameDoubleArray(double[] d1, double[] d2)
	{
		if (d1.length != d2.length)
			return false;
		
		for (int i = 0; i < d1.length; i++)
		{
			if (!nearSame(d1[i], d2[i]))
				return false;
		}
		
		return true;
	}
	
	
	public static double[] floatToDouble(float[] floatArray)
	{
		
		double[] doubleArray = new double[floatArray.length];
		
		for (int i = 0; i < floatArray.length; i++)
		{
			doubleArray[i] = floatArray[i];  // no casting needed
		}
		
		return doubleArray;
	}
	
	public static List<Double> sumTwoList(List<Double> d1, List<Double> d2)
	{
		
		
		if (d1 == null || d2 == null || d1.size() == 0)
		{
			throw new IllegalArgumentException("null or empty input");
		}
		
		
		if (d1.size() != d2.size())
		{
			throw new IllegalArgumentException("not same length");
		}
		
		List<Double> sum = new ArrayList<>();
		
		for (int i = 0; i < d1.size(); i++)
		{
			sum.add(d1.get(i) + d2.get(i));
		}
		
		return sum;
	}
	
	
	public static List<Double> subtractTwoList(List<Double> d1, List<Double> d2)
	{
		
		
		if (d1 == null || d2 == null || d1.size() == 0)
		{
			throw new IllegalArgumentException("null or empty input");
		}
		
		
		if (d1.size() != d2.size())
		{
			throw new IllegalArgumentException("not same length");
		}
		
		List<Double> sub = new ArrayList<>();
		
		for (int i = 0; i < d1.size(); i++)
		{
			sub.add(d1.get(i) - d2.get(i));
		}
		
		return sub;
	}
	
	
	public static List<Double> divideList(List<Double> d1, Double dividedBy)
	{
		if (dividedBy == 0)
		{
			throw new IllegalArgumentException("cannot divide by zero");
		}
		
		if (d1 == null || d1.size() == 0)
		{
			throw new IllegalArgumentException("null or empty input");
		}
		
		List<Double> result = new ArrayList<>(d1.size());
		
		for (int i = 0; i < result.size(); i++)
		{
			result.set(i, d1.get(i) + dividedBy);
		}
		
		return result;
		
		
	}
	/*
	public static List<Double> dArrayToList(double[] da)
	{
		return Arrays.asList(ArrayUtils.toObject(da));
	}
	
	public static double[] dListToArray(List<Double> dl)
	{
		Double[] ds = dl.toArray(new Double[dl.size()]);
		return ArrayUtils.toPrimitive(ds);
	}
	
	public static List<Integer> iArrayToList(int[] a)
	{
		List<Integer> r = new ArrayList<Integer>();
		
		for (int i = 0; i < a.length; i++)
		{
			r.add(Integer.valueOf(a[i]));
		}
		return r;
	}*/
	
	/**
	 * convert any theta value to [0,Math.PI/2] or [0, -Math.PI/2)
	 *
	 * @param theta
	 * @return
	 */
	
	public static double canonicalTheta(double theta)
	{
		// in case theta is greather than 2Pi
		double mod2pi = theta % (2 * Math.PI);
		
		// move to -pi, pi range
		if (mod2pi <= -Math.PI)
		{
			return 2 * Math.PI + mod2pi;
		}
		
		if (mod2pi > Math.PI)
		{
			return mod2pi - 2 * Math.PI;
		}
		
		return mod2pi;
	}
	
	/**
	 * Mix two theta (orientation) values by the ratio1 : ratio2
	 *
	 * @param theta1
	 * @param ratio1
	 * @param theta2
	 * @param ratio2
	 * @return
	 */
	public static double mixTheta(double theta1, double ratio1, double theta2, double ratio2)
	{
		if (ratio1 == 0 || ratio2 == 0)
			throw new IllegalArgumentException("ratio cannot be null. ratio1 = " + ratio1
					+ " ratio2=" + ratio2);
		
		double r1 = ratio1 / (ratio1 + ratio2);
		double r2 = ratio2 / (ratio1 + ratio2);
		
		double x1 = Math.cos(theta1);
		double y1 = Math.sin(theta1);
		double x2 = Math.cos(theta2);
		double y2 = Math.sin(theta2);
		
		return Math.atan2(y1 * r1 + y2 * r2, x1 * r1 + x2 * r2);
		
	}
	
	public static <K, V> Set<K> getKeys(Map<K, V> map, V value)
	{
		Set<K> keys = new HashSet<>();
		for (Map.Entry<K, V> entry : map.entrySet())
		{
			if (entry.getValue().equals(value))
			{
				keys.add(entry.getKey());
			}
		}
		return keys;
	}
	
	public static String getShortDoubleStr(double[] dd)
	{
		
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < dd.length; i++)
		{
			sb.append(df2.format(dd[i]) + ", ");
		}
		
		return sb.toString();
	}
	
	/*public static String getShortDoubleStr(List<Double> rawMeasurements)
	{
		return getShortDoubleStr(Utils.dListToArray(rawMeasurements));
	}*/
}
