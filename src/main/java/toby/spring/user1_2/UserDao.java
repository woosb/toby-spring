package toby.spring.user1_2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void add(User user) throws SQLException {
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();
        ps.close();
        c.close();
    }

    public List<User> getUsers() throws SQLException {
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("select * from users");
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
        Connection c = getConnection();
        PreparedStatement ps = c.prepareStatement("delete from users");
        ps.execute();
        ps.close();
        c.close();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/toby_spring", "root", "1234");
    }

    /*
    * 탬플릿 메서드 패턴으로 슈퍼클래스에서 일부 기능을 추상 메소드나 오버라이딩 가능한 protected 메소드 등으로
    * 만들어 서브 클래스에서 필요에 맞게 구현하도록 하는 방법이다.
    * */
    protected Connection AbstractGetConnection() throws ClassNotFoundException, SQLException {
        return null;
    }

}
