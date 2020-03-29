package com.github.fwidder.statrechner.controller.rest;

import com.github.fwidder.statrechner.dao.PlayerRepository;
import com.github.fwidder.statrechner.dao.PlayerResourceRepository;
import com.github.fwidder.statrechner.dao.ResourcePackageRepository;
import com.github.fwidder.statrechner.dao.ResourceRepository;
import com.github.fwidder.statrechner.model.Player;
import com.github.fwidder.statrechner.model.PlayerResources;
import com.github.fwidder.statrechner.model.ResourcePackage;
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
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourcePackageRepository resourcePackageRepository;

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
        return savePlayerResources(playerResources);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public PlayerResources post(@RequestBody final PlayerResources playerResources) {
        playerResources.setId(null);

        return savePlayerResources(playerResources);
    }

    private PlayerResources savePlayerResources(@RequestBody PlayerResources playerResources) {
        Iterable<ResourcePackage> foodPackages = resourcePackageRepository.saveAll(playerResources.getFood().getResourcePackages());
        playerResources.getFood().getResourcePackages().clear();
        foodPackages.forEach(playerResources.getFood().getResourcePackages()::add);
        playerResources.setFood(resourceRepository.save(playerResources.getFood()));

        Iterable<ResourcePackage> woodPackages = resourcePackageRepository.saveAll(playerResources.getWood().getResourcePackages());
        playerResources.getWood().getResourcePackages().clear();
        woodPackages.forEach(playerResources.getWood().getResourcePackages()::add);
        playerResources.setWood(resourceRepository.save(playerResources.getWood()));

        Iterable<ResourcePackage> ironPackages = resourcePackageRepository.saveAll(playerResources.getIron().getResourcePackages());
        playerResources.getIron().getResourcePackages().clear();
        ironPackages.forEach(playerResources.getIron().getResourcePackages()::add);
        playerResources.setIron(resourceRepository.save(playerResources.getIron()));

        Iterable<ResourcePackage> silverPackages = resourcePackageRepository.saveAll(playerResources.getSilver().getResourcePackages());
        playerResources.getSilver().getResourcePackages().clear();
        silverPackages.forEach(playerResources.getSilver().getResourcePackages()::add);
        playerResources.setSilver(resourceRepository.save(playerResources.getSilver()));

        return playerResourceRepository.save(playerResources);
    }
}