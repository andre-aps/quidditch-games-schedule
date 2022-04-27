package com.myrella.quidditch.games.schedule.api.DAO;

import com.myrella.quidditch.games.schedule.api.Entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, Integer> {

}
