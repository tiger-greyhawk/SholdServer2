package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.shold.core.entity.User;

import java.util.Set;

/**
 * Created by Admin on 16.11.2015.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername (String login);
//    Set<User> getId();

}
