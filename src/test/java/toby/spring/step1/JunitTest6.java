package toby.spring.step1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*
* getBean()  을 사용했는데 주어진 이름의 빈이 발견되지 않으면 어떤 결과가 나올지에 대한 테스트
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/WEB-INF/step1/JunitTest.xml")
public class JunitTest6 {

    @Autowired
    ApplicationContext context;

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void test(){
        context.getBean("User");
    }
}
