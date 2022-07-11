package Connect_MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_MySQL {
    public static Connection getConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/nhanvien_C0322g1?useSSL=false";
            String username = "root";
            String pass = "12345sau";
            return DriverManager.getConnection(url,username,pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
