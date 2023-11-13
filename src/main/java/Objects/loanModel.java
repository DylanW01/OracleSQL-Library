package Objects;

import java.io.Serializable;
import java.util.Date;

public class loanModel implements Serializable {
    private long LoanId;
    private long BookId;
    private long UserId;
    private Date ReturnBy;
    private Date ReturnedOn;
    private boolean Returned;
    private String UserFirstName;
    private String UserLastName;
    private String UserEmail;
    private String BookTitle;
    private long BookIsbn;
    private String AuthorFirstName;
    private String AuthorLastName;








    public void setLoanId(long LoanId) { this.LoanId = LoanId; }
    private long getLoanId() {
        return LoanId;
    }
    public void setBookId(long BookId) { this.BookId = BookId; }
    public long getBookId() { return BookId; }
    public void setUserId(long UserId) { this.UserId = UserId; }
    public long getUserId() { return UserId; }
    public void setReturnBy(Date ReturnBy) { this.ReturnBy = ReturnBy; }
    public Date getReturnBy() { return ReturnBy; }
    public void setReturnedOn(Date ReturnedOn) { this.ReturnedOn = ReturnedOn; }
    public Date getReturnedOn() { return ReturnedOn; }
    public void setReturned(boolean Returned) { this.Returned = Returned; }
    public boolean getReturned() { return Returned; }
    public void setUserFirstName(String UserFirstName) { this.UserFirstName = UserFirstName; }
    public String getUserFirstName() { return UserFirstName; }
    public void setUserLastName(String UserLastName) { this.UserLastName = UserLastName; }
    public String getUserLastName() { return UserLastName; }
    public void setUserEmail(String UserEmail) { this.UserEmail = UserEmail; }
    public String getUserEmail() { return UserEmail; }
    public void setBookTitle(String BookTitle) { this.BookTitle = BookTitle; }
    public String getBookTitle() { return BookTitle; }
    public void setBookIsbn(long BookIsbn) { this.BookIsbn = BookIsbn; }
    public long getBookIsbn() { return BookIsbn; }
    public void setAuthorFirstName(String AuthorFirstName) { this.AuthorFirstName = AuthorFirstName; }
    public String getAuthorFirstName() { return AuthorFirstName; }
    public void setAuthorLastName(String AuthorLastName) { this.AuthorLastName = AuthorLastName; }
    public String getAuthorLastName() { return AuthorLastName; }

}
