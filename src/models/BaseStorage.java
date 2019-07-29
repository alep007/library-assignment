package models;

import interfaces.IBaseStorage;

import java.util.ArrayList;
import java.util.List;

public class BaseStorage implements IBaseStorage{

    private int id;
    private List<OrganizedBook> organizedBooks= new ArrayList<>();
    public BaseStorage() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrganizedBook> getOrganizedBooks() {
        return organizedBooks;
    }

    public void addOrganizedBook(OrganizedBook organizedBooks) {
        this.organizedBooks.add(organizedBooks);
    }

    @Override
    public void registerOrganizedBook(OrganizedBook book) {
        this.addOrganizedBook(book);
    }

    @Override
    public List<OrganizedBook> returnOrganizedBooksByNumber(int number) {
        if (this.getId() == number) {
            return this.getOrganizedBooks();
        } else {
            return null;
        }
    }

    @Override
    public OrganizedBook locateBookByISBN(String ISBN) {
        for (OrganizedBook organizedBook : this.getOrganizedBooks()) {
            if (organizedBook.getISBN().equals(ISBN)) {
                return organizedBook;
            }
        }
        return null;
    }
}
