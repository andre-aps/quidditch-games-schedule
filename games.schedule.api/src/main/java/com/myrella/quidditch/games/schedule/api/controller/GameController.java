package com.myrella.quidditch.games.schedule.api.controller;

import com.myrella.quidditch.games.schedule.api.entity.Game;
import com.myrella.quidditch.games.schedule.api.entity.GameUpdatePayload;
import com.myrella.quidditch.games.schedule.api.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public Collection<Game> getGames() {
        return gameService.getGames();
    }

    @PostMapping
    public Game postGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @GetMapping(value = "/{id}")
    public Optional<Game> getGameById(@PathVariable("id") int id) {
        return gameService.getGameById(id);
    }

    @DeleteMapping(value = "/{id}")
    public Optional<Game> deleteGameById(@PathVariable("id") int id) {
        return gameService.deleteGameById(id);
    }

    @PutMapping(value = "/{id}")
    public Optional<Game> deleteGameById(@PathVariable("id") int id, @RequestBody GameUpdatePayload gameUpdatePayload) {
        return gameService.updateGameById(id, gameUpdatePayload);
    }

}
