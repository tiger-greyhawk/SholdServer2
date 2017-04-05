package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.shold.core.entity.UserRole;

/**
 * Created by Admin on 26.02.2017.
 */
public interface RoleRepository extends JpaRepository<UserRole, Long> {

}
