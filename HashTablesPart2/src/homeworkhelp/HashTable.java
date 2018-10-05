package homeworkhelp;

import java.util.Iterator;

/**
 * Example hash table class using linear probing.
 *
 * @param <T> the type of elements stored in the table
 * @author Josh Archer
 * @version 1.0
 */
public class HashTable<T> implements Iterable<T>, ICollection<T>
{
    private static final int INITIAL_TABLE_SIZE = 10;
    private static final double MAX_LOAD_FACTOR = 0.6;
    private static final double RESIZE_RATE = 2.0;

    private HashTableElement<T>[] table;
    private int size;

    /**
     * Creates a new hash table.
     */
    public HashTable()
    {
        table = new HashTableElement[INITIAL_TABLE_SIZE];
    }

    @Override
    public void add(T element)
    {
        //have we exceeded our load factor
        if (exceededLoadFactor())
        {
            rehash();
        }

        int code = Math.abs(element.hashCode());
        int index = code % table.length;

        //check for collisions
        while (table[index] != null)
        {
            //stop if the element is already in the table
            if (table[index].data.equals(element))
            {
                //there is a duplicate
                if (table[index].isActive)
                {
                    return;
                }
                else //the element is here, but was previously deleted
                {
                    //add the new element at this position
                    table[index].data = element;
                    table[index].isActive = true;
                    size++;
                    return;
                }
            }

            //otherwise move to the next element
            index = index + 1;
            index = index % table.length;
        }

        table[index] = new HashTableElement<>(element, true);
        size++;
    }

    private boolean exceededLoadFactor()
    {
        return (double) size / table.length >= MAX_LOAD_FACTOR;
    }

    private void rehash()
    {
        //save the data
        HashTableElement<T>[] oldTable = table;

        //set our size and usedSpaces to zero so that add() below
        //can start anew
        size = 0;

        //reassign our table array
        table = new HashTableElement[(int)(oldTable.length * RESIZE_RATE)];

        //loop over each element that is active and add() it
        for (int i = 0; i < oldTable.length; i++)
        {
            if (oldTable[i] != null && oldTable[i].isActive)
            {
                add(oldTable[i].data);
            }
        }
    }

    @Override
    public boolean contains(T element)
    {
        int index = find(element);

        if (index != -1)
        {
            return table[index].isActive;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void remove(T element)
    {
        int index = find(element);

        if (index != -1)
        {
            if (table[index].isActive)
            {
                table[index].isActive = false;
                size--;
            }
        }
    }

    @Override
    public T get(T element)
    {
        int index = find(element);

        return index == -1 ? null : table[index].data;
    }

    //returns the index at which the element was found (regardless of
    //whether it is active or not!)
    private int find(T element)
    {
        int code = Math.abs(element.hashCode());
        int index = code % table.length;

        while (table[index] != null)
        {
            //if the element matches and is active
            if (table[index].data.equals(element))
            {
                return index;
            }

            index = (index + 1) % table.length;
        }

        return -1;
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
        table = new HashTableElement[INITIAL_TABLE_SIZE];
    }

    @Override
    public Iterator<T> iterator()
    {
        return new TableIterator(table);
    }

    private class TableIterator implements Iterator<T>
    {
        private HashTableElement<T>[] table;
        private int nextIndex = -1;

        public TableIterator(HashTableElement<T>[] table)
        {
            this.table = table;
            findNext();
        }

        @Override
        public boolean hasNext()
        {
            return nextIndex != -1;
        }

        @Override
        public T next()
        {
            Object nextElement = table[nextIndex].data;
            findNext();
            return (T)nextElement;
        }

        private void findNext()
        {
            for (int i = nextIndex + 1; i < table.length; i++)
            {
                if (table[i] != null && table[i].isActive)
                {
                    nextIndex = i;
                    return;
                }
            }

            nextIndex = -1;
        }

        public String toString()
        {
            return "Iterator: hasNext - " + hasNext();
        }
    }

    private class HashTableElement<T>
    {
        private T data;
        private boolean isActive;

        public HashTableElement(T data, boolean isActive)
        {
            this.data = data;
            this.isActive = isActive;
        }

        public String toString()
        {
            if (!isActive)
            {
                return "Inactive";
            }
            else
            {
                return data.toString();
            }
        }
    }

    @Override
    public String toString()
    {
        return "HashTable: size = " + size();
    }
}