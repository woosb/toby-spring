package toby.spring.user1_7;

import java.sql.Connection;

public class CountingConnectionMaker implements ConnectionMaker{

    private final ConnectionMaker connectionMaker;

    private int count = 0;
    public CountingConnectionMaker(ConnectionMaker realConnectionMaker){
        this.connectionMaker = realConnectionMaker;
    }

    @Override
    public Connection makeNewConnection(){
        return connectionMaker.makeNewConnection();
    }

    @Override
    public Connection getConnection(){
        Connection con = makeNewConnection();
        if(con == null){
            con = makeNewConnection();
        }
        count ++;
        return con;
    }

    public int getCount(){
        return this.count;
    }
}
