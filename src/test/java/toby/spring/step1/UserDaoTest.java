package toby.spring.step1;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import toby.spring.step1.user1_8.User;
import toby.spring.step1.user1_8.UserDao;

import java.sql.SQLException;


public class UserDaoTest {

    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext applicationContext = new GenericXmlApplicationContext("/WEB-INF/user1_8Context.xml");
        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);

        userDao.deleteAll();

        User addUser = new User();
        addUser.setId("test22");
        addUser.setName("토비33");
        addUser.setPassword("123444");
        userDao.add(addUser);

        User getUser = userDao.getUser((addUser.getId()));

        assertThat(addUser.getId(), is(getUser.getId()));
        assertThat(addUser.getName(), is(getUser.getName()));
        assertThat(addUser.getPassword(), is(getUser.getPassword()));

    }
}
