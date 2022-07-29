package api.security;

import api.exception.AuthenticationException;
import api.exception.RegistrationException;
import api.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password) throws RegistrationException;
}
