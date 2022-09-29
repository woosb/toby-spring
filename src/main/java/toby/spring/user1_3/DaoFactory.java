package toby.spring.user1_3;

public class DaoFactory {
    public UserDao getUserDao(){
        return new UserDao(getConnectionMaker());
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

    private ConnectionMaker getConnectionMaker(){
        return new NConnectionMaker();
    }
}
