package com.github.fwidder.statrechner.controller.rest;

import com.github.fwidder.statrechner.dao.PlayerResourceRepository;
import com.github.fwidder.statrechner.model.PlayerResources;
import com.github.fwidder.statrechner.util.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest/PlayerResources")
public class PlayerResourcesRestController {

    @Autowired
    private PlayerResourceRepository playerResourceRepository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerResources findById(@PathVariable long id) {
        return playerResourceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object with ID "+id+" does not exist!"));
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<PlayerResources> findAll() {
        return playerResourceRepository.findAll();
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PlayerResources update(@PathVariable(value = "id", required = true) final long id, @RequestBody(required = true) final PlayerResources playerResources) {
        if(id != playerResources.getId())
            throw new AssertionError("ID in Path and ID in PlayerResources must be equal!");
        if(findById(id)==null)
            throw new ObjectNotFoundException("Object with ID "+id+" does not exist! To create a new Object use POST!");
        return playerResourceRepository.save(playerResources);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PlayerResources post(@RequestBody final PlayerResources playerResources) {
        playerResources.setId(null);
        return playerResourceRepository.save(playerResources);
    }
}