package Objects;

import java.io.Serializable;
import java.util.Date;

public class fineModel implements Serializable {
    private long FineId;
    private long LoanId;
    private double FineAmount;
    private Date FineDate;
    private boolean Paid;
    private long BookId;
    private long UserId;
    private Date ReturnBy;
    private Date ReturnedOn;
    private String UserFirstName;
    private String UserLastName;
    private String UserEmail;
    private String BookTitle;
    private String AuthorFirstName;
    private String AuthorLastName;


    public void setFineId(long FineId) { this.FineId = FineId; }
    public long getFineId() {
        return FineId;
    }
    public void setLoanId(long LoanId) { this.LoanId = LoanId; }
    public long getLoanId() { return LoanId; }
    public void setFineAmount(double FineAmount) { this.FineAmount = FineAmount; }
    public double getFineAmount() { return FineAmount; }
    public void setFineDate(Date FineDate) { this.FineDate = FineDate; }
    public Date getFineDate() { return FineDate; }
    public void setPaid(boolean Paid) { this.Paid = Paid; }
    public boolean getPaid() { return Paid; }
    public void setBookId(long BookId) { this.BookId = BookId; }
    public long getBookId() { return BookId; }
    public void setUserId(long UserId) { this.UserId = UserId; }
    public long getUserId() { return UserId; }
    public void setReturnBy(Date ReturnBy) { this.ReturnBy = ReturnBy; }
    public Date getReturnBy() { return ReturnBy; }
    public void setReturnedOn(Date ReturnedOn) { this.ReturnedOn = ReturnedOn; }
    public Date getReturnedOn() { return ReturnedOn; }
    public void setUserFirstName(String UserFirstName) { this.UserFirstName = UserFirstName; }
    public String getUserFirstName() { return UserFirstName; }
    public void setUserLastName(String UserLastName) { this.UserLastName = UserLastName; }
    public String getUserLastName() { return UserLastName; }
    public void setUserEmail(String UserEmail) { this.UserEmail = UserEmail; }
    public String getUserEmail() { return UserEmail; }
    public void setBookTitle(String BookTitle) { this.BookTitle = BookTitle; }
    public String getBookTitle() { return BookTitle; }
    public void setAuthorFirstName(String AuthorFirstName) { this.AuthorFirstName = AuthorFirstName; }
    public String getAuthorFirstName() { return AuthorFirstName; }
    public void setAuthorLastName(String AuthorLastName) { this.AuthorLastName = AuthorLastName; }
    public String getAuthorLastName() { return AuthorLastName; }
}
