package EJB;

import Objects.fineModel;
import Objects.loanModel;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Date;

@Stateless(name = "FineOracleEJB")
public class FineOracleBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;

    public void createFine(fineModel fine) {
        String insertLoan = "INSERT INTO fines"
                + "(LOAN_ID, FINE_AMOUNT, FINE_DATE, PAID)" + "VALUES ("
                + "'" + fine.getLoanId() + "',"
                + "'" + fine.getFineAmount() + "',"
                + "CURRENT_TIMESTAMP,"
                + "0";

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
    public ArrayList<fineModel> getFines() {
        String query = "SELECT f.FINE_ID FINE_ID," +
                "f.LOAN_ID LOAN_ID," +
                "f.FINE_AMOUNT FINE_AMOUNT," +
                "f.FINE_DATE FINE_DATE," +
                "f.PAID PAID," +
                "l.BOOK_ID BOOK_ID," +
                "l.USER_ID USER_ID," +
                "l.RETURN_BY RETURN_BY," +
                "l.RETURNED_ON RETURNED_ON," +
                "l.RETURNED_ON RETURNED_ON," +
                "b.TITLE TITLE," +
                "u.FIRST_NAME FIRST_NAME," +
                "u.LAST_NAME LAST_NAME," +
                "u.EMAIL EMAIL," +
                "a.FIRST_NAME AUTHOR_FIRST_NAME," +
                "a.LAST_NAME AUTHOR_LAST_NAME FROM fines f " +
                "INNER JOIN loans l ON l.LOAN_ID = f.LOAN_ID " +
                "INNER JOIN LIBRARY_USERS u ON u.USER_ID = l.USER_ID " +
                "INNER JOIN books b ON b.BOOK_ID = l.BOOK_ID " +
                "INNER JOIN AUTHORS a ON a.AUTHOR_ID = b.AUTHOR_ID";
        ArrayList fines_list = new ArrayList();
        Statement stmt = null;

        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                fineModel fine = new fineModel();
                fine.setFineId(rs.getLong("FINE_ID"));
                fine.setLoanId(rs.getLong("LOAN_ID"));
                fine.setFineAmount(rs.getDouble("FINE_AMOUNT"));
                fine.setFineDate(rs.getDate("FINE_DATE"));
                fine.setPaid(rs.getBoolean("PAID"));
                fine.setBookId(rs.getLong("BOOK_ID"));
                fine.setUserId(rs.getLong("USER_ID"));
                fine.setReturnBy(rs.getDate("RETURN_BY"));
                fine.setReturnedOn(rs.getDate("RETURNED_ON"));
                fine.setBookTitle(rs.getString("TITLE"));
                fine.setUserFirstName(rs.getString("FIRST_NAME"));
                fine.setUserLastName(rs.getString("LAST_NAME"));
                fine.setUserEmail(rs.getString("EMAIL"));
                fine.setAuthorFirstName(rs.getString("AUTHOR_FIRST_NAME"));
                fine.setAuthorLastName(rs.getString("AUTHOR_LAST_NAME"));
                fines_list.add(fine);
            }

            stmt.close();
            return fines_list;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<fineModel> getUnpaidFines() {
        String query = "SELECT f.FINE_ID FINE_ID," +
                "f.LOAN_ID LOAN_ID," +
                "f.FINE_AMOUNT FINE_AMOUNT," +
                "f.FINE_DATE FINE_DATE," +
                "f.PAID PAID," +
                "l.BOOK_ID BOOK_ID," +
                "l.USER_ID USER_ID," +
                "l.RETURN_BY RETURN_BY," +
                "l.RETURNED_ON RETURNED_ON," +
                "l.RETURNED_ON RETURNED_ON," +
                "b.TITLE TITLE," +
                "u.FIRST_NAME FIRST_NAME," +
                "u.LAST_NAME LAST_NAME," +
                "u.EMAIL EMAIL," +
                "a.FIRST_NAME AUTHOR_FIRST_NAME," +
                "a.LAST_NAME AUTHOR_LAST_NAME FROM fines f " +
                "INNER JOIN loans l ON l.LOAN_ID = f.LOAN_ID " +
                "INNER JOIN LIBRARY_USERS u ON u.USER_ID = l.USER_ID " +
                "INNER JOIN books b ON b.BOOK_ID = l.BOOK_ID " +
                "INNER JOIN AUTHORS a ON a.AUTHOR_ID = b.AUTHOR_ID " +
                "WHERE f.PAID = 0";
        ArrayList fines_list = new ArrayList();
        Statement stmt = null;

        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                fineModel fine = new fineModel();
                fine.setFineId(rs.getLong("FINE_ID"));
                fine.setLoanId(rs.getLong("LOAN_ID"));
                fine.setFineAmount(rs.getDouble("FINE_AMOUNT"));
                fine.setFineDate(rs.getDate("FINE_DATE"));
                fine.setPaid(rs.getBoolean("PAID"));
                fine.setBookId(rs.getLong("BOOK_ID"));
                fine.setUserId(rs.getLong("USER_ID"));
                fine.setReturnBy(rs.getDate("RETURN_BY"));
                fine.setReturnedOn(rs.getDate("RETURNED_ON"));
                fine.setBookTitle(rs.getString("TITLE"));
                fine.setUserFirstName(rs.getString("FIRST_NAME"));
                fine.setUserLastName(rs.getString("LAST_NAME"));
                fine.setUserEmail(rs.getString("EMAIL"));
                fine.setAuthorFirstName(rs.getString("AUTHOR_FIRST_NAME"));
                fine.setAuthorLastName(rs.getString("AUTHOR_LAST_NAME"));
                fines_list.add(fine);
            }

            stmt.close();
            return fines_list;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void checkIssueFine(int loanId) {
        String query = "SELECT l.LOAN_ID LOAN_ID," +
                "l.BOOK_ID BOOK_ID," +
                "l.RETURN_BY RETURN_BY," +
                "l.RETURNED_ON RETURNED_ON," +
                "l.RETURNED RETURNED," +
                "b.TITLE TITLE FROM loans l " +
                "INNER JOIN books b ON b.book_id = l.book_id " +
        "WHERE l.LOAN_ID = " + loanId;

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
                loan.setReturnBy(rs.getDate("RETURN_BY"));
                loan.setReturnedOn(rs.getDate("RETURNED_ON"));
                loan.setReturned(rs.getBoolean("RETURNED"));
                loan.setBookTitle(rs.getString("TITLE"));
                loans_list.add(loan);
            }

            stmt.close();
            Object loanObject = loans_list.get(0);
            if (loanObject instanceof loanModel) {
                loanModel loan = (loanModel) loanObject;
                Date returnBy = loan.getReturnBy();
                Date returnDate = loan.getReturnedOn();

                // Convert Date to java.sql.Date
                java.sql.Date returnBySqlDate = new java.sql.Date(returnBy.getTime());
                java.sql.Date returnDateSqlDate = new java.sql.Date(returnDate.getTime());

                // Calculate the difference in days
                long daysDifference = ChronoUnit.DAYS.between(returnBySqlDate.toLocalDate(), returnDateSqlDate.toLocalDate());

                // Convert the days difference to an integer
                int daysDifferenceInt = Math.toIntExact(daysDifference);

                // Calculate the fine cost
                double fineCost = 2.5 * daysDifferenceInt;

                if(fineCost > 0) {
                    String insertFine = "INSERT INTO fines"
                            + "(LOAN_ID, FINE_AMOUNT, FINE_DATE, PAID)" + "VALUES ("
                            + loanId + ","
                            + fineCost + ","
                            + "CURRENT_TIMESTAMP,"
                            + "0)";

                    Statement stmt2 = null;
                    try {
                        stmt2 = con.createStatement();
                        System.out.println(insertFine);
                        stmt2.executeUpdate(insertFine);
                        stmt2.close();
                    } catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void markAsPaid(int fineId) {
        Connection con = oracleClientProviderBean.getOracleClient();
        String insertLoan = "UPDATE FINES SET PAID = 1 WHERE FINE_ID = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertLoan)) {
            preparedStatement.setInt(1, fineId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle any potential exceptions here
            e.printStackTrace();
        }
    }

    public ArrayList<fineModel> getFineReportForCustomer(int customerId, Date startDate, Date endDate) {
        String query = "{CALL GET_FINE_REPORT_FOR_CUSTOMER(?, ?, ?, ?)}";

        ArrayList<fineModel> fineList = new ArrayList<>();

        try (Connection con = oracleClientProviderBean.getOracleClient();
             CallableStatement cstmt = con.prepareCall(query)) {

            cstmt.setInt(1, customerId);
            cstmt.setObject(2, new java.sql.Date(startDate.getTime()));
            cstmt.setObject(3, new java.sql.Date(endDate.getTime()));
            cstmt.registerOutParameter(4, OracleTypes.CURSOR);

            cstmt.execute();

            try (ResultSet rs = (ResultSet) cstmt.getObject(4)) {
                while (rs.next()) {
                    fineModel fine = new fineModel();
                    fine.setFineId(rs.getLong("FINE_ID"));
                    fine.setFineAmount(rs.getDouble("FINE_AMOUNT"));
                    fine.setFineDate(rs.getDate("FINE_DATE"));
                    fine.setPaid(rs.getBoolean("PAID"));
                    fine.setBookTitle(rs.getString("TITLE"));
                    fine.setUserFirstName(rs.getString("FIRST_NAME"));
                    fine.setUserLastName(rs.getString("LAST_NAME"));
                    fine.setAuthorFirstName(rs.getString("AUTHOR_FIRST_NAME"));
                    fine.setAuthorLastName(rs.getString("AUTHOR_LAST_NAME"));
                    fineList.add(fine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fineList;
    }

}
