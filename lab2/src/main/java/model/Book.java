package model;

import util.ApplicationUtils;

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
                + "<p>date: " + ApplicationUtils.DATE_FORMAT.format(date) + "</p>"
                + "<p>genre: " + genre + "</p>"
                + "<p>rating: " + rating + "</p>"
                + "<p>_______________</p>";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
