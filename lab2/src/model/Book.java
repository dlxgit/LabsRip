package model;

import servlet.MyFirstServlet;

import java.util.Date;

public class Book {
    private String name;
    private String author;
    private Date date;
    private String genre;
    private int rating;

    public Book(String name, String author, Date date, String genre, int rating) {
        this.name = name;
        this.author = author;
        this.date = date;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return    "Book: " + name
                + "<p>author: " + author + "</p>"
                + "<p>date: " + MyFirstServlet.DATE_FORMAT.format(date) + "</p>"
                + "<p>genre: " + genre + "</p>"
                + "<p>rating: " + rating + "</p>"
                + "<p>_______________</p>";
    }
}
