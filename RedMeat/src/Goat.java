import java.util.*;
import java.io.*;

public class Goat {

    public static int numTestCases;

    public static void solve(HashSet<Cow> hashCow, int longest) {

        Heap heap = new Heap();

        for (Cow c : hashCow) {
            heap.add(c.getNextMilk());
        }

        int a = heap.removeMin();
        int b;

        if(!heap.isEmpty())
        {
            //another goat
            b = heap.removeMin();

            if(a == b)
            {
                //nothing happpens
            }
            else
            {
                //eat it;

            }
        }
        else
        {
            //last goat
        }


    }


    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(new File("goat.dat"));

        numTestCases = sc.nextInt();

        for(int testCase = 0; testCase < numTestCases; testCase++)
        {
            HashSet<Cow> cowArr = new HashSet<>();
            HashMap<ArrayList<Integer>, Integer> intersect = new HashMap<>();

            int numCows = sc.nextInt();
            int collisions = 0;

            for(int cow = 0; cow < numCows; cow++)
            {
                int cycleCnt = sc.nextInt();

                Cow c = new Cow(cow);
                for(int cycle = 0; cycle < cycleCnt; cycle++) {
                    c.addItem(sc.nextInt());
                }

                cowArr.add(c);

                /*if(intersect.containsKey(c.getArray()))
                {
                    intersect.put(c.getArray(), intersect.get(c.getArray()) + 1);
                }
                else
                {
                    intersect.put(c.getArray(), 1);
                }*/

                collisions = Math.max(collisions, c.getArray().size());
            }

            /*for(ArrayList<Integer> intArr : intersect.keySet())
            {
                if(intersect.get(intArr) > 1)
                {
                    collisions += intersect.get(intArr);
                }
            }*/

            solve(cowArr, collisions);
        }






    }

    static class Cow
    {
        public ArrayList<Integer> production;
        public int currCycle;
        public int id;
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
            return production.toString();
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
