package toby.spring.step1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/*
* JUnit 은 테스트 메소드를 수행할 때 마다 새로운 오브젝트를 만든다.
* 하지만 스프링 테스트 컨텍스트는 테스트 개수에 상관없이 하나만 만들어 진다.
* 이를 검증하기 위한 테스트 코드는 아래와 같다.
* */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/WEB-INF/step1/JunitTest.xml")
public class JunitTest2 {

    @Autowired
    ApplicationContext applicationContext;
    static ApplicationContext contextObject = null;
    static Set<JunitTest2> testObjects = new HashSet<>();

    @Test
    public void test1(){
        assertThat(testObjects, is(not(hasItem(this))));
        testObjects.add(this);
        assertThat(contextObject == null || contextObject == this.applicationContext, is (true));
        contextObject = this.applicationContext;
    }

    @Test
    public void test2(){
        assertThat(testObjects, is(not(hasItem(this))));
        testObjects.add(this);
        assertTrue(contextObject == null || contextObject == this.applicationContext);
        contextObject = this.applicationContext;
    }

    @Test
    public void test3(){
        assertThat(testObjects, is(not(hasItem(this))));
        testObjects.add(this);
        assertThat(contextObject, either(is(nullValue())).or(is(this.applicationContext)));
        contextObject = this.applicationContext;
    }


}
