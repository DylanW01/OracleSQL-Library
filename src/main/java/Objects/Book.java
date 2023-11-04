package Objects;

import org.bson.types.ObjectId;

import java.util.Date;

public class Book {
    private String Id;
    private String BookTitle;
    private String Author;
    private String ISBN;
    private Number Pages;
    private Date Added;
    private boolean OnLoan;

    public Book(String id, String title, String author, String isbn, Number pages, Date added, boolean onLoan) {
        Id = id;
        BookTitle = title;
        Author = author;
        ISBN = isbn;
        Pages = pages;
        Added = added;
        OnLoan = onLoan;
    }

    // constructors
    // standard getters and setters.
}
