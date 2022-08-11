package api.service.impl;

import api.dao.RoleDao;
import api.model.Role;
import api.service.RoleService;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Role getByName(String roleName) {
        return roleDao.getByName(Role.RoleName.valueOf(roleName))
                .orElseThrow(() -> new NoSuchElementException("Can't find role " + roleName));
    }
}
