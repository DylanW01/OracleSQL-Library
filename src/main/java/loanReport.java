import Objects.loanModel;
import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import EJB.LoanOracleBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "loan-report", value = "/loan-report")
public class loanReport extends HttpServlet {
    @EJB
    LoanOracleBean loanBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        int customerId = Integer.parseInt(request.getParameter("id"));
        String date = request.getParameter("date");

        Date[] convertedDate = getFirstAndLastDayOfMonth(date);

        PrintWriter out = response.getWriter();
        ArrayList<loanModel> result = loanBean.geLoanReportForCustomer(customerId, convertedDate[0], convertedDate[1]);

        Gson gson = new Gson();
        String jsonArray = gson.toJson(result);

        out.print(jsonArray);
        out.flush();
    }

    public static Date[] getFirstAndLastDayOfMonth(String yearMonth) {
        String[] parts = yearMonth.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1, 0, 0, 0); // Set time to midnight
        Date firstDay = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1); // Move to the last day of the month
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date lastDay = calendar.getTime();
        return new Date[] { firstDay, lastDay };
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
