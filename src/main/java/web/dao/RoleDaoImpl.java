package web.dao;

import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    public Role getRoleAdmin() {
    Query roleQuery =  entityManager.createQuery("Select r from Role r where r.roleName=:roleName", Role.class);
    roleQuery.setParameter("roleName", "ROLE_ADMIN");
    return (Role) roleQuery.getSingleResult();
    }
    public Role getRoleUser() {
        Query roleQuery =  entityManager.createQuery("Select r from Role r where r.roleName=:roleName", Role.class);
        roleQuery.setParameter("roleName", "ROLE_USER");
        return (Role) roleQuery.getSingleResult();
    }

}
