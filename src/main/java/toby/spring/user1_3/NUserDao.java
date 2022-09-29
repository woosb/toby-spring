package toby.spring.user1_3;

import java.sql.SQLException;
import java.util.List;

public class NUserDao{
    UserDao userDao = new UserDao(new NConnectionMaker());

    public void add(User user) throws SQLException {
        userDao.add(user);
    }

    public List<User> getUsers() throws SQLException {
        return userDao.getUsers();
    }

    public void deleteAll() throws SQLException {
        userDao.deleteAll();
    }
}
