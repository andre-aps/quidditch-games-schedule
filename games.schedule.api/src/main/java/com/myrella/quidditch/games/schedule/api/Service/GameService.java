package com.myrella.quidditch.games.schedule.api.Service;

import com.myrella.quidditch.games.schedule.api.DAO.GameDAO;
import com.myrella.quidditch.games.schedule.api.Entity.Game;
import com.myrella.quidditch.games.schedule.api.Entity.GameUpdatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Optional;

@Service
public class GameService {
@Autowired
    private GameDAO gameDAO;

    public Collection<Game> getGames() {
return gameDAO.getGames();

    }

    public Game createGame(Game game) {
        return gameDAO.createGame(game);
    }

    public Optional<Game> getGameById(int id) {
        return gameDAO.getGameById(id);
    }

    public Optional<Game> deleteGameById(int id) {
        return gameDAO.deleteGameById(id);
    }

    public Optional<Game> updateGameById(int id, GameUpdatePayload gameUpdatePayload) {
        return gameDAO.updateGameById(id, gameUpdatePayload);
    }
}
