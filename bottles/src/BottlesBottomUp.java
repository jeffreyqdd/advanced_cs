import java.io.*;
import java.util.*;


public class BottlesBottomUp {

    public static int CASES;

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new File("bottles.dat"));

        //read in N
        CASES = sc.nextInt();


        for(int i = 0; i < CASES; i++) {
            int numBottles = sc.nextInt();
            ArrayList<Integer> shelf = new ArrayList<>();

            for (int j = 0; j < numBottles; j++) {
                shelf.add(sc.nextInt());
            }


            //solve bottom up
            int[] dp = new int[shelf.size()];

            dp[0] = shelf.get(0);

            for(int bottle = 1; bottle < shelf.size(); bottle++)
            {
                if(bottle == 1)
                {
                    dp[1] = Math.max(dp[0], shelf.get(bottle));

                }
                else
                {
                    dp[bottle] = Math.max(dp[bottle - 1], dp[bottle - 2] + shelf.get(bottle));
                }

                //System.out.println("bottle index: " + bottle + " --> " + dp[bottle]);
            }

            System.out.println(dp[shelf.size() - 1]);
        }
    }

}
