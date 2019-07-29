

import models.Book;
import models.OrganizedBook;
import models.Room;
import repositories.BookRepository;
import repositories.Globals;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {


        /**
         * Exercise 2
         */
        Book book = new Book();

        List<String> authors = new ArrayList<String>();
        authors.add("Robert Pressman");
        authors.add("Test Author");
        book.setAuthors(authors);
        book.setPublicationYear(2019);
        book.setPublisher("McGrawHill");
        book.setTitle("Software for dummies");


        String input="Book:" +
                "Author: Brian Jensen\n" +
                "Title: Texts from Denmark\n" +
                "Publisher: Gyldendal\n" +
                "Published: 2001" +
                "Book:\n" +
                "Author: Peter Jensen\n" +
                "Author: Hans Andersen\n" +
                "Title: Stories from abroad\n" +
                "Publisher: Borgen\n" +
                "Published: 2012";

        BookRepository bookRepository = new BookRepository();

        List<Book> readBooks =bookRepository.readBooks(input);

        bookRepository.setBooks(readBooks);

        System.out.println(readBooks.size());

        List<Book> foundBooks=bookRepository.findBooks("*20*&*peter*");

        System.out.println(foundBooks.size());

        /**
         * Exercise 3
         */

        OrganizedBook organizedBook=new OrganizedBook();
        organizedBook.setISBN("TresBien");
        List<OrganizedBook> organizedBooks= new ArrayList<>();
        organizedBooks.add(organizedBook);
        Room room = new Room();
        room.setId(1);

        List<OrganizedBook> books = room.returnOrganizedBooksByNumber(1);


        OrganizedBook searched = room.locateBookByISBN("TresBien");
        System.out.println(searched.getISBN());



    }

}