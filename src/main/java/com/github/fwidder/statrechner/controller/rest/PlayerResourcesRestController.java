package com.github.fwidder.statrechner.controller.rest;

import com.github.fwidder.statrechner.dao.PlayerRepository;
import com.github.fwidder.statrechner.dao.PlayerResourceRepository;
import com.github.fwidder.statrechner.model.Player;
import com.github.fwidder.statrechner.model.PlayerResources;
import com.github.fwidder.statrechner.util.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("rest/PlayerResources")
public class PlayerResourcesRestController {

    @Autowired
    private PlayerResourceRepository playerResourceRepository;
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerResources findById(@PathVariable long id) {
        return playerResourceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object with ID " + id + " does not exist!"));
    }

    @GetMapping(value = "player/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<PlayerResources> findByPlayerId(@PathVariable Long playerId) {
        Optional<Player> player = playerRepository.findById(playerId);
        if (player.isEmpty())
            throw new ObjectNotFoundException("Player with ID " + playerId + " does not exist!");
        return playerResourceRepository.findByPlayerOrderByTimestampAsc(player.get());
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<PlayerResources> findAll() {
        return playerResourceRepository.findAll();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PlayerResources update(@PathVariable(value = "id") final long id, @RequestBody final PlayerResources playerResources) {
        if (id != playerResources.getId())
            throw new AssertionError("ID in Path and ID in PlayerResources must be equal!");
        if (findById(id) == null)
            throw new ObjectNotFoundException("Object with ID " + id + " does not exist! To create a new Object use POST!");
        return playerResourceRepository.save(playerResources);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PlayerResources post(@RequestBody final PlayerResources playerResources) {
        playerResources.setId(null);
        return playerResourceRepository.save(playerResources);
    }
}