package com.avasilyev.sprboot.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer id;

  private String name;

  private String author;

  @DateTimeFormat(pattern="yyyy-mm-dd")
  private Date date;

  private String genre;

  private int rating;

  public Book() {};

  public Book(String name, String author, Date date, String genre, int rating) {
    this.name = name;
    this.author = author;
    this.date = date;
    this.genre = genre;
    this.rating = rating;
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
