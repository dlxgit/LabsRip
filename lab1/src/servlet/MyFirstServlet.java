package servlet;

import model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/MyFirstServlet")
public class MyFirstServlet extends HttpServlet{
    List<Book> books;

    public MyFirstServlet() {
        this.books = new LinkedList<Book>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        for(Book b : books) {
            out.println("<h1>" + b.toString() + "</h1>");
        }
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book.Builder()
                .setName(req.getParameter("bookName"))
                .setAuthor(req.getParameter("bookAuthor"))
                .setYear(req.getParameter("bookYear"))
                .setGenre(req.getParameter("bookGenre"))
                .build();
        books.add(book);

        resetPage(req, resp);
    }

    private void resetPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}