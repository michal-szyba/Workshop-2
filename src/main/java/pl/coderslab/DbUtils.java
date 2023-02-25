package pl.coderslab;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DbUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/Workshop?useSSL=false&characterEncoding=UTF8";
    private static final String USERNAME = "root";
    private static final String PASS = "coderslab";

    public static Connection dbConnect() throws SQLException{
        return DriverManager.getConnection(DB_URL, USERNAME, PASS);
    }
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
