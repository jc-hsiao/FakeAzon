package shop.sqlcontroller;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;
public class SQLConnection {

    public static final String databaseURL = "jdbc:mysql://fakeazon.cn8iyodgb9pu.us-east-1.rds.amazonaws.com?serverTimezone=UTC";
    public static final String user = "justZipCode";
    public static final String password = "timeToCode?";

    public static Connection connection;

    public static  void makeConnection(){
        try {
            DriverManager.registerDriver(new Driver());
            System.out.println("Great Success");
            connection = DriverManager.getConnection(databaseURL, user, password);
        } catch(SQLException e){
            throw new RuntimeException("Can't Connect", e);
        }
    }

    public static void useDB() {
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("USE fakeAzonDB ");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        makeConnection();
        useDB();
        return connection;

    }

    /*
    *****  TESTING PURPOSES  *******
    public static void main(String[] args) {
        getConnection();
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from test");
            sout(rs);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
     */


}
