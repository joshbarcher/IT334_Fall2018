package examples;

import java.util.Random;

public class HeapDriver
{
    public static void main(String[] args)
    {
        Random random = new Random();

        Integer[] input = new Integer[10000];
        for (int i = 0; i < 10000; i++)
        {
            input[i] = random.nextInt(10000);
        }

        //use buildHeap()
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(input); //O(n), not O(nlogn)

        while (!queue.isEmpty())
        {
            System.out.println(queue.remove());
        }
    }
}
