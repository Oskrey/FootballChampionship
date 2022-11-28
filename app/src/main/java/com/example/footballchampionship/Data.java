package com.example.footballchampionship;

public class Data {
    int ID;
    String TeamHome;
    String TeamGuest;
    int GoalsHome;
    int GoalsGuest;

    public Data(int ID, String teamHome, String teamGuest, int goalsHome, int goalsGuest) {
        this.ID = ID;
        TeamHome = teamHome;
        TeamGuest = teamGuest;
        GoalsHome = goalsHome;
        GoalsGuest = goalsGuest;
    }
}
