package examples;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PriorityQueue<T extends Comparable<T>> implements Queue<T>
{
    private static final int INITIAL_SIZE = 10;

    //complete binary tree
    private T[] binaryHeap;

    //counters
    private int size;
    private int nextIndex = 1; //start at index 1

    public PriorityQueue()
    {
        //use a default size for the complete binary tree
        binaryHeap = (T[]) new Comparable[INITIAL_SIZE + 1];
    }

    public PriorityQueue(T[] array)
    {
        //copy over all elements from the input array to a new array
        binaryHeap = (T[]) new Comparable[array.length + 1];

        for (int i = 0; i < array.length; i++)
        {
            //take into account the first empty index in binaryHeap[0]
            binaryHeap[i + 1] = array[i];
        }

        //housekeeping
        size = array.length;
        nextIndex = size + 1;

        //build heap operation - O(n)
        buildHeap();
    }

    @Override
    public boolean add(T newElement) //insert() operation
    {
        //have we run out of space in our internal array?
        if (nextIndex == binaryHeap.length)
        {
            resize(); //make some more room in our array
        }

        //place the element at the end of the complete binary tree and percolate up
        binaryHeap[nextIndex] = newElement;
        percolateUp(nextIndex);

        //move to the next index and increment the size
        nextIndex++;
        size++;

        return true;
    }

    @Override
    public T peek() //findMin() operation
    {
        if (size == 0)
        {
            throw new NoSuchElementException("The heap is empty!");
        }

        return binaryHeap[1];
    }

    @Override
    public T remove() //deleteMin() operation
    {
        //make sure we don't call remove() with no elements
        if (isEmpty())
        {
            throw new NoSuchElementException("The heap is empty");
        }

        //save our element to return
        T element = binaryHeap[1];

        //move the last element in the heap (size) to the first spot
        swap(1, size);

        //alter our indices
        size--;
        nextIndex--;

        //percolate our element to the correct position
        percolateDown(1);

        return element;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public void clear()
    {
        size = 0;
        nextIndex = 1;
        binaryHeap = (T[]) new Comparable[INITIAL_SIZE + 1];
    }

    //private heap methods

    private void buildHeap()
    {
        //start with the highest index node with a child node
        for (int i = size / 2; i >= 1; i--)
        {
            percolateDown(i);
        }
    }

    private void percolateUp(int index)
    {
        //continue as long as the current index is not equal to the root
        while (index > 1)
        {
            int parentIndex = index / 2;

            //check whether the parent and child node are out of order
            if (binaryHeap[index].compareTo(binaryHeap[parentIndex]) < 0)
            {
                swap(index, parentIndex);
            }

            //move up to the parent and do the same thing
            index = parentIndex;
        }
    }

    private void percolateDown(int index)
    {
        //the last node with a child node is index at size / 2
        while (index <= size / 2)
        {
            //get child indices
            int left = 2 * index;
            int right = 2 * index + 1;

            //determine which child is the smallest
            int indexToCheck = left;

            //if we have a right child, and the right child is smaller
            if (right < size && binaryHeap[right].compareTo(binaryHeap[left]) < 0)
            {
                indexToCheck = right;
            }

            //is the parent smaller than the smallest child
            if (binaryHeap[indexToCheck].compareTo(binaryHeap[index]) < 0)
            {
                //swap
                swap(indexToCheck, index);

                //move to that child node and do the same
                index = indexToCheck;
            }
            else
            {
                break; //exit our loop, heap order is satisfied
            }
        }
    }

    private void resize()
    {
        //double the size of our array
        T[] newBinaryHeap = (T[]) new Comparable[binaryHeap.length * 2];

        //copy across elements to the new heap
        for (int i = 0; i < binaryHeap.length; i++)
        {
            newBinaryHeap[i] = binaryHeap[i];
        }

        //replace the old heap with the new heap
        binaryHeap  = newBinaryHeap;
    }

    private void swap(int first, int second)
    {
        T temp = binaryHeap[first];
        binaryHeap[first] = binaryHeap[second];
        binaryHeap[second] = temp;
    }

    //ignore methods below this

    @Override
    public boolean contains(Object element)
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Iterator<T> iterator()
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Object[] toArray()
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public <T1> T1[] toArray(T1[] array)
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public boolean remove(Object element)
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public boolean containsAll(Collection<?> collection)
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public boolean addAll(Collection<? extends T> collection)
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public boolean removeAll(Collection<?> collection)
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public boolean offer(T element)
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public T poll()
    {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public T element()
    {
        throw new UnsupportedOperationException("Operation not supported");
    }
}