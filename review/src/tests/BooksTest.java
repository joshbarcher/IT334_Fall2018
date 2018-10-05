package tests;

import entities.Book;
import entities.Book.BookType;
import hashing.BookLookupTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BooksTest
{
    private BookLookupTable table;

    @Before
    public void setup()
    {
        table = new BookLookupTable();
    }

    @Test
    public void testAddingElements()
    {
        //create a book and add it
        Book fellowship = new Book(
                "J.R.R. Tolkien", "The Fellowship of the Ring",
                BookType.HARDCOVER);
        Book harryPotter = new Book("J.K. Rowling", "The Philosopher's Stone",
                BookType.SOFTCOVER);

        Book tolkien = new Book(
                "J.R.R. Tolkien", "The Fellowship of the Ring",
                BookType.SOFTCOVER);

        table.addBook(fellowship);

        //test whether we can find an existing element
        Assert.assertTrue("Cannot find a book in the table",
                table.contains(fellowship));

        //test whether a book with a different type is still found
        Assert.assertTrue("Cannot find a book that is in the table with a " +
                        "different type in the table",
                table.contains(tolkien));

        //test whether we can find a missing element
        Assert.assertFalse("Can find a non-existent element in the table",
                table.contains(harryPotter));
    }

    @Test
    public void testContains()
    {
        //TODO: do this later...
    }
}
