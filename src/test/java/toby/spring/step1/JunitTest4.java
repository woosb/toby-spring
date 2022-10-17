package toby.spring.step1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import toby.spring.step1.user1_8.UserDao;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/*
* @Autowired로 가져온 빈 오브젝트가 애플리케이션 컨텍스트에서 직접 getBean()으로 가져오는 것과 동일한지 테스트
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/WEB-INF/step1/user1_8Context.xml")
public class JunitTest4 {

    @Autowired
    UserDao userDao;

    @Autowired
    ApplicationContext context;

    ApplicationContext context2;

    static UserDao testObject;
    @Before
    public void before(){
        this.context2 = new GenericXmlApplicationContext("/WEB-INF/step1/user1_8Context.xml");
        this.testObject = context2.getBean("userDao", UserDao.class);
    }

    @Test
    public void test1(){
        //ApplicationContext 를 새로 만들면 각각의 컨텍스트(context, context2)에서 빈을 만들어 관리하기 때문에 아래와 같은 결과가 나온다.
        assertFalse(userDao == context2.getBean("userDao"));

        assertFalse(this.context == this.context2);

        assertTrue(userDao == context.getBean("userDao"));

        assertFalse(userDao == context2.getBean("userDao"));
    }
}
