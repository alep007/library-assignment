package repositories;


import models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookRepository {

    public List<Book> books;

    public BookRepository() {

    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    /**
     * @param input it has to follow a certain pattern to work properly
     * @return a List of Book
     */
    public List<Book> readBooks(String input) {
        List<Book> readBooks = new ArrayList<>();

        if (input.equals(""))
            return readBooks;

        String[] params = input.split(Globals.BOOK_PATTERN);

        for (String param : params) {
            if (param.equals(""))
                continue;
            Book aBook = new Book();
            List<String> authors = new ArrayList<>();
            String[] properties = param.split(Globals.PROPERTY_PATTERN);
            for (String property : properties) {
                String[] values = property.split(Globals.VALUE_PATTERN);
                switch (values[0]) {
                    case Globals.AUTHOR_ATTRIBUTE:
                        authors.add(values[1]);
                        break;
                    case Globals.TITLE_ATTRIBUTE:
                        aBook.setTitle(values[1]);
                        break;
                    case Globals.PUBLISHER_ATTRIBUTE:
                        aBook.setPublisher(values[1]);
                        break;
                    case Globals.PUBLISHED_ATTRIBUTE:
                        aBook.setPublicationYear(Integer.valueOf(values[1]));
                        break;
                }
            }
            aBook.setAuthors(authors);
            readBooks.add(aBook);
        }
        return readBooks;
    }


    /**
     * @param searchString accepts wildcards searchs (*value*) and multiple words using (&)
     * @return a List of Book that matches the searchString
     */
    public List<Book> findBooks(String searchString) {

        List<Book> foundBooks = new ArrayList<>();
        String[] combinations = null;
        if (searchString.equals(""))
            return foundBooks;

        searchString = searchString.replaceAll(Globals.WILDCARD_PATTERN, "");
        if (searchString.contains(Globals.COMBINATION_PATTERN)) {
            combinations = searchString.split(Globals.COMBINATION_PATTERN);
        }
        if (combinations != null) {
            for (Book aBook : books) {
                int timesMatched = 0;
                for (String combination : combinations) {
                    Pattern searchPattern = Pattern.compile(combination, Pattern.CASE_INSENSITIVE);
                    Boolean canContinue = this.searchBook(aBook, searchPattern);
                    if (!canContinue)
                        break;
                    else
                        timesMatched++;
                }
                if (timesMatched == combinations.length)
                    foundBooks.add(aBook);
            }
        } else {
            Pattern searchPattern = Pattern.compile(searchString, Pattern.CASE_INSENSITIVE);

            for (Book aBook : books) {
                Boolean isFound = this.searchBook(aBook, searchPattern);
                if (isFound)
                    foundBooks.add(aBook);
            }
        }
        return foundBooks;
    }

    /**
     * @param aBook
     * @param searchPattern
     * @return Boolean
     */
    private Boolean searchBook(Book aBook, Pattern searchPattern) {
        Boolean isFound = false;
        Matcher matcher;
        for (String author : aBook.getAuthors()) {
            matcher = searchPattern.matcher(author);
            if (matcher.find()) {
                isFound = true;
                break;
            }
        }
        matcher = searchPattern.matcher(aBook.getTitle());
        if (matcher.find()) {
            isFound = true;
        }
        matcher = searchPattern.matcher(aBook.getPublisher());
        if (matcher.find()) {
            isFound = true;
        }
        matcher = searchPattern.matcher(String.valueOf(aBook.getPublicationYear()));
        if (matcher.find()) {
            isFound = true;
        }
        return isFound;
    }
}
