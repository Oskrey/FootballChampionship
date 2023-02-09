package com.example.footballchampionship;

import java.io.Serializable;

public class Matches implements Serializable{
    private long id;
    private int teamhouse;
    private int teamguest;
    private int goalshouse;
    private int goalsguest;

    public Matches (long id, int teamh, int teamg, int gh,int gg) {
        this.id = id;
        this.teamhouse = teamh;
        this.teamguest = teamg;
        this.goalshouse = gh;
        this.goalsguest = gg;
    }

    public long getId() {
        return id;
    }

    public int getTeamhouse() {
        return teamhouse;
    }

    public int getTeamguest() {
        return teamguest;
    }

    public int getGoalshouse() {
        return goalshouse;
    }

    public int getGoalsguest() {
        return goalsguest;
    }
}
