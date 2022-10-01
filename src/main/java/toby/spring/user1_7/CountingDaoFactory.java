package toby.spring.user1_7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 어플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시
public class CountingDaoFactory {
    @Bean // 오브젝트 생성을 담당하는 IoC용 메소드라는 표시
    public UserDao userDao(){
//        UserDao userDao = new UserDao();
//        userDao.setConnectionMaker(connectionMaker());
//        return userDao;
        return new UserDao(connectionMaker());
    }


    /*
    ## new NConnectionMaker()를 메소드 추출을 통해 리팩토링 시
    ## 아래와 같이 중복 될 수 있는 부분을 제거해 준다.

    public AccountDao getAccountDao(){
        return new AccountDao(getConnectionMaker());
    }
    public MessageDao getMessageDao(){
        return new MessageDao(getConnectionMaker());
    }
    */
    @Bean
    public ConnectionMaker connectionMaker(){
        return new CountingConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker(){
        return new NConnectionMaker();
    }
}
