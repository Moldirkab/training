package repositories;
import data.DB;
import models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final Connection connection;

    public BookRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM \"Books\" Order by book_id ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("is_borrowed")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public Book getBookById(int id) {
        String query = "SELECT * FROM \"Books\" WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getInt("book_id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getBoolean("is_borrowed")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching book by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean addBook(Book book) {
        String query = "INSERT INTO \"Books\" (title, author, is_borrowed) VALUES (?,?,?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setBoolean(3, book.isBorrowed());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void updateBook(Book book) {
        String query = "UPDATE \"Books\" SET title = ?, author = ?, is_borrowed = ? WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setBoolean(3, book.isBorrowed());
            pstmt.setInt(4, book.getBookId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(int id) {
        String query = "DELETE FROM \"Books\" WHERE book_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            if(pstmt.executeUpdate()>0){
                System.out.println("Book deleted successfully");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
}
