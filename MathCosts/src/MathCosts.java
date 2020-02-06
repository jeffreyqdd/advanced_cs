import java.awt.print.PrinterAbortException;
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
        System.out.println("here");
        int a = - 1, b = -1;


        if(pq.isEmpty()) return;

        do {

            if(a == -1) {
                a = pq.poll();
                continue;
            }
            if(b == -1){
                b = pq.poll();
                continue;
            }

            a = a + b;
        }while(!pq.isEmpty());

        System.out.println(a);


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
