package EJB;

import Objects.loanModel;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@Stateless(name = "BookOracleEJB")
public class BookOracleBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

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
}
