import java.util.*;
import java.io.*;


public class Truck {
    //since it is dp, we will be using 1 based indexing


    private static int N;


    private static int MAX_WEIGHT, NUM_PAIRS;
    private static int[][] dp;
    private static int[] weight, value;


    public static int topDownMem(int i, int w) {
        if(i <= 0 || w <= 0)
        {
            return 0;
        }
        else if(dp[i][w] != -1)
        {
            return dp[i][w];
        }
        else if(w - weight[i] >= 0)
        {
            dp[i][w] = Math.max(topDownMem(i - 1, w),
                    topDownMem(i, w - weight[i]) + value[i]
            );
        }
        else
        {
            return topDownMem(i - 1, w);
        }

        return dp[i][w];
    }

    public static int bottomUpDp(int totalI, int totalWeight)
    {

        for(int i = 1; i<= totalI; i++)
        {
            for(int w = 1; w <= totalWeight; w++)
            {
                if(w - weight[i] < 0)
                {
                    dp[i][w] = dp[i-1][w];
                }
                else
                {
                    dp[i][w] = Math.max(dp[i-1][w], dp[i][w-weight[i]] + value[i]);
                }
            }
        }

        return dp[totalI][totalWeight];
    }





    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("truck.dat"));

        N = sc.nextInt();


        for(int i = 1; i <= N; i++)
        {
            MAX_WEIGHT = sc.nextInt();
            NUM_PAIRS = sc.nextInt();

            dp = new int[NUM_PAIRS + 2][MAX_WEIGHT + 2];
            weight = new int[NUM_PAIRS + 1];
            value = new int[NUM_PAIRS + 1];

            for(int a = 0; a < dp.length; a++) {
                for (int b = 0; b < dp[0].length; b++) {
                    if(a == 0 || b == 0)
                        dp[a][b] = 0;
                    else
                        dp[a][b] = -1;
                }
            }



            for(int pair = 1; pair <= NUM_PAIRS; pair++)
            {
                weight[pair] = sc.nextInt();
                value[pair] = sc.nextInt();
            }



            //--------------------------------------------------
            System.out.println( topDownMem(NUM_PAIRS, MAX_WEIGHT) );
            //System.out.println( bottomUpDp(NUM_PAIRS, MAX_WEIGHT));


        }
    }
}
