package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails, Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastname;

    @Column(name = "mail")
    private String mail;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public String rolesToString() {
        StringBuilder roles = new StringBuilder();
        Boolean switcher = false;
        for (Role role : this.roles) {
            if (!switcher) {
                roles.append(role.toString());
                switcher = true;
            } else {
                roles.append(", ");
                roles.append(role.toString());
                switcher = false;
            }


        }

        return roles.toString();
    }

    @Override
    public String toString() {
        return id + " " + username + " " + name + " " + lastname + ", " + mail + ", " + rolesToString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }


    @Override
    public String getUsername() {
        return username;
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

    public User() {
    }

    public User(String name, String lastName, String mail, String password) {
        this.name = name;
        this.lastname = lastName;
        this.mail = mail;
        this.password = password;

    }

    public User(Long id, String username, String password, String name, String lastName, String mail, Set<Role> roles) {
        this.id = id;
        this.password = password;
        this.username = username;


        this.name = name;
        this.lastname = lastName;
        this.mail = mail;
        this.roles = roles;
    }
}
