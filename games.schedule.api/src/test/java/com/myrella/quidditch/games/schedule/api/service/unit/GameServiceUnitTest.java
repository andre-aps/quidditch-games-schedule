package com.myrella.quidditch.games.schedule.api.service.unit;


import com.myrella.quidditch.games.schedule.api.dao.GameDAO;
import com.myrella.quidditch.games.schedule.api.entity.Game;
import com.myrella.quidditch.games.schedule.api.exceptions.DateAlreadyRegisteredException;
import com.myrella.quidditch.games.schedule.api.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameServiceUnitTest {
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameDAO gameDAO;

    @Test()
    void quandoExecutarCreateGameComOponenteVazioDeveraLançarExceçao() {
        // given
        Game game = new Game(1, "19/10/2022", "", "Grifinoria", "100", "200", "Em andamento");
        // when
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> gameService.createGame(game));
        // then
        Assertions.assertEquals("Opponent cannot be empty!", exception.getMessage());

    }

    @Test()
    void quandoExecutarCreateGameNaoDeveraCriarGameComDateJaExistente() {

        Game game1 = new Game(1, "29/09/2022", "Grifinória", "Lufa-lufa", "100", "200", "Em andamento");
        Game game2 = new Game(2, "29/09/2022", "Grifinoria", "Sonserina", "100", "300", "Em andamento");
        gameService.createGame(game1);
        Assertions.assertThrows(DateAlreadyRegisteredException.class, () -> gameService.createGame(game2));
       //  Assertions.assertEquals("The date is already registered in the system.", exception.getMessage());
    }


}
