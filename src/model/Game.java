package model;

import java.util.Date;

/**
 * Store game related stats - kills/deaths/mvps etc.
 */
public class Game {

    private int kills;
    private int deaths;
    private int assists;
    private int roundsWon;
    private int roundsLost;
    private int mvp;
    private int id;

    public Game(int id, int kills, int deaths, int assists, int roundsWon, int roundsLost, int mpv) {
        this.id = id;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.roundsWon = roundsWon;
        this.roundsLost = roundsLost;
        this.mvp = mpv;
    }

    /*
     * ID should be the date/time represented as an int
     */
    public Game(int kills, int deaths, int assists, int roundsWon, int roundsLost, int mvp) {
        this((int)(new Date().getTime()/1000), kills, deaths, assists, roundsWon, roundsLost, mvp);
    }

    /*
     * Return player's Kill/Death ratio for the match
     */
    public double KDR () {
        //If KDR == infinity return 0 for now;
        if(kills == 0 || deaths == 0)
            return 0.0;
        return (double)kills/deaths;
    }

    /*
     * Values formatted for SQL statement
     */
    public String getValues() {
        return "(" + id + "," + kills + "," + deaths + "," + roundsWon + "," + roundsLost + "," + mvp + "," + assists + ")";
    }

    /*
     * return net rounds gained
     */
    public int netRounds() {
        return roundsWon - roundsLost;
    }

    public void setKills(int n) {
        if(n > Integer.MAX_VALUE)
            throw new RuntimeException("Integer overflow occurred - kills");
        else if(n >= 0) //Ignoring negative kills
            this.kills = n;
    }

    public void setDeaths(int n) {
        if(n > Integer.MAX_VALUE)
            throw new RuntimeException("Integer overflow occurred - deaths");
        else if(n >= 0) //Impossible to have negative deaths
            this.deaths = n;
    }

    public void setAssists(int n) {
        if(n > Integer.MAX_VALUE)
            throw new RuntimeException("Integer overflow occurred - assists");
        else if(n >= 0)
            this.assists = n;
    }

    public void setRoundsWon(int n) {
        if(n > Integer.MAX_VALUE)
            throw new RuntimeException("Integer overflow occurred - rounds won");
        else if(n >= 0 && n <= 16) //16 round wins = game over
            this.roundsWon = n;
    }

    public void setRoundsLost(int n) {
        if(n > Integer.MAX_VALUE)
            throw new RuntimeException("Integer overflow occurred - rounds lost");
        else if(n >= 0 && n <= 16)
            this.roundsLost = n;
    }

    public void setMvp(int n) {
        if(n > Integer.MAX_VALUE)
            throw new RuntimeException("Integer overflow occurred - mvp");
        else if(n >= 0 && n <= 16)
            this.mvp = n;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public int getRoundsLost() {
        return roundsLost;
    }

    public int getMvp() {
        return mvp;
    }

    @Override
    public String toString() {
        //Format date
        return new Date(((long)id)*1000L) + "\n" + "RW: " + roundsWon + "\n" + "RL: " + roundsLost + " \n " + kills + " " + deaths;
    }
}
