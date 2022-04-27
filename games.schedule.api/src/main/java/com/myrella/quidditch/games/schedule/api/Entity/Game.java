package com.myrella.quidditch.games.schedule.api.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Document(collection = "Game")
public class Game {

    @Id
    private Integer id;
    String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    private String opponent1;
    private String opponent2;
    private String score_opponent1;
    private String score_opponent2;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpponent1() {
        return opponent1;
    }

    public void setOpponent1(String opponent1) {
        this.opponent1 = opponent1;
    }

    public String getOpponent2() {
        return opponent2;
    }

    public void setOpponent2(String opponent2) {
        this.opponent2 = opponent2;
    }

    public String getScore_opponent1() {
        return score_opponent1;
    }

    public void setScore_opponent1(String score_opponent1) {
        this.score_opponent1 = score_opponent1;
    }

    public String getScore_opponent2() {
        return score_opponent2;
    }

    public void setScore_opponent2(String score_opponent2) {
        this.score_opponent2 = score_opponent2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
