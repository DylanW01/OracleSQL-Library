package EJB;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton(name = "OracleClientProviderEJB")
public class OracleClientProviderBean {
    private Connection oracleClient = null;

    @Lock(LockType.READ)
    public Connection getOracleClient() {return oracleClient;}

    @PostConstruct
    public void init() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver Not Found");
            e.printStackTrace();
        }
        try {
            oracleClient = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//oracle.glos.ac.uk:1521/orclpdb.chelt.local",
                    "s4002608",
                    "s4002608!");
            if (oracleClient != null) {
                System.out.println("Connected!");
            } else {
                System.out.println("Connection to DB failed");
            }
        } catch(SQLException e) {
            System.out.println("Connection to DB failed. Check console.");
            e.printStackTrace();
        }

    }
}
