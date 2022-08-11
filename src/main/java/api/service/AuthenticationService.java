package api.service;

import api.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
