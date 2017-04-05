package ru.rsoft.shold.backend.security;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Admin on 25.02.2017.
 */
public interface IChangePassword extends UserDetailsService {
    void changePassword(String username, String password);
}
