package repositories;

import data.DB;
import models.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepositoryDraft implements IBookRepositoryDraft {
    private final DB db;
    public BookRepositoryDraft(DB db) {
        this.db = db;
    }
    @Override
    public Book searchByName(String name) {
        Connection connection = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connection = db.getConnection();

            String sql = "SELECT * FROM books WHERE title ILIKE ?";
            st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");

            rs = st.executeQuery();

            if (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getBoolean("is_borrowed"),
                        rs.getString("author")
                );
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Error while searching for book by name: " + name);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }



    @Override
    public void borrowBook(String name) {

    }

    @Override
    public void returnBook(String name) {

    }


}
