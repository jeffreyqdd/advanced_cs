import java.util.*;
import java.io.*;

public class Bottles {

    public static int CASES;



    /* this is the memoized recursive solution*/
    public static int[] dp;

    public static int memoizationSetup(ArrayList<Integer> shelf)
    {
        dp = new int[shelf.size()];

        for(int i = 0; i < dp.length; i++)
        {
            dp[i] = -1;
        }

        return memoizedRecursion(shelf, shelf.size() - 1);
    }

    public static int memoizedRecursion(ArrayList<Integer> bottles, int position)
    {
        //return stored data
        if(dp[position] != -1)
        {
            return dp[position];
        }

        //base case 1
        if(position == 0)
        {
            dp[position] = bottles.get(0);
        }
        //base case 2
        else if(position == 1)
        {
            dp[position] = Math.max(bottles.get(0), bottles.get(1));
        }
        //divide and conquer
        else
        {

            dp[position] = Math.max(memoizedRecursion(bottles, position - 1),
                    memoizedRecursion(bottles, position - 2) + bottles.get(position));
        }

        return dp[position];
    }


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new File("bottles.dat"));

        //read in N
        CASES = sc.nextInt();


        for(int i = 0; i < CASES; i++)
        {
            int numBottles = sc.nextInt();
            ArrayList<Integer> shelf = new ArrayList<>();

            for(int j = 0; j < numBottles; j++)
            {
                shelf.add(sc.nextInt());
            }

            //System.out.println(shelf + ": ");
            System.out.println(       memoizationSetup(shelf)            );        }

    }
}