package EJB;

import Objects.bookModel;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.sql.*;
import java.util.ArrayList;

@Stateless(name = "BookOracleEJB")
public class BookOracleBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

    public ArrayList<bookModel> getBooks() {
        String query = "SELECT b.BOOK_ID BOOK_ID," +
                "b.TITLE TITLE," +
                "b.ISBN ISBN," +
                "b.PAGES PAGES," +
                "b.CREATED CREATED," +
                "b.ON_LOAN ON_LOAN," +
                "a.FIRST_NAME AUTHOR_FIRST_NAME," +
                "a.LAST_NAME AUTHOR_LAST_NAME FROM books b " +
                "INNER JOIN authors a ON b.author_id = a.author_id";
        ArrayList books_list = new ArrayList();
        Statement stmt = null;

        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                bookModel book = new bookModel();
                book.setBookId(rs.getLong("BOOK_ID"));
                book.setOnLoan(rs.getBoolean("ON_LOAN"));
                book.setBookTitle(rs.getString("TITLE"));
                book.setBookIsbn(rs.getLong("ISBN"));
                book.setBookPages(rs.getInt("PAGES"));
                book.setCreated(rs.getDate("CREATED"));
                book.setAuthorFirstName(rs.getString("AUTHOR_FIRST_NAME"));
                book.setAuthorLastName(rs.getString("AUTHOR_LAST_NAME"));
                books_list.add(book);
            }

            stmt.close();
            return books_list;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<bookModel> getAvailableBooks() {
        String query = "SELECT b.BOOK_ID BOOK_ID," +
                "b.TITLE TITLE," +
                "b.ISBN ISBN," +
                "b.PAGES PAGES," +
                "b.CREATED CREATED," +
                "b.ON_LOAN ON_LOAN," +
                "a.FIRST_NAME AUTHOR_FIRST_NAME," +
                "a.LAST_NAME AUTHOR_LAST_NAME FROM books b " +
                "INNER JOIN authors a ON b.author_id = a.author_id " +
                "WHERE b.ON_LOAN = 0";

        ArrayList books_list = new ArrayList();
        Statement stmt = null;

        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                bookModel book = new bookModel();
                book.setBookId(rs.getLong("BOOK_ID"));
                book.setOnLoan(rs.getBoolean("ON_LOAN"));
                book.setBookTitle(rs.getString("TITLE"));
                book.setBookIsbn(rs.getLong("ISBN"));
                book.setBookPages(rs.getInt("PAGES"));
                book.setCreated(rs.getDate("CREATED"));
                book.setAuthorFirstName(rs.getString("AUTHOR_FIRST_NAME"));
                book.setAuthorLastName(rs.getString("AUTHOR_LAST_NAME"));
                books_list.add(book);
            }

            stmt.close();
            return books_list;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void markAsReturned(int bookId) {
        Connection con = oracleClientProviderBean.getOracleClient();
        String insertLoan = "UPDATE books SET on_loan = 0 WHERE book_id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertLoan)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle any potential exceptions here
            e.printStackTrace();
        }
    }

    public void markAsBorrowed(int bookId) {
        Connection con = oracleClientProviderBean.getOracleClient();
        String insertLoan = "UPDATE books SET on_loan = 1 WHERE book_id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertLoan)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle any potential exceptions here
            e.printStackTrace();
        }
    }
}
