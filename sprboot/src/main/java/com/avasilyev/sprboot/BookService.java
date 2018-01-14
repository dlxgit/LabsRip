package com.avasilyev.sprboot;

import com.avasilyev.sprboot.database.DatabaseHelper;
import com.avasilyev.sprboot.model.Book;
import com.avasilyev.sprboot.util.ApplicationUtils;
import com.avasilyev.sprboot.util.FileEventLogger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class BookService {


  public List<Book> getAllBooks() {
    List<Book> books = null;
    try {
      books = DatabaseHelper.readBooks();
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return books;
  }

  public void addBook() {
    FileEventLogger.logEvent("post request: " + req);
    try {
      Book book = new Book(
        req.getParameter("book"),
        req.getParameter("author"),
        ApplicationUtils.DATE_FORMAT.parse(req.getParameter("date")),
        req.getParameter("genre"),
        Integer.parseInt(req.getParameter("rating"))
      );
      DatabaseHelper.addBook(book);
    } catch (ParseException e) {
      System.out.println("Err occured");
      e.printStackTrace();
    }
    //resetpage
  }
}
