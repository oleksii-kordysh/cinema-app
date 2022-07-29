package api.dao;

import java.util.Optional;
import api.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
