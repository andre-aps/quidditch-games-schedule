package com.myrella.quidditch.games.schedule.api.service.unit;


import com.myrella.quidditch.games.schedule.api.dao.GameDAO;
import com.myrella.quidditch.games.schedule.api.entity.Game;
import com.myrella.quidditch.games.schedule.api.exceptions.DateAlreadyRegisteredException;
import com.myrella.quidditch.games.schedule.api.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceUnitTest {
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameDAO gameDAO;

    @Test()
    void quandoExecutarCreateGameComOponente1VazioDeveraLançarExceçao() {
        Game game = new Game(1, "19/10/2022", "", "Grifinoria", "100", "200", "Em andamento");

        var exception = assertThrows(IllegalArgumentException.class, () -> gameService.createGame(game));
        assertEquals("Opponent cannot be empty!", exception.getMessage());

    }

    @Test()
    void quandoExecutarCreateGameComOponente2VazioDeveraLançarExceçao() {
        Game game = new Game(1, "26/10/2022", "Sonserina", "", "200", "300", "Finalizado");
        var exception = assertThrows(IllegalArgumentException.class, () -> gameService.createGame(game));
        assertEquals("Opponent cannot be empty!", exception.getMessage());
    }

    @Test()
    void quandoExecutarCreateGameNaoDeveraCriarGameComDateJaExistente() {
        Game game1 = new Game(1, "29/09/2022", "Grifinória", "Lufa-lufa", "100", "200", "Em andamento");
        Game game2 = new Game(2, "30/09/2022", "Corvinal", "Sonserina", "100", "300", "Em andamento");
        Game game3 = new Game(3, "29/09/2022", "Corvinal", "Sonserina", "100", "300", "Em andamento");

        when(gameDAO.getGames()).thenReturn(List.of(game1, game2));
        var exception = assertThrows(DateAlreadyRegisteredException.class, () -> gameService.createGame(game3));
        assertEquals("The date is already registered in the system.", exception.getMessage());
    }

    @Test()
    void quandoExecutarCreateGameDeveraCriarGameComDateNaoExistente() {
        Game game1 = new Game(1, "29/09/2022", "Grifinória", "Lufa-lufa", "100", "200", "Em andamento");
        Game game2 = new Game(2, "30/09/2022", "Corvinal", "Sonserina", "100", "300", "Em andamento");
        Game game3 = new Game(3, "01/10/2022", "Corvinal", "Sonserina", "100", "300", "Em andamento");

        when(gameDAO.getGames()).thenReturn(List.of(game1, game2));
        when(gameDAO.createGame(any())).thenReturn(game3);
        var gameReturned = gameService.createGame(game3);
        assertEquals(game3.getId(), gameReturned.getId());
    }

}
