package org.aaron.domain.team;

import org.aaron.domain.match.MatchResult;
import org.aaron.domain.match.Result;

import java.util.HashMap;
import java.util.Map;

public class Team implements Comparable<Team>{
    private final Series series;
    private final Games games;
    private final String name;
    private Map<String, Result> versusMatchResults = new HashMap<>();

    public Team(String name) {
        this.name = name;
        this.series = new Series();
        this.games = new Games();
    }

    public Team(String name, Series series, Games games, Map<String, Result> versusMatchResults) {
        this.series = series;
        this.games = games;
        this.name = name;
        this.versusMatchResults.putAll(versusMatchResults);
    }

    public Team(Team team) {
        name = team.name;
        games = new Games(team.getGames());
        series = new Series(team.getSeries());
        versusMatchResults.putAll(team.versusMatchResults);
    }

    public String getName() {
        return name;
    }

    public String getSeriesInString() {
        return series.toString();
    }

    public String getGamesInString() {
        return games.toString();
    }

    public Series getSeries() {
        return series;
    }

    public Games getGames() {
        return games;
    }


    public Team computeScore(String opponentName, int victory, int defeat) {
        MatchResult matchResult = new MatchResult(opponentName, victory == 2 ? Result.VICTORY : Result.DEFEAT);
        versusMatchResults.put(matchResult.oppositeTeam(), matchResult.result());

        return new Team(
                name,
                new Series(series.getVictory() + (victory == 2 ? 1 : 0), series.getDefeat() + (victory == 2 ? 0 : 1)),
                new Games(games.getVictory() + victory, games.getDefeat() + defeat),
                versusMatchResults
        );
    }

    @Override
    public String toString() {
        return "Team{" +
                "series=" + series +
                ", games=" + games +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Team o) {
        int seriesComparison = series.compareTo(o.series);
        if(seriesComparison == 0) {
            int gamesComparison = games.compareTo(o.games);
            if(gamesComparison == 0) {
                if(versusMatchResults.containsKey(o.getName())) {
                    Result result = versusMatchResults.get(o.getName());
                    if(result.equals(Result.VICTORY)) return -1;
                    return 1;
                }
            }
            return gamesComparison;
        }
        return seriesComparison;
    }
}
