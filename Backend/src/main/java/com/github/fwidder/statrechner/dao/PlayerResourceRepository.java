package com.github.fwidder.statrechner.dao;

import com.github.fwidder.statrechner.model.Player;
import com.github.fwidder.statrechner.model.PlayerResources;
import org.springframework.data.repository.CrudRepository;

public interface PlayerResourceRepository extends CrudRepository<PlayerResources, Long> {
    Iterable<PlayerResources> findByPlayerOrderByTimestampAsc(Player player);
}
