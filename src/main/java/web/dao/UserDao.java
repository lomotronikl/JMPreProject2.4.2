package web.dao;

import web.model.Role;
import web.model.User;
import java.util.List;
import java.util.Set;

public interface UserDao {
    void saveUser(User user);
    void removeUserById(long id);
    List<User> getAllUsers();
    User getUser(long id);
    void updateUser( User user);
    void setRoles(Set<Role> roles, User user);
    User findByUsername(String userName);
}
