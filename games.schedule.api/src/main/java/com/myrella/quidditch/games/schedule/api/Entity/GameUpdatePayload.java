package com.myrella.quidditch.games.schedule.api.Entity;

public class GameUpdatePayload {
    private String score_opponent1;
    private String score_opponent2;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
