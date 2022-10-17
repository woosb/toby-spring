package toby.spring.step1;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import toby.spring.step1.user1_8.User;
import toby.spring.step1.user1_8.UserDao;

import java.sql.SQLException;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/WEB-INF/user1_8Context.xml")

public class UserDaoTest {

    /*
    * 1. 스프링 어플리케이션 컨텍스트는 초기화 할때 자기 자신도 빈으로 등록하기 때문에 user1_8Context.xml에 설정파일이 없어도 주입 받을 수 있다.
    * 2. @Autowired 를 이용하여 di를 받을 수 있다면 context가 아닌 바로 userDao를 주입받을 수 있기 때문에 바로 바로 받아온다.

    * @Autowired
    * private ApplicationContext context;
    *
     * */
    @Autowired
    UserDao userDao;

    private User user1;
    private User user2;
    private User user3;


    @Before
    public void setUp(){
        user1 = new User("qwer", "유저1", "1234");
        user2 = new User("asdf", "유저2", "5678");
        user3 = new User("zxcv", "유저3", "7946");
    }

    @Test
    public void addAndGet() throws SQLException {
        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);

        assertThat(userDao.getCount(), is(1));

        User getUser = userDao.getUser((user1.getId()));

        assertThat(user1.getId(), is(getUser.getId()));
        assertThat(user1.getName(), is(getUser.getName()));
        assertThat(user1.getPassword(), is(getUser.getPassword()));
    }

    @Test
    public void getCount() throws SQLException{
        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        assertThat(userDao.getCount(), is(1));

        userDao.add(user2);
        assertThat(userDao.getCount(), is(2));

        userDao.add(user3);
        assertThat(userDao.getCount(), is(3));

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException{
        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));
        userDao.getUser("null");
    }
}
