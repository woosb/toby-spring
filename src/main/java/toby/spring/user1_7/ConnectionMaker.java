package toby.spring.user1_7;

import java.sql.Connection;

public interface ConnectionMaker {
    Connection makeNewConnection ();
    Connection getConnection ();
}
