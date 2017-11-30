package database;

import model.Book;
import util.ApplicationUtils;
import util.FileEventLogger;

import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class DatabaseHelper {
    public static final String url = "jdbc:mysql://localhost:3306/books";
    public static final String user = "root";
    public static final String password = "qwerty";

    private static Connection con;

    public DatabaseHelper() {
    }

    public static void addBook(Book book) {

        FileEventLogger.logEvent("Adding new book: " + book.toString());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO Books (name, author, date, genre, rating) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1,book.getName());
            stmt.setString(2, book.getAuthor());
            stmt.setDate(3,new java.sql.Date(book.getDate().getTime()));
            stmt.setString(4,book.getGenre());
            stmt.setInt(5,book.getRating());
            stmt.addBatch();
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Book> readBooks() throws ParseException {
        if (con == null) {
            try {
                con = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                FileEventLogger.logEvent(e.getMessage());
            }
        }
        FileEventLogger.logEvent("Reading books from database.");
        ResultSet rs = null;
        try {
            rs = queryRead("select name, author, date, genre, rating from books");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        }
        List<Book> result = new LinkedList<Book>();
        try {
            while (rs.next()) {
                String name = rs.getString(1);
                String author = rs.getString(2);
                Date date = ApplicationUtils.fromDatabase(String.valueOf(rs.getDate(3)));
                String genre = rs.getString(4);
                int rating = rs.getInt(5);

                result.add(new Book(name,author,date,genre,rating));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        }
        FileEventLogger.logEvent("Reading successful!.");
        return result;
    }


    private static ResultSet queryRead(String query) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        return con.createStatement().executeQuery(query);
    }
}