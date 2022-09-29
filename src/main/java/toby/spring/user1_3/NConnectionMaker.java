package toby.spring.user1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker{

     private Connection con = null;
     public void makeNewConnection(){
          try{
               this.con = DriverManager.getConnection("jdbc:mysql://localhost/toby_spring", "root", "1234");
          }catch(SQLException e){
               e.printStackTrace();
          }
     }

     public Connection getConnection(){
          if(this.con == null){
               makeNewConnection();
          }
          return con;
     }
}
