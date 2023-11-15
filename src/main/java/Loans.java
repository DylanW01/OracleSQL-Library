import EJB.LoanOracleBean;
import EJB.BookOracleBean;
import Objects.loanModel;
import com.google.gson.Gson;
import com.mongodb.client.AggregateIterable;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "Loans", value = "/loans")
public class Loans extends HttpServlet {
    @EJB
    LoanOracleBean loanBean;
    @EJB
    BookOracleBean bookBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        ArrayList<loanModel> result = loanBean.getLoans();

        // Convert the list of documents to a JSON array
        Gson gson = new Gson();
        String jsonArray = gson.toJson(result);

        out.print(jsonArray);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        loanModel loan = new loanModel();
        out.print("Creating Loan");
        out.print(request.getParameter("users"));
        out.print(request.getParameter("books"));
        loan.setUserId((long) Integer.parseInt(request.getParameter("users")));
        loan.setBookId((long) Integer.parseInt(request.getParameter("books")));
        loanBean.createLoan(loan);
        bookBean.markAsBorrowed(Integer.parseInt(request.getParameter("books")));
    }
}
