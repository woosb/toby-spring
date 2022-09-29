package toby.spring.user1_3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private ConnectionMaker connectionMaker;
    private final Connection con;

    public UserDao(ConnectionMaker connectionMaker){
        this.con = connectionMaker.getConnection();
        this.connectionMaker =  connectionMaker;
    }

    public void add(User user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public List<User> getUsers() throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from users");
        ResultSet resultSet = ps.executeQuery();

        List<User> users = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getString("id"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            users.add(user);
        }
        return users;
    }

    public void deleteAll() throws SQLException {
        PreparedStatement ps = con.prepareStatement("delete from users");
        ps.execute();
        ps.close();
        con.close();
    }
}