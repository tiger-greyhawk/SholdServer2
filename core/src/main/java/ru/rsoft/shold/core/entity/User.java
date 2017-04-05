package ru.rsoft.shold.core.entity;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.Set;

/**
 * Created by Admin on 16.11.2015.
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column (name = "username", unique = true, nullable = false)
    @Nonnull
    private String username;

    @Column (name = "password", nullable = false)
    @Nonnull
    private String password;

    @Column (name = "enabled", nullable = false)
    @Nonnull
    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<UserRole> roles;

    public User() {
        this("", null, true);
    }

    public User(@Nonnull String username,@Nonnull String password, @Nonnull boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    @Nonnull
    public boolean isEnabled() {
        return enabled;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEnabled(@Nonnull boolean enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    @Nonnull
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nonnull String username) {
        this.username = username;
    }

    @Nonnull
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nonnull String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", roles=" + roles +
                '}';
    }
}