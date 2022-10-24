package com.myrella.quidditch.games.schedule.api.dao;

import com.myrella.quidditch.games.schedule.api.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, Integer> {

}
