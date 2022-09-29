package toby.spring.user1_3;
import java.sql.SQLException;
import java.util.List;

public class NUserDaoTest {
    public static void main(String[] args) throws SQLException {
        UserDao userDao = new UserDao(new NConnectionMaker());

        userDao.deleteAll();
        for(int i = 0; i < 3; i++){
            User user = new User();
            user.setId("test"+ i);
            user.setName("토비" + i);
            user.setPassword("1234" + i);
            userDao.add(user);
            System.out.println(user.getId() + " 등록 성공!");
        }

        List<User> users = userDao.getUsers();
        for(User u : users){
            System.out.println(u);
        }

        userDao.closeConnection();
    }
}
