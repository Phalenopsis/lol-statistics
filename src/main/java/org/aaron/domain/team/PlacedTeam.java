package org.aaron.domain.team;

public class PlacedTeam extends Team {
    private final int rank;

    public PlacedTeam(Team team, int rank) {
        super(team);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}
