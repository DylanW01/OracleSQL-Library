package EJB;

import Objects.loanModel;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.sql.*;

@Stateless(name = "LoanOracleEJB")
public class LoanOracleBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

    public void createLoan(loanModel loan) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(Calendar.DAY_OF_MONTH, 14);

        Date dateAfterTwoWeeks = calendar.getTime();
        Date test = new java.sql.Date(dateAfterTwoWeeks.getTime());

        String insertLoan = "INSERT INTO loans"
                + "(book_id, user_id, return_by, returned)" + "VALUES ("
                + "'" + loan.getBookId() + "',"
                + "'" + loan.getUserId() + "',"
                + "'" + test + "',"
                + "0,";

        Statement stmt = null;
        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();

            System.out.println(insertLoan);

            stmt.executeUpdate(insertLoan);

            stmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<loanModel> getLoans() {
        String query = "SELECT l.LOAN_ID LOAN_ID," +
                "l.BOOK_ID BOOK_ID," +
                "l.USER_ID USER_ID," +
                "l.RETURN_BY RETURN_BY," +
                "l.RETURNED_ON RETURNED_ON," +
                "l.RETURNED RETURNED," +
                "u.FIRST_NAME FIRST_NAME," +
                "u.LAST_NAME LAST_NAME," +
                "u.EMAIL EMAIL," +
                "b.TITLE TITLE," +
                "b.ISBN ISBN," +
                "b.PAGES PAGES," +
                "a.FIRST_NAME AUTHOR_FIRST_NAME," +
                "a.LAST_NAME AUTHOR_LAST_NAME FROM loans l " +
                "INNER JOIN library_users u ON l.user_id = u.user_id " +
                "INNER JOIN books b ON b.book_id = l.book_id " +
                "INNER JOIN authors a ON b.author_id = a.author_id";
        ArrayList loans_list = new ArrayList();
        Statement stmt = null;

        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                loanModel loan = new loanModel();
                loan.setLoanId(rs.getLong("LOAN_ID"));
                loan.setBookId(rs.getLong("BOOK_ID"));
                loan.setUserId(rs.getLong("USER_ID"));
                loan.setReturnBy(rs.getDate("RETURN_BY"));
                loan.setReturnedOn(rs.getDate("RETURNED_ON"));
                loan.setReturned(rs.getBoolean("RETURNED"));
                loan.setUserFirstName(rs.getString("FIRST_NAME"));
                loan.setUserLastName(rs.getString("LAST_NAME"));
                loan.setUserEmail(rs.getString("EMAIL"));
                loan.setBookTitle(rs.getString("TITLE"));
                loan.setBookIsbn(rs.getLong("ISBN"));
                loan.setAuthorFirstName(rs.getString("AUTHOR_FIRST_NAME"));
                loan.setAuthorLastName(rs.getString("AUTHOR_LAST_NAME"));
                loans_list.add(loan);
            }

            stmt.close();
            return loans_list;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<loanModel> getActiveLoans() {
        String query = "SELECT l.LOAN_ID LOAN_ID," +
                "l.BOOK_ID BOOK_ID," +
                "l.USER_ID USER_ID," +
                "l.RETURN_BY RETURN_BY," +
                "l.RETURNED_ON RETURNED_ON," +
                "l.RETURNED RETURNED," +
                "u.FIRST_NAME FIRST_NAME," +
                "u.LAST_NAME LAST_NAME," +
                "u.EMAIL EMAIL," +
                "b.TITLE TITLE," +
                "b.ISBN ISBN," +
                "b.PAGES PAGES," +
                "a.FIRST_NAME AUTHOR_FIRST_NAME," +
                "a.LAST_NAME AUTHOR_LAST_NAME FROM loans l " +
                "INNER JOIN library_users u ON l.user_id = u.user_id " +
                "INNER JOIN books b ON b.book_id = l.book_id " +
                "INNER JOIN authors a ON b.author_id = a.author_id " +
                "WHERE l.returned = 0";
        ArrayList loans_list = new ArrayList();
        Statement stmt = null;

        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println(rs);

            while (rs.next()) {
                loanModel loan = new loanModel();
                loan.setLoanId(rs.getLong("LOAN_ID"));
                loan.setBookId(rs.getLong("BOOK_ID"));
                loan.setUserId(rs.getLong("USER_ID"));
                loan.setReturnBy(rs.getDate("RETURN_BY"));
                loan.setReturnedOn(rs.getDate("RETURNED_ON"));
                loan.setReturned(rs.getBoolean("RETURNED"));
                loan.setUserFirstName(rs.getString("FIRST_NAME"));
                loan.setUserLastName(rs.getString("LAST_NAME"));
                loan.setUserEmail(rs.getString("EMAIL"));
                loan.setBookTitle(rs.getString("TITLE"));
                loan.setBookIsbn(rs.getLong("ISBN"));
                loan.setAuthorFirstName(rs.getString("AUTHOR_FIRST_NAME"));
                loan.setAuthorLastName(rs.getString("AUTHOR_LAST_NAME"));
                loans_list.add(loan);
            }

            stmt.close();
            return loans_list;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<loanModel> geLoanReportForCustomer(int customerId, Date startDate, Date endDate) {
        String query = "SELECT l.LOAN_ID LOAN_ID," +
                "l.BOOK_ID BOOK_ID," +
                "l.USER_ID USER_ID," +
                "l.RETURN_BY RETURN_BY," +
                "l.RETURNED_ON RETURNED_ON," +
                "l.RETURNED RETURNED," +
                "u.FIRST_NAME FIRST_NAME," +
                "u.LAST_NAME LAST_NAME," +
                "u.EMAIL EMAIL," +
                "b.TITLE TITLE," +
                "b.ISBN ISBN," +
                "b.PAGES PAGES," +
                "a.FIRST_NAME AUTHOR_FIRST_NAME," +
                "a.LAST_NAME AUTHOR_LAST_NAME FROM loans l " +
                "WHERE l.USER_ID = " + customerId + " " +
                "AND l.RETURN_BY >= " + startDate + " " +
                "AND l.RETURN_BY =< " + endDate + " " +
                "INNER JOIN library_users u ON l.user_id = u.user_id " +
                "INNER JOIN books b ON b.book_id = l.book_id " +
                "INNER JOIN authors a ON b.author_id = a.author_id";
        ArrayList loans_list = new ArrayList();
        Statement stmt = null;

        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                loanModel loan = new loanModel();
                loan.setLoanId(rs.getLong("LOAN_ID"));
                loan.setBookId(rs.getLong("BOOK_ID"));
                loan.setUserId(rs.getLong("USER_ID"));
                loan.setReturnBy(rs.getDate("RETURN_BY"));
                loan.setReturnedOn(rs.getDate("RETURNED_ON"));
                loan.setReturned(rs.getBoolean("RETURNED"));
                loan.setUserFirstName(rs.getString("FIRST_NAME"));
                loan.setUserLastName(rs.getString("LAST_NAME"));
                loan.setUserEmail(rs.getString("EMAIL"));
                loan.setBookTitle(rs.getString("TITLE"));
                loan.setBookIsbn(rs.getLong("ISBN"));
                loan.setAuthorFirstName(rs.getString("AUTHOR_FIRST_NAME"));
                loan.setAuthorLastName(rs.getString("AUTHOR_LAST_NAME"));
                loans_list.add(loan);
            }

            stmt.close();
            return loans_list;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void returnBook(int loanId) {
        Connection con = oracleClientProviderBean.getOracleClient();
        String insertLoan = "UPDATE loans SET returned = 1, returned_on = CURRENT_TIMESTAMP WHERE loan_id = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertLoan)) {
            preparedStatement.setInt(1, loanId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle any potential exceptions here
            e.printStackTrace();
        }
    }
}
