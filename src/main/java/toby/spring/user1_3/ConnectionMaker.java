package toby.spring.user1_3;

import java.sql.Connection;

public interface ConnectionMaker {
    void makeNewConnection ();
    Connection getConnection ();
}
