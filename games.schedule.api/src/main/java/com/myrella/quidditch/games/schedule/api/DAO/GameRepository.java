package com.myrella.quidditch.games.schedule.api.DAO;

import com.myrella.quidditch.games.schedule.api.Entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, Integer> {

}
