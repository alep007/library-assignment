package interfaces;

import models.OrganizedBook;

import java.util.List;

public interface IBaseStorage {

    void registerOrganizedBook(OrganizedBook books);

    List<OrganizedBook> returnOrganizedBooksByNumber(int number);

    OrganizedBook locateBookByISBN(String ISBN);
}
