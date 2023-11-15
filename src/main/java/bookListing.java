import Objects.bookModel;
import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import EJB.BookOracleBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "bookListing", value = "/books/available")
public class bookListing extends HttpServlet {

    @EJB
    BookOracleBean bookBean;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        ArrayList<bookModel> result = bookBean.getAvailableBooks();

        Gson gson = new Gson();
        String jsonArray = gson.toJson(result);

        out.print(jsonArray);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}