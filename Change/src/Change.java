import java.util.*;
import java.io.*;

public class Change {

    private static int NUM_CASES;

    private static int[][] dp;
    private static int[] denominationAmounts;


    private static HashMap<Integer, Integer> backTrack(int coinType, int centsLeft)
    {
        HashMap<Integer, Integer> dict = new HashMap<>();

        while(centsLeft > 0)
        {
            int minCoinsWithCurr = dp[coinType][centsLeft];

            int trackBack, trackUp;


            if(centsLeft - denominationAmounts[coinType] > 0)
            {
                trackBack = dp[coinType][centsLeft - denominationAmounts[coinType]];
            }
            else
            {
                trackBack = -1;
            }
            if(coinType > 1)
            {
                trackUp = dp[coinType - 1][centsLeft];
            }
            else
            {
                trackUp = -1;
            }

            if(trackBack == -1)
            {
                //track up

            }
            if(trackUp == -1)
            {
                //track back

            }

        }


        return dict;
    }


    private static int topDownMem(int coinType, int centsLeft)
    {
        if(dp[coinType][centsLeft] != -1)
        {
            return dp[coinType][centsLeft];
        }

        else
        {
            if(coinType == 1)
            {
                dp[coinType][centsLeft] = topDownMem(coinType, centsLeft - denominationAmounts[coinType]) + 1;
            }
            else if(centsLeft - denominationAmounts[coinType] < 0)
            {
                dp[coinType][centsLeft] =  topDownMem(coinType - 1, centsLeft);
            }
            else
            {
                dp[coinType][centsLeft] =  Math.min(
                        topDownMem(coinType - 1, centsLeft),
                        topDownMem(coinType, centsLeft - denominationAmounts[coinType]) + 1
                );
            }

            return dp[coinType][centsLeft];
        }

    }


    private static int bottomUpDp(int coinType, int centsLeft)
    {
        for(int cents = 1; cents <= centsLeft; cents++)
        {
            for(int coins = 1; coins <= coinType; coins++)
            {
                if(coins == 1)
                {
                    dp[coins][cents] = dp[coins][cents - denominationAmounts[coins]] + 1;
                }
                
            }
        }




    }


    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("change.dat"));

        NUM_CASES = sc.nextInt();

        for(int curr_cass = 0; curr_cass < NUM_CASES; curr_cass++)
        {

            int numDenominations = sc.nextInt();
            denominationAmounts = new int[numDenominations + 1];

            for(int denom = 1; denom <= numDenominations; denom++)
            {
                denominationAmounts[denom] = sc.nextInt();
            }

            int centGoal = sc.nextInt();

            //rows = number of denominations, columns = cent achieved
            dp = new int[numDenominations + 1][centGoal + 1];

            for(int r = 0; r <= numDenominations; r++)
            {
                for(int c = 0; c <= centGoal; c++)
                {
                    if(c == 0)
                    {
                        dp[r][c] = 0;
                    }
                    else
                    {
                        dp[r][c] = -1;
                    }
                }
            }

            //for(int n : denominationAmounts)
                //System.out.println(n);

            for(int r = 0; r <= numDenominations; r++)
            {
                for(int c = 0; c <= centGoal; c++)
                {
                    System.out.print(dp[r][c] + " ");
                }
                System.out.println();
            }


            System.out.println( topDownMem(numDenominations, centGoal) );



            for(int r = 0; r <= numDenominations; r++)
            {
                for(int c = 0; c <= centGoal; c++)
                {
                    System.out.print(dp[r][c] + " ");
                }
                System.out.println();
            }
        }

    }
}
