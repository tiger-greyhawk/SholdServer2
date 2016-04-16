package ru.rsoft.configurator.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.configurator.core.entity.Player;

/**
 * Created by Admin on 26.11.2015.
 */
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}
