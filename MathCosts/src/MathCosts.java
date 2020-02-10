import java.util.*;
import java.io.*;

public class MathCosts {

    private static void solve(ArrayList<Integer> arr)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(Integer n : arr)
        {
            pq.add(n);
        }


        int ret = 0;
        while(pq.size() >= 2)
        {
            int a = pq.poll(), b = pq.poll();

            pq.add(a + b);
            ret += a + b;
        }

        System.out.println(ret);

    }

    public static void main(String[] args) throws IOException
    {

        Scanner sc = new Scanner(new File("mathCosts.dat"));

        while(true)
        {
            int cnt = sc.nextInt();

            if(cnt == 0) break;

            ArrayList<Integer> arr = new ArrayList<>();

            for(int i = 0; i < cnt; i++)
            {
                arr.add(sc.nextInt());
            }

            solve(arr);
        }



    }
}
