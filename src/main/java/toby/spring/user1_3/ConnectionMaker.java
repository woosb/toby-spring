package toby.spring.user1_3;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    Connection getConnection () throws SQLException;
}
