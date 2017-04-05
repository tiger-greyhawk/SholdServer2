package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.shold.core.entity.FactionInvite;

import java.util.List;

/**
 * Created by Admin on 15.12.2016.
 */
public interface FactionInviteRepository extends JpaRepository<FactionInvite, Integer> {
    List<FactionInvite> findByPlayerId(int playerId);
}
