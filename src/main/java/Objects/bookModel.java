package Objects;

import java.io.Serializable;
import java.util.Date;

public class bookModel implements Serializable {
    private long BookId;
    private String BookTitle;
    private long BookIsbn;
    private int BookPages;
    private Date Created;
    private boolean OnLoan;
    private String AuthorFirstName;
    private String AuthorLastName;


    public void setBookId(long BookId) { this.BookId = BookId; }
    public long getBookId() { return BookId; }
    public void setBookTitle(String BookTitle) { this.BookTitle = BookTitle; }
    public String getBookTitle() { return BookTitle; }
    public void setBookIsbn(long BookIsbn) { this.BookIsbn = BookIsbn; }
    public long getBookIsbn() { return BookIsbn; }
    public void setBookPages(int BookPages) { this.BookPages = BookPages; }
    public int getBookPages() { return BookPages; }
    public void setCreated(Date Created) { this.Created = Created; }
    public Date getCreated() { return Created; }
    public void setOnLoan(boolean OnLoan) { this.OnLoan = OnLoan; }
    public boolean getOnLoan() { return OnLoan; }
    public void setAuthorFirstName(String AuthorFirstName) { this.AuthorFirstName = AuthorFirstName; }
    public String getAuthorFirstName() { return AuthorFirstName; }
    public void setAuthorLastName(String AuthorLastName) { this.AuthorLastName = AuthorLastName; }
    public String getAuthorLastName() { return AuthorLastName; }

}
