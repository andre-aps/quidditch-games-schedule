package com.myrella.quidditch.games.schedule.api.dao;

import com.myrella.quidditch.games.schedule.api.entity.Game;
import com.myrella.quidditch.games.schedule.api.entity.GameUpdatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class GameDAO {

    @Autowired
    private GameRepository repository;

    public Collection<Game> getGames() {
        return repository.findAll();
    }

    public Game createGame(Game game) {
        return repository.insert(game);
    }

    public Optional<Game> getGameById(int id) {
        return repository.findById(id);
    }

    public Optional<Game> deleteGameById(int id) {
        Optional<Game> game = repository.findById(id);
        game.ifPresent(g -> repository.delete(g));
        return game;
    }

    public Optional<Game> updateGameById(int id, GameUpdatePayload gameUpdatePayload) {
        Optional<Game> game = repository.findById(id);
        game.ifPresent(g -> g.setScore_opponent1(gameUpdatePayload.getScore_opponent1()));
        game.ifPresent(g -> g.setScore_opponent2(gameUpdatePayload.getScore_opponent2()));
        game.ifPresent(g -> g.setStatus(gameUpdatePayload.getStatus()));
        game.ifPresent(g -> repository.save(g));
        return game;
    }

}
