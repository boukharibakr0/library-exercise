package org.example.model;

public class Book {

    private String id;
    private String title;
    private String author;
    private String isbn;
    private int totalCopies;
    private int availableCopies;

    public Book(String id, String title, String author, String isbn, int totalCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getId()               { return id; }
    public String getTitle()            { return title; }
    public String getAuthor()           { return author; }
    public String getIsbn()             { return isbn; }
    public int getTotalCopies()         { return totalCopies; }
    public int getAvailableCopies()     { return availableCopies; }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return String.format("Book{id='%s', title='%s', author='%s', available=%d/%d}",
                id, title, author, availableCopies, totalCopies);
    }
}
