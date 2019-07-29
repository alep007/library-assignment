package models;

public class OrganizedBook extends Book {

    private String ISBN;

    public OrganizedBook(){

    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
