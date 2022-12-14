package toby.spring.step1.user1_8;

import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public UserDao(){}
    public DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws SQLException {
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public List<User> getUsers() throws SQLException {
        Connection con = dataSource.getConnection();
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
        con.close();
        return users;
    }

    public User getUser(String id) throws SQLException{
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);
        ResultSet resultSet = ps.executeQuery();

        User user = null;
        if(resultSet.next()){
            user = new User();
            user.setId(resultSet.getString("id"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
        }

        if(user == null) throw new EmptyResultDataAccessException(1);
        return user;
    }

    public void deleteAll() throws SQLException {
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement("delete from users");
        ps.execute();
        ps.close();
        con.close();
    }

    public int getCount() throws SQLException{
        Connection con = dataSource.getConnection();
        PreparedStatement ps = con.prepareStatement("select count(*) from users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
}
