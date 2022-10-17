package toby.spring.step1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import toby.spring.step1.user1_2.User;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*
* XML에서 스트링 타입의 프로퍼티 값을 설정한 것이 정말 빈에 잘 주입되는지 테스트
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/WEB-INF/step1/JunitTest.xml")
public class JunitTest5 {

    @Autowired
    User user;
    @Test
    public void test1(){
        assertThat(user.getId(), is("testId"));
        assertThat(user.getPassword(), is("testPw"));
        assertThat(user.getName(), is("testName"));
    }
}
