package web.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NamedNativeQuery(name = "get_all_users", query = "select * from users",resultClass=User.class)

@Entity
@Table (name = "users")
public class User implements UserDetails, Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "lastName")
    private String lastName;

    @Column (name = "eMail")
    private String eMail;

    @Column (name = "userName")
    private String userName;

    @Column (name = "password")
    private String password;

    private String securePassword;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isRoleUser() {
        return roleUser;
    }

    public void setRoleUser(boolean roleUser) {
        this.roleUser = roleUser;
    }

    public boolean isRoleAdmin() {
        return roleAdmin;
    }



    public void setRoleAdmin(boolean roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    private boolean roleUser;
    private boolean roleAdmin;


    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, String name, String lastName, String eMail) {
        this.userName=userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.eMail = eMail;
    }

    public void emptyPassword(){
        securePassword = password;
        password = "";
    }
    public void restorePassword(){
        password = securePassword;
    }

    public void addRole(Role role) {
        roles.add(role);
    }
    public Set<Role> getRole() {
        return roles;
    }
    public void setRole(Set<Role> role) {
        this.roles = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("id=");
        stringBuilder.append(id).append(", ");
        stringBuilder.append(" name=").append(name).append(", ");
        stringBuilder.append(" last name=").append(lastName).append(", ");
        return stringBuilder.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
