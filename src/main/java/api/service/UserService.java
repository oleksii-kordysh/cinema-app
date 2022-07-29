package api.service;

import java.util.Optional;
import api.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
