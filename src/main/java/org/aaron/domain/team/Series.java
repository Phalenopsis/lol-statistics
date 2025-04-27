package org.aaron.domain.team;

import java.util.Objects;

public class Series implements Comparable<Series> {
    private final int victory;
    private final int defeat;

    public Series(int victory, int defeat) {
        this.victory = victory;
        this.defeat = defeat;
    }

    public Series(Series series) {
        victory = series.victory;
        defeat = series.defeat;
    }

    public Series() {
        victory = 0;
        defeat = 0;
    }

    public int getVictory() {
        return victory;
    }

    public int getDefeat() {
        return defeat;
    }

    @Override
    public String toString() {
        return victory + " - " + defeat;
    }

    @Override
    public int compareTo(Series o) {
        return Integer.compare(o.victory, victory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return victory == series.victory && defeat == series.defeat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(victory, defeat);
    }
}
