package ru.rsoft.shold.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rsoft.shold.core.dto.FactionDto;
import ru.rsoft.shold.core.entity.Faction;
import ru.rsoft.shold.core.entity.Player;

import java.util.List;

/**
 * Created by Admin on 07.10.2016.
 */
public interface FactionRepository extends JpaRepository<Faction, Integer> {
    //List<FactionDto> findAll();
    List<Faction> findByOwner(String owner);
}
