package servlet;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import database.DatabaseHelper;
import javafx.util.Pair;
import model.Book;
import util.ApplicationUtils;
import util.FileEventLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet{
    HashMap<String, String> templateMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileEventLogger.logEvent("get request: " + req.toString());

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        List<Book> books = null;
        try {
            books = DatabaseHelper.readBooks();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String output = "";
        for(Book b : books) {
            output += ("<h1>" + b.toString() + "</h1>");
        }
        templateMap.put("books", output);

        out.write(getBaseHtml().replace("{%%content%%}", output));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileEventLogger.logEvent("post request: " + req);
        try {
            Book book = new Book(
                    req.getParameter("book")
                    , req.getParameter("author")
                    , ApplicationUtils.DATE_FORMAT.parse(req.getParameter("date"))
                    , req.getParameter("genre")
                    , Integer.parseInt(req.getParameter("rating"))
            );
            DatabaseHelper.addBook(book);
        } catch (ParseException e) {
            System.out.println("Err occured");
            e.printStackTrace();
        }

        resetPage(req, resp);
    }

    private void resetPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.html");
        requestDispatcher.forward(req, resp);
    }

    private String getBaseHtml() {
        return "<html>" +
                "<head>" +
                "<title>Books</title>" +
                "</head>" +
                "<body>" +
                "{%%content%%}" +
                "</body>" +
                "</html>";
    }
}