package Endpoints;

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


@WebServlet(name = "Fines", value = "/fines")
public class Fines extends HttpServlet {
    @EJB
    FineOracleBean fineBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        ArrayList<fineModel> result = fineBean.getFines();

        Gson gson = new Gson();
        String jsonArray = gson.toJson(result);

        out.print(jsonArray);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("Fine Paid. <a href=\"http://localhost:8080/MongoDB-Library-1.0-SNAPSHOT/unpaidfines.jsp\">Click Here</a> to go back");
        fineBean.markAsPaid(Integer.parseInt(request.getParameter("fineId")));
    }
}
