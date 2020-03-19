package shop.sqlcontroller;

import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    public static final String databaseURL = "jdbc:mysql://fakeazon.cn8iyodgb9pu.us-east-1.rds.amazonaws.com?serverTimezone=UTC";
    public static final String user = "justZipCode";
    public static final String password = "timeToCode?";


    public static Connection getConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            System.out.println("Great Success");
            return DriverManager.getConnection(databaseURL, user, password);
        } catch(SQLException e){
            throw new RuntimeException("Can't Connect", e);
        }
    }

}
