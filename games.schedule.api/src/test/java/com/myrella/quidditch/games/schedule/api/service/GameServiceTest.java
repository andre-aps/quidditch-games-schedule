package com.myrella.quidditch.games.schedule.api.service;

import com.myrella.quidditch.games.schedule.api.DAO.GameDAO;
import com.myrella.quidditch.games.schedule.api.DAO.GameRepository;
import com.myrella.quidditch.games.schedule.api.Entity.Game;
import com.myrella.quidditch.games.schedule.api.Service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class GameServiceTest {

    @Autowired
    GameService gameService;
    @Autowired
    GameDAO gameDAO;
    @Autowired
    GameRepository gameRepository;

    private Game game;

    @BeforeEach
    void setup() {
        //given
        game = new Game();
        game.setId(new Random().nextInt());
        game.setDate("03/10/2022");
        game.setOpponent1("Grifinória");
        game.setOpponent2("Lufa-Lufa");
        game.setStatus("FINALIZADO");
        game.setScore_opponent1("100");
        game.setScore_opponent2("99");
    }

    @Test
    void dadoUmJogoEntaoOjogoDeveraSerCriado() {
        //when
        var gameSaved = gameService.createGame(game);
        //then
        assertThat(game).usingRecursiveComparison().isEqualTo(gameSaved);
    }

    @Test
    void dadoUmJogoComIdValidoEntaoDeveraRetornarUmJogo() {
        var gameSaved = gameService.createGame(game);
        var gameFounded = gameService.getGameById(gameSaved.getId());

        assertNotNull(gameFounded);
    }

    @Test
    void dadoUmJogoComIdInvalidoEntaoNaoDeveraRetornarUmJogo() {
        var gameFounded = gameService.getGameById(123);

        assertThat(gameFounded).isEmpty();
    }

}
