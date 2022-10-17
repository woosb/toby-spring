package toby.spring.step1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import toby.spring.step1.user1_8.UserDao;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*
* 스프링이 싱글톤 방식으로 빈 오브젝트를 만든다는 것을 테스트
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/WEB-INF/step1/user1_8Context.xml")
public class JunitTest3 {

    @Autowired
    ApplicationContext context;

    static Set<UserDao> testObjects = new HashSet<>();

    @Before
    public void before(){
        testObjects.add((UserDao)context.getBean("userDao"));
    }

    @Test
    public void test1(){
        UserDao object = (UserDao)context.getBean("userDao");
        assertThat(testObjects, is(hasItem(object)));
        testObjects.add(object);
    }

    @Test
    public void test2(){
        UserDao object = (UserDao)context.getBean("userDao");
        assertThat(testObjects, is(hasItem(object)));
        testObjects.add(object);
    }

    @Test
    public void test3(){
        UserDao object = (UserDao)context.getBean("userDao");
        assertThat(testObjects, is(hasItem(object)));
        testObjects.add(object);
    }

}
