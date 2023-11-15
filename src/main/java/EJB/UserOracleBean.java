package EJB;

import Objects.UserModel;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Stateless(name = "UserOracleEJB")
public class UserOracleBean {
    @EJB
    OracleClientProviderBean oracleClientProviderBean;
    public ArrayList<UserModel> getUsers() {
        String query = "SELECT u.USER_ID USER_ID," +
                "u.FIRST_NAME FIRST_NAME," +
                "u.LAST_NAME LAST_NAME," +
                "u.EMAIL EMAIL FROM library_users u";
        ArrayList users_list = new ArrayList();
        Statement stmt = null;

        try {
            Connection con = oracleClientProviderBean.getOracleClient();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                UserModel user = new UserModel();
                user.setUserId(rs.getLong("USER_ID"));
                user.setFirst_Name(rs.getString("FIRST_NAME"));
                user.setLast_Name(rs.getString("LAST_NAME"));
                user.setEmail(rs.getString("EMAIL"));
                users_list.add(user);
            }

            stmt.close();
            return users_list;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
