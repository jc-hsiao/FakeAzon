package shop.sqlcontroller;

import java.sql.Connection;

public class UserSQL {

    SQLConnection connection = new SQLConnection();
    Connection userConnect = connection.getConnection();


}
