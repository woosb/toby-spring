package toby.spring.user1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker{

     public Connection getConnection() throws SQLException {
          return DriverManager.getConnection("jdbc:mysql://localhost/toby_spring", "root", "1234");
     }
}
