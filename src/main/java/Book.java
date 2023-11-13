import EJB.BookBean;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet(name = "Books", value = "/books")
public class Book extends HttpServlet {
    @EJB
    BookBean bookBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        FindIterable<Document> foundBooks = bookBean.getBooks();
        MongoCursor<Document> cursor = foundBooks.iterator();

        List<Objects.Book> books = new ArrayList<Objects.Book>();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();

                Objects.Book book = new Objects.Book(
                        doc.get("_id").toString(),
                        doc.get("Title").toString(),
                        doc.get("Author").toString(),
                        doc.get("ISBN").toString(),
                        (Integer) doc.get("Pages"),
                        (Date) doc.get("Added"),
                        (Boolean) doc.get("OnLoan"));

                books.add(book);
            }
        } finally {
            cursor.close();
        }

        String booksJsonString = new Gson().toJson(books);
        out.print(booksJsonString);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void createCustomer(Document customer) {


    }
}
