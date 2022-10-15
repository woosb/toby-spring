package toby.spring.step1.user1_7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

     public Connection makeNewConnection(){
          Connection con;
          try{
               con = DriverManager.getConnection("jdbc:mysql://localhost/toby_spring", "root", "1234");
               return con;
          }catch(SQLException e){
               e.printStackTrace();
          }
          return null;
     }

     public Connection getConnection(){
          Connection con = makeNewConnection();
          if(con == null){
              con = makeNewConnection();
          }
          return con;
     }
}
