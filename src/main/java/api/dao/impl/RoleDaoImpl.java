package api.dao.impl;

import api.dao.AbstractDao;
import api.dao.RoleDao;
import api.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(Role.RoleName roleName) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Role where roleName = :roleName", Role.class)
                    .setParameter("roleName", roleName)
                    .uniqueResultOptional();
        }
    }
}
