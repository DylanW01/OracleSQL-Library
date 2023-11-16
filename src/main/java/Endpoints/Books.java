package Endpoints;

import Objects.bookModel;
import com.google.gson.Gson;
import EJB.BookOracleBean;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "Books", value = "/books")
public class Books extends HttpServlet {
    @EJB
    BookOracleBean bookBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        ArrayList<bookModel> result = bookBean.getBooks();

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
