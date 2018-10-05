package hashing;

import entities.Book;

public class BookLookupTable
{
    private static final int INIT_SIZE = 10;
    private Book[] table;
    private int size;

    public BookLookupTable()
    {
        table = new Book[INIT_SIZE];
    }

    public void addBook(Book newBook)
    {
        //make sure we have room for another book
        if (size == table.length)
        {
            return; //exit...
        }

        //use our hashcode to find a place in the table for newBook
        int index = Math.abs(newBook.hashCode() % table.length);

        //be careful that we have an open spot in the table (linear probing)
        while (table[index] != null)
        {
            //this could be a duplicate element
            if (table[index].equals(newBook))
            {
                return; //exit...
            }

            //move to the next index and then check again
            index = (index + 1) % table.length;
        }

        table[index] = newBook;
        size++;
    }

    public boolean contains(Book book)
    {
        return getBook(book) != null;
    }

    public Book getBook(Book book)
    {
        int index = Math.abs(book.hashCode() % table.length);

        while (table[index] != null)
        {
            if (table[index].equals(book))
            {
                return table[index]; //we did find the element
            }

            //move to the next index and then check again
            index = (index + 1) % table.length;
        }

        return null; //we didn't find the element
    }
}
