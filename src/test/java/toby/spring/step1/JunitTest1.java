package toby.spring.step1;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/*
* JUnit 은 테스트 메소드를 수행할 때 마다 새로운 오브젝트를 만든다.
* 이를 검증하기 위한 테스트 코드는 아래와 같다.
*
* */
public class JunitTest1 {
    static Set<JunitTest1> testObjects = new HashSet<>();

    @Test
    public void test1(){
        assertThat(testObjects, is(not(hasItem(this))));
        testObjects.add(this);
    }

    @Test
    public void test2(){
        assertThat(testObjects, is(not(hasItem(this))));
        testObjects.add(this);
    }

    @Test
    public void test3(){
        assertThat(testObjects, is(not(hasItem(this))));
        testObjects.add(this);
    }


}
