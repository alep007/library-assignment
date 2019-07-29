package test;

import models.Bookshelf;
import models.OrganizedBook;
import models.Room;
import models.Row;

import java.util.List;

public class Exercise3Test {

    public static void main(String[] args) {
        /**
         * Creating Data Test
         */
        Room room = new Room();
        room.setId(1);

        Bookshelf bookshelf = new Bookshelf();
        bookshelf.setId(10);

        Row row = new Row();
        row.setId(100);

        for (int i = 0; i < 15; i++) {
            OrganizedBook organizedBook = new OrganizedBook();
            organizedBook.setISBN("TestCase:" + i);

            if (i < 5) {
                room.registerOrganizedBook(organizedBook);
            }

            if (i >= 5 && i < 10) {
                bookshelf.registerOrganizedBook(organizedBook);
            }

            if (i >= 10) {
                row.registerOrganizedBook(organizedBook);
            }
        }

        /**
         * Testing
         */

        //Return Books from a given Room number
        Boolean testCase1 = false;
        List<OrganizedBook> booksInRoom = room.returnOrganizedBooksByNumber(1);
        if (booksInRoom.size() > 0) {
            testCase1 = true;
        }
        if (testCase1)
            System.out.println("TEST 1 PASSED");

        // Return null if room doesnt exists

        Boolean testCase2 = false;
        booksInRoom = room.returnOrganizedBooksByNumber(1002);
        if (booksInRoom == null) {
            testCase2 = true;
        }
        if (testCase2)
            System.out.println("TEST 2 PASSED");


        // Search a book in a room
        OrganizedBook searched = room.locateBookByISBN("TestCase:40");
        if (searched != null) {
            System.out.println(searched.getISBN());
            System.out.println("TEST 3 PASSED");
        } else {
            System.out.println("TEST 3 FAILED");
        }


        //Search a book in a bookshelf

        OrganizedBook searchedBookshelf = bookshelf.locateBookByISBN("TestCase:6");
        if (searchedBookshelf != null) {
            System.out.println(searchedBookshelf.getISBN());
            System.out.println("TEST 4 PASSED");
        } else {
            System.out.println("TEST 4 FAILED");
        }


        //Return list of books on a Row

        List<OrganizedBook> booksInRow = row.returnOrganizedBooksByNumber(100);
        if (booksInRow != null) {
            System.out.println("TEST 5 PASSED");
        } else {
            System.out.println("TEST 5 FAILED");
        }

    }


}
