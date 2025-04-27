package org.aaron.domain.team;

import java.util.Objects;

public class Games implements Comparable<Games>{
    private final int victory;
    private final int defeat;

    public Games(int victory, int defeat) {
        this.victory = victory;
        this.defeat = defeat;
    }

    public Games(Games games) {
        victory = games.victory;
        defeat = games.defeat;
    }

    public Games() {
        victory = 0;
        defeat = 0;
    }

    public int getVictory() {
        return victory;
    }

    public int getDefeat() {
        return defeat;
    }

    public int getGoalAverage() {
        return victory - defeat;
    }

    @Override
    public String toString() {
        return victory + " - " + defeat;
    }

    @Override
    public int compareTo(Games o) {
        int compareGoalAverage = Integer.compare(o.getGoalAverage(), getGoalAverage());
        if(compareGoalAverage != 0) return compareGoalAverage;
        return Integer.compare(o.victory, victory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Games games = (Games) o;
        return victory == games.victory && defeat == games.defeat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(victory, defeat);
    }
}
