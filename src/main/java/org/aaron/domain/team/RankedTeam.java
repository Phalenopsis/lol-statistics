package org.aaron.domain.team;

public class RankedTeam extends Team {
    private final int rank;

    public RankedTeam(Team team, int rank) {
        super(team);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
