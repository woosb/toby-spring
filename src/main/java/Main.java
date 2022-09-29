import toby.spring.user1_2.NUserDao;
import toby.spring.user1_2.User;
import toby.spring.user1_2.UserDao;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        UserDao NuserDao = new NUserDao();

        NuserDao.deleteAll();
        for(int i = 0; i < 3; i++){
            User user = new User();
            user.setId("test"+ i);
            user.setName("토비" + i);
            user.setPassword("1234" + i);
            NuserDao.add(user);
            System.out.println(user.getId() + " 등록 성공!");
        }

        List<User> users = NuserDao.getUsers();
        for(User u : users){
            System.out.println(u);
        }

    }
}
