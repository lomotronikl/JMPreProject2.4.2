package web.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import java.util.Set;


@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String roleName;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> user;

    public Role() {

    }

    public Role (Long id){
        this.id = id;
    }

    public Role (Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    @Override
     public String toString(){
        return this.roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
