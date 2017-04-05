package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.shold.core.entity.AdminMessage;

/**
 * Created by Admin on 29.06.2016.
 */
public interface AdminMessageRepository extends JpaRepository<AdminMessage, Integer> {
}
