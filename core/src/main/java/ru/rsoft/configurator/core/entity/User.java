package ru.rsoft.configurator.core.entity;

import javax.persistence.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by Admin on 16.11.2015.
 */
@Entity
@Table(name="USERS_MY")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column (name = "LOGIN", nullable = false)
    @Nonnull
    private String login;

    @Column (name = "PASSWORD", nullable = false)
    @Nonnull
    private String password;

    public User() {
        this("", null);
    }

    public User(@Nonnull String login,@Nonnull String password) {
        this.login = login;
        this.password = password;
    }


    public Integer getId() {
        return id;
    }

    @Nonnull
    public String getLogin() {
        return login;
    }

    public void setLogin(@Nonnull String login) {
        this.login = login;
    }

    @Nonnull
    public String getPassword() {
        return password;
    }

    public void setPassword(@Nonnull String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}