package org.aaron.domain.match;

import org.aaron.domain.team.Team;

public class Match {
    private final Team team1;
    private final Team team2;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Match(Match match) {
        team1 = match.team1;
        team2 = match.team2;
    }

    public Team getTeam2() {
        return team2;
    }

    public Team getTeam1() {
        return team1;
    }
}
