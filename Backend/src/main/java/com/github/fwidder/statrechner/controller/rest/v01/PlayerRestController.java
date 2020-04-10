package com.github.fwidder.statrechner.controller.rest.v01;

import com.github.fwidder.statrechner.dao.PlayerRepository;
import com.github.fwidder.statrechner.model.Player;
import com.github.fwidder.statrechner.util.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/v01/Player")
public class PlayerRestController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Player findById(@PathVariable long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object with ID " + id + " does not exist!"));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Player update(@PathVariable(value = "id") final long id, @RequestBody final Player player) {
        if (id != player.getId())
            throw new AssertionError("ID in Path and ID in PlayerResources must be equal!");
        if (findById(id) == null)
            throw new ObjectNotFoundException("Object with ID " + id + " does not exist! To create a new Object use POST!");
        return playerRepository.save(player);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Player post(@RequestBody final Player player) {
        player.setId(null);
        return playerRepository.save(player);
    }
}