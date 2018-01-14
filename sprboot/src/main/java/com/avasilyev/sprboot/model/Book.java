package com.avasilyev.sprboot.model;



import com.avasilyev.sprboot.util.ApplicationUtils;

import java.util.Date;

/**
 * Book model
 */
public class Book {
  /**
   * name
   */
  private String name;
  /**
   * author
   */
  private String author;
  /**
   * date.
   */
  private Date date;
  /**
   * genre
   */
  private String genre;
  /**
   * rating
   */
  private int rating;

  /**
   * @param name    name of Book
   * @param author  author of Book
   * @param date    date of Book
   * @param genre   genre of Book
   * @param rating  rating of Book
   */
  public Book(String name, String author, Date date, String genre, int rating) {
    this.name = name;
    this.author = author;
    this.date = date;
    this.genre = genre;
    this.rating = rating;
  }

  /**
   * @return string value of object
   */
  @Override
  public String toString() {
    return "Book: " + name
      + "<p>author: " + author + "</p>"
      + "<p>date: "
      + ApplicationUtils.DATE_FORMAT.format(date) + "</p>"
      + "<p>genre: " + genre + "</p>"
      + "<p>rating: " + rating + "</p>"
      + "<p>_______________</p>";
  }

  /**
   * @return getter
   */
  public String getName() {
    return name;
  }

  /**
   * @param name setter
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return setter
   */
  public String getAuthor() {
    return author;
  }

  /**
   * @param author setter
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * @return getter
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date setter
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return getter
   */
  public String getGenre() {
    return genre;
  }

  /**
   * @param genre setter
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * @return getter
   */
  public int getRating() {
    return rating;
  }

  /**
   * @param rating setter
   */
  public void setRating(int rating) {
    this.rating = rating;
  }
}
