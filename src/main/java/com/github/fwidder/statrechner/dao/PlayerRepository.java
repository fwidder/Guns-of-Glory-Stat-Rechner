package com.github.fwidder.statrechner.dao;

import com.github.fwidder.statrechner.model.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
