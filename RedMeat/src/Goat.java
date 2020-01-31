import java.util.*;
import java.io.*;

public class Goat {

    private static int numTestCases;

    private static void solve(HashSet<Cow> hashCow, int lcm) {

        //System.out.println("entering: " + lcm);

        int lcmReset = 0;
        int cowEaten = 0;
        int day = 1;

        while(lcmReset < lcm)
        {
            Heap<Cow> heap = new Heap<>();

            for(Cow c : hashCow)
            {
                //System.out.println("inserting: " + c);
                heap.add(c, c.getNextMilk());
            }

            if(heap.size() == 0)
            {
                break;
            }
            else if(heap.size() == 1)
            {
                Cow a = heap.removeMin();

                hashCow.remove(a);

                lcmReset = 0;

                cowEaten = day;
            }
            else
            {
                Cow a = heap.removeMin();
                Cow b = heap.removeMin();

                //System.out.println("removing: " + a);

                if(a.getNextMilk() != b.getNextMilk())
                {
                    hashCow.remove(a);
                    lcmReset = 0;

                    cowEaten = day;
                }
            }

            //System.out.println(lcmReset);

            for(Cow c : hashCow)
            {
                c.nextDay();
            }

            lcmReset++;
            day++;

        }

        System.out.println(hashCow.size() + " " + cowEaten);


    }

    public static int getLcm(ArrayList<Integer> arrList)
    {
        int[] element_array = new int[arrList.size()];

        for(int i = 0; i < arrList.size(); i++)
        {
            element_array[i] = arrList.get(i);
        }



        int lcm_of_array_elements = 1;
        int divisor = 2;

        while (true) {
            int counter = 0;
            boolean divisible = false;
            for (int i = 0; i < element_array.length; i++) {

                if (element_array[i] == 0) {
                    return 0;
                } else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }

            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            } else {
                divisor++;
            }
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }



    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(new File("goat.dat"));

        numTestCases = sc.nextInt();

        for(int testCase = 0; testCase < numTestCases; testCase++)
        {
            HashSet<Cow> cowArr = new HashSet<>();
            ArrayList<Integer> numCycles = new ArrayList<>();

            int numCows = sc.nextInt();

            for(int cow = 0; cow < numCows; cow++)
            {
                int cycleCnt = sc.nextInt();

                numCycles.add(cycleCnt);

                Cow c = new Cow(cow);
                for(int cycle = 0; cycle < cycleCnt; cycle++) {
                    c.addItem(sc.nextInt());
                }

                cowArr.add(c);

            }

            int lcm = getLcm(numCycles);
            solve(cowArr, lcm);
        }
    }

    static class Cow
    {
        private ArrayList<Integer> production;
        private int currCycle;
        private int id;
        Cow(int hash)
        {
            id = hash;
            currCycle = 0;
            production = new ArrayList<>();
        }
        Cow(Cow old)
        {
            production = new ArrayList<>(old.getArray());
            this.currCycle = old.currCycle;
            this.id = old.id;
        }

        public void addItem(int n)
        {
            production.add(n);
        }
        public void nextDay()
        {
            currCycle = (currCycle + 1) % production.size();
        }
        public int getNextMilk()
        {
            return production.get(currCycle);
        }
        public ArrayList<Integer> getArray()
        {
            return production;
        }
        @Override
        public String toString()
        {
            return id + ": " + production.toString();
        }
        @Override
        public boolean equals(Object obj)
        {
            Cow toCompare = (Cow) obj;
            if(production.equals(toCompare.getArray()))
            {
                return true;
            }
            return false;
        }
        @Override
        public int hashCode()
        {
            return id;
        }

    }
}
