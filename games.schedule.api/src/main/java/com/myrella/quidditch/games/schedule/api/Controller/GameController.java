package com.myrella.quidditch.games.schedule.api.Controller;

import com.myrella.quidditch.games.schedule.api.Entity.Game;
import com.myrella.quidditch.games.schedule.api.Entity.GameUpdatePayload;
import com.myrella.quidditch.games.schedule.api.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
