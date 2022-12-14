package toby.spring.step1;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import toby.spring.step1.user1_8.User;
import toby.spring.step1.user1_8.UserDao;

import javax.sql.DataSource;
import java.sql.SQLException;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "/WEB-INF/step1/user1_8Context.xml")
@DirtiesContext
// @DirtiesContext:
// - 테스트 메소드에서 애플리케이션 컨텍스트의 구성이나 상태를 변경한다는 것을 테스트 컨텍스트 프레임워크에 알려준다.
// - 이 애노테이션이 붙은 테스트 클래스에는 애플리케이션 컨텍스트 공유를 허용하지 않는다.
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

        // user1_8Context.xml 에 있는 설정정보를 사용하지 않고 userDao 에 강제로 DataSource 를 주입해준다.
        // @DirtiesContext : 테스트 클래스를 생성할 때 ApplicationContext 를 공유하지 않도록 설정하여 Datasource 를 강제로 주입한 userDao 를 사용하도록 한다.
        DataSource dataSource = new SingleConnectionDataSource("jdbc:mysql://localhost/toby_spring", "root", "1234", true);
        userDao.setDataSource(dataSource);
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
