package ru.rsoft.configurator.core.repository;

import ru.rsoft.configurator.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Set;

/**
 * Created by Admin on 16.11.2015.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Set<User> findByLogin (String login);
//    Set<User> getId();

}
