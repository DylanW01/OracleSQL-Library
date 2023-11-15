package Objects;

public class UserModel {
    private long UserId;
    private String First_Name;
    private String Last_Name;
    private String Email;

    public void setUserId(long UserId) { this.UserId = UserId; }
    public long getUserId() { return UserId; }
    public void setFirst_Name(String First_Name) { this.First_Name = First_Name; }
    public String getFirst_Name() { return First_Name; }
    public void setLast_Name(String Last_Name) { this.Last_Name = Last_Name; }
    public String getLast_Name() { return Last_Name; }
    public void setEmail(String Email) { this.Email = Email; }
    public String getEmail() { return Email; }
}
