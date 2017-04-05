package ru.rsoft.shold.backend.security;

import org.springframework.stereotype.Service;
import ru.rsoft.shold.core.entity.User;

/**
 * Created by Admin on 27.02.2017.
 */

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
