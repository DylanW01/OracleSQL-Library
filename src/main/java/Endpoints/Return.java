package Endpoints;

import EJB.BookOracleBean;
import EJB.LoanOracleBean;
import EJB.FineOracleBean;
import Objects.loanModel;
import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "Return", value = "/return")
public class Return extends HttpServlet {
    @EJB
    LoanOracleBean loanBean;
    @EJB
    BookOracleBean bookBean;
    @EJB
    FineOracleBean fineBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        ArrayList<loanModel> result = loanBean.getActiveLoans();

        // Convert the list of documents to a JSON array
        Gson gson = new Gson();
        String jsonArray = gson.toJson(result);

        out.print(jsonArray);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("Book Returned. <a href=\"http://localhost:8080/OracleSQL-Library-1.0-SNAPSHOT/newloan.jsp\">Click Here</a> to go back");
        int loanId = Integer.parseInt(request.getParameter("loanId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        // Mark loan as returned
        loanBean.returnBook(loanId);

        // Get loan details, check due date & issue fine if needed
        fineBean.checkIssueFine(loanId);

        // Mark book as returned
        bookBean.markAsReturned(bookId);
    }
}
