package com.avasilyev.sprboot.web;

import com.avasilyev.sprboot.repository.BookRepository;
import com.avasilyev.sprboot.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.util.Map;

@Controller
public class MainController {

  @Autowired
  private BookRepository bookRepository;

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public String getCarsList(Map<String, Object> model) {
    model.put("books", bookRepository.findAll());
    model.put("book", new Book());
    model.put("textFromUrl", getText());

    return "index";
  }

  @RequestMapping(value = "/index", method = RequestMethod.POST)
  String hm(@ModelAttribute Book book) {
    bookRepository.save(book);
    return "redirect:/index";
  }

  private String getText() {
    final String urlStr = "https://example.com";
    String content = "";

    try {
      StringBuilder contentBuilder = new StringBuilder();
      try {
        URL url = new URL(urlStr);
        BufferedReader in = new BufferedReader(
          new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
          content += inputLine;
        in.close();
      } catch (IOException e) {
        content = e.getMessage();
      }
    } catch (Exception err) {
      content = err.getMessage();
    }

    return content;
  }
}