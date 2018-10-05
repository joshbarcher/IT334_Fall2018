package launch;

import entities.Book;
import entities.Book.BookType;
import hashing.BookLookupTable;

public class TestLookupTable
{
    public static void main(String[] args)
    {
        //create a few books
        Book gatsby = new Book("F. Scott Fitzgerald",
                "The Great Gatsby", BookType.HARDCOVER);
        Book goneWithTheWind = new Book("Margaret Mitchell",
                "Gone with the Wind", BookType.SOFTCOVER);
        Book warAndPeace = new Book("Leo Tolstoy", "War and Peace",
                BookType.HARDCOVER);

        //store a few in our table
        BookLookupTable table = new BookLookupTable();
        table.addBook(gatsby);
        table.addBook(goneWithTheWind);
        table.addBook(warAndPeace);

        //see if we can find the books in our table
        Book gatsbyBook = new Book("F. Scott Fitzgerald",
                "The Great Gatsby", BookType.SOFTCOVER);
        Book retrievedBook = table.getBook(gatsbyBook);
        System.out.println(retrievedBook);

        System.out.println("contains(gatsby): " + table.contains(gatsbyBook));
        System.out.println("contains(fellowship): " + table.contains(new Book(
                "J.R.R. Tolkien", "The Fellowship of the Ring",
                BookType.HARDCOVER)));
    }
}
