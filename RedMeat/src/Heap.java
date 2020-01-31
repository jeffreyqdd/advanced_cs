import java.util.ArrayList;

public class Heap<E> {
    private ArrayList<Node<E>> arr;
    private int size;

    public Heap()
    {
        arr = new ArrayList<>();

        this.arr.add(new Node<E>(null, Integer.MIN_VALUE));
        this.size = 0;
    }

    public void add(E e, int priority)
    {
        if(size == arr.size() - 1)
            arr.add(null);

        size++;

        arr.set(size, new Node<E>(e, priority));

        int i = size;

        while(i != 0 && arr.get(getParent(i)).priority > arr.get(i).priority)
        {
            swap(i, getParent(i));
            i = getParent(i);
        }

    }

    public int size()
    {
        return size;
    }

    public E removeMin()
    {
        Node ret = arr.get(1);

        arr.set(1, arr.get(size));
        size--;


        if(size == 0)
            return (E) ret.holder;

        minHeapify(1);


        return (E) ret.holder;
    }


    private void minHeapify(int node)
    {
        int leftChild = getLeftChild(node);
        int rightChild = getRightChild(node);

        if(leftChild <= size && arr.get(leftChild).priority < arr.get(node).priority)
        {
            if(rightChild <= size && arr.get(rightChild).priority < arr.get(leftChild).priority)
            {
                swap(node, rightChild);
                minHeapify(rightChild);
            }
            else
            {
                swap(node, leftChild);
                minHeapify(leftChild);
            }
        }
        else if(rightChild <= size && arr.get(rightChild).priority < arr.get(node).priority)
        {
            swap(node, rightChild);
            minHeapify(rightChild);
        }


    }
    public String toString()
    {
        return convert();
    }

    private String convert()
    {
        StringBuilder sb = new StringBuilder();


        for(int i = 1, level = 1; i <= size; i++)
        {
            if(i == Math.pow(2, level))
            {
                level++;
                sb.append("\n");
            }


            sb.append(arr.get(i) + " ");
        }


        return sb.toString();
    }


    public boolean isEmpty()
    {
        return size == 0;
    }

    private void swap(int i, int j)
    {
        Node intermediate = arr.get(j);
        arr.set(j, arr.get(i));
        arr.set(i, intermediate);
    }
    private int getParent(int i)
    {
        return i / 2;
    }

    private int getLeftChild(int i)
    {
        return (2 * i);
    }

    private int getRightChild(int i)
    {
        return (2 * i) + 1;
    }

    class Node<T>
    {
        T holder;
        int priority;

        Node(T t, int priority)
        {
            this.holder = t;
            this.priority = priority;
        }

        @Override
        public String toString()
        {
            return "" + priority;
        }


    }

}
