package org.aaron.domain.match;

public class Match {
    private final String team1;
    private final String team2;

    public Match(String team1, String team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Match(Match match) {
        team1 = match.team1;
        team2 = match.team2;
    }

    public String getTeam2() {
        return team2;
    }

    public String getTeam1() {
        return team1;
    }
}
