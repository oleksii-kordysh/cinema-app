package api.service;

import api.model.Role;

public interface RoleService {
    Role add(Role role);

    Role getByName(String roleName);
}
