package repos.User;

import domain.User.User;
import java.util.List;

public interface IUserRepo {
    String save(User person);
    List<User> all();
    User find(String email);
    User findById(Long id);
    boolean login(String email, String password);
    String getRules(User user);
    void update(User user);
}