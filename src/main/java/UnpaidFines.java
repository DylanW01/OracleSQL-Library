import Objects.fineModel;
import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import EJB.FineOracleBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "UnpaidFines", value = "/unpaidFines")
public class UnpaidFines extends HttpServlet {
    @EJB
    FineOracleBean fineBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        ArrayList<fineModel> result = fineBean.getUnpaidFines();
        Gson gson = new Gson();
        String jsonArray = gson.toJson(result);
        out.print(jsonArray);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
