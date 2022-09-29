package toby.spring.user1_3;

public class DaoFactory {
    public UserDao getUserDao(){
        return new UserDao(new NConnectionMaker());
    }
}
