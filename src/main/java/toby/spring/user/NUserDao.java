package toby.spring.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao{

    /*
    * 팩토리 메서드 패턴으로 "Connection"을 얻어 오는 부분을 서브클래스에서 구현할 수 있다.
    * */
    @Override
    public Connection AbstractGetConnection() throws SQLException {
        return  DriverManager.getConnection("jdbc:mysql://localhost/toby_spring", "root", "1234");
    }
}
