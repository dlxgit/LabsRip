package servlet;

import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet{
    List<Book> books;
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);

    public MyFirstServlet() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGetWorks");
        if (books == null) {
            resetPage(req,resp);
            req.setAttribute("firstPage", true);
            return;
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        for(Book b : books) {
            out.println("<h1>" + b.toString() + "</h1>");
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPostWorks");

        try {
            Book book = new Book(
                      req.getParameter("name")
                    , req.getParameter("author")
                    , DATE_FORMAT.parse(req.getParameter("date"))
                    , req.getParameter("genre")
                    , Integer.parseInt(req.getParameter("rating"))
            );
            if (books == null) {
                books = new LinkedList<Book>();
            }
            books.add(book);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        resetPage(req, resp);
    }

    private void resetPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print(getBasePageHtml());
        out.flush();
    }

    private String getBasePageHtml() {
        return  "<page contentType=\"text/html;charset=UTF-8\">" +
                "<html>" +
                "  <head>" +
                "    <title>$Title$</title>" +
                "  </head>" +
                "  <body>" +
                "    <form method=\"post\" action=\"MyFirstServlet\">" +
                "                Name: <input type=\"text\" name=\"name\">" +
                "                Author: <input type=\"text\" name=\"author\">" +
                "                Date: <input type=\"text\" name=\"date\">" +
                "                Genre: <input type=\"text\" name=\"genre\">" +
                "                Rating: <input type=\"text\" name=\"rating\">" +
                "      <button>Add</button>" +
                "    </form>" +
                "    <form action=\"/MyFirstServlet\">" +
                "      <button>Show all books</button>" +
                "    </form>" +
                "  </body>" +
                "</html>";
    }
}