package repos.User;

import domain.User;
import java.util.List;

public interface IUserRepo {
    String save(User person);
    List<User> all();
    User find(String email);
    boolean login(String email, String password);
    String getRules(User user);
}