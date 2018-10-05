package homeworkhelp;

import java.util.Iterator;

/**
 * Represents a simple collection of objects. The interface
 * does not include any functionality related to indices of
 * an underlying structure.
 *
 * DO NOT ALTER THIS FILE!
 *
 * @author Josh Archer
 * @version 1.0
 */
public interface ICollection<T> extends Iterable<T>
{
    /**
     * Adds an element to the collection. No specific ordering
     * is required.
     *
     * @param element the new element to put in the collection
     */
    public void add(T element);

    /**
     * Finds and removes an element from the collection.
     *
     * @throws java.util.NoSuchElementException thrown when the
     * element is not found in the collection
     * @param element the element to remove
     */
    public void remove(T element);

    /**
     * Reports whether the collection contains an element.
     *
     * @param element the element to search for.
     * @return true if the element is found, otherwise false
     */
    public boolean contains(T element);

    /**
     * Returns the number of elements in the collection.
     *
     * @return the number of elements
     */
    public int size();

    /**
     * Reports whether the collection is empty or not.
     *
     * @return true if the collection is empty, otherwise false
     */
    public boolean isEmpty();

    /**
     * Removes all elements from the collection.
     */
    public void clear();

    /**
     * Returns an element in the collection that matches the
     * input parameter according the equals() method of the
     * parameter.
     *
     * @param element an element to search for
     * @return a matching element
     */
    public T get(T element);

    /**
     * Returns an iterator over the collection.
     *
     * @return an object using the Iterator<T> interface
     */
    public Iterator<T> iterator();
}