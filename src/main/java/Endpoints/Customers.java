package Endpoints;

import Objects.UserModel;
import com.google.gson.Gson;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import EJB.UserOracleBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "users", value = "/users")
public class Customers extends HttpServlet {
    @EJB
    UserOracleBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        ArrayList<UserModel> result = userBean.getUsers();

        // Convert the list of documents to a JSON array
        Gson gson = new Gson();
        String jsonArray = gson.toJson(result);

        out.print(jsonArray);
        out.flush();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
