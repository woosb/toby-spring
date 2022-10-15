package toby.spring.step1.user1_7;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class UserDaoFactoryTest {
    public static void main(String[] args) throws SQLException {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

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

        CountingConnectionMaker ccm = applicationContext.getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println(ccm.getCount());

    }
}
