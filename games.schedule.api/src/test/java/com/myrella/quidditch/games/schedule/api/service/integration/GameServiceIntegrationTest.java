package com.myrella.quidditch.games.schedule.api.service.integration;

import com.myrella.quidditch.games.schedule.api.dao.GameDAO;
import com.myrella.quidditch.games.schedule.api.dao.GameRepository;
import com.myrella.quidditch.games.schedule.api.entity.Game;
import com.myrella.quidditch.games.schedule.api.exceptions.DateAlreadyRegisteredException;
import com.myrella.quidditch.games.schedule.api.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class GameServiceIntegrationTest {

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

    @DisplayName("Dado um jogo então o jogo deverá ser criado")
    @Test
    void dadoUmJogoEntaoOjogoDeveraSerCriado() {
        //when
        var gameSaved = gameService.createGame(game);
        //then
        assertEquals(game.getId(), gameSaved.getId());
    }

    @DisplayName("Dado um jogo com Id válido então um jogo deverá ser retornado")
    @Test
    void dadoUmJogoComIdValidoEntaoDeveraRetornarUmJogo() {
        var gameSaved = gameService.createGame(game);
        var gameFounded = gameService.getGameById(gameSaved.getId());

        assertNotNull(gameFounded);
    }

    @DisplayName("Dado um jogo com Id inválido então não deverá retornar um jogo")
    @Test
    void dadoUmJogoComIdInvalidoEntaoNaoDeveraRetornarUmJogo() {
        var gameFounded = gameService.getGameById(123);

        assertThat(gameFounded).isEmpty();
    }

    @DisplayName("Jogo com oponente1 vazio deverá retornar exceção")
    @Test
    void dadoUmJogoComOponente1VazioEntaoDeveraRetornarUmaExceçao() {
        game.setOpponent1("");
        // when
        var exception = assertThrows(IllegalArgumentException.class, () -> gameService.createGame(game));
        // then
        assertEquals("Opponent cannot be empty!", exception.getMessage());
    }

    @DisplayName("Jogo com oponente2 vazio deverá retornar exceção")
    @Test
    void dadoUmJogoComOponente2VazioEntaoDeveraRetornarUmaExceçao() {
        // given
        game.setOpponent2("");
        // when
        var exception = assertThrows(IllegalArgumentException.class, () -> gameService.createGame(game));
        // then
        assertEquals("Opponent cannot be empty!", exception.getMessage());
    }

    @DisplayName("Jogo com data já existente deverá retornar uma exceção")
    @Test
    void dadoUmJogoComDataJaExistenteEntaoDeveraRetornarUmaExceçao() {
        // given
        game.setDate("29/09/2022");
        Game game2 = new Game(2, "30/09/2022", "Corvinal", "Sonserina", "100", "300", "Em andamento");
        Game game3 = new Game(3, "29/09/2022", "Corvinal", "Sonserina", "100", "300", "Em andamento");

        gameService.createGame(game);
        gameService.createGame(game2);

        // when
        var exception = assertThrows(DateAlreadyRegisteredException.class, () -> gameService.createGame(game3));

        // then
        assertEquals("The date is already registered in the system.", exception.getMessage());
    }

    @DisplayName("Jogo com data não existente deverá ser criado")
    @Test
    void dadoUmJogoComDataNaoExistenteEntaoDeveraSerCriado() {
        Game game2 = new Game(2, "30/09/2022", "Corvinal", "Sonserina", "100", "300", "Em andamento");
        Game game3 = new Game(3, "29/09/2022", "Corvinal", "Sonserina", "100", "300", "Em andamento");

        gameService.createGame(game);
        gameService.createGame(game2);

        // when
        var gameReturned = gameService.createGame(game3);

        // then
        assertEquals(game3.getId(), gameReturned.getId());
    }



}

