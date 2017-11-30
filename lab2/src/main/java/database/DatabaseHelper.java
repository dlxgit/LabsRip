package database;

import model.Book;
import util.ApplicationUtils;
import util.FileEventLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class DatabaseHelper {
    public static final String url = "jdbc:mysql://localhost:3306/books";
    public static final String user = "root";
    public static final String password = "qwerty";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public DatabaseHelper() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        }
    }

    public static void addBook(Book book) {
        FileEventLogger.logEvent("Adding new book: " + book.toString());
        if (exists(book)) {
            FileEventLogger.logEvent("Book " + book.toString() + " already exists.");
            return;
        }

        try {
            queryInsert("INSERT INTO Books (name, author, date, genre, rating) VALUES ('"
                    + book.getName() + "','"
                    + book.getAuthor() + "',"
                    + "STR_TO_DATE('" + ApplicationUtils.formatDate(book.getDate()) + "','%d.%m.%Y'),'"
                    + book.getGenre() + "'," +
                    + book.getRating() + ");");
        } catch (SQLException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        }
    }

    public static List<Book> readBooks() throws ParseException {
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
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        return rs;
    }

    private static void queryInsert(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(url, user, password);
        stmt = con.createStatement();
        stmt.executeUpdate(query);

        if (con != null) {
            con.close();
        }
        if (con != null) {
            stmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    private static boolean exists(Book book) {
        ResultSet rs = null;
        try {
            rs = queryRead("SELECT * FROM Books WHERE name='" + book.getName() + "' and author='" + book.getAuthor() + "';");
            return rs.next();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            FileEventLogger.logEvent(e.getMessage());
        }
        return false;
    }
}