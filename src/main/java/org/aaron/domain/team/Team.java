package org.aaron.domain.team;

import org.aaron.domain.match.MatchResult;
import org.aaron.domain.match.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team implements Comparable<Team>{
    private final Series series;
    private final Games games;
    private final String name;
    private final Map<String, Result> matchResultMap = new HashMap<>();
    private List<MatchResult> matchResultList = new ArrayList<>();

    public Team(String name) {
        this.name = name;
        this.series = new Series();
        this.games = new Games();
    }

    public Team(String name, Series series, Games games, List<MatchResult> matchResultList) {
        this.series = series;
        this.games = games;
        this.name = name;
        this.matchResultList = matchResultList;
        prepareMatchResults();
    }

    public Team(Team team) {
        name = team.name;
        games = new Games(team.getGames());
        series = new Series(team.getSeries());
        matchResultList = List.copyOf(team.matchResultList);
        prepareMatchResults();
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

    private void prepareMatchResults() {
        for (MatchResult matchResult: matchResultList) {
            matchResultMap.put(matchResult.oppositeTeam(), matchResult.result());
        }
    }

    public Team computeScore(String opponentName, int victory, int defeat) {
        MatchResult matchResult = new MatchResult(opponentName, victory == 2 ? Result.VICTORY : Result.DEFEAT);
        ArrayList<MatchResult> list = new ArrayList<>(matchResultList);
        list.add(matchResult);

        return new Team(
                name,
                new Series(series.getVictory() + (victory == 2 ? 1 : 0), series.getDefeat() + (victory == 2 ? 0 : 1)),
                new Games(games.getVictory() + victory, games.getDefeat() + defeat),
                list
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
                if(matchResultMap.containsKey(o.getName())) {
                    Result result = matchResultMap.get(o.getName());
                    if(result.equals(Result.VICTORY)) return -1;
                    return 1;
                }
            }
            return gamesComparison;
        }
        return seriesComparison;
    }
}
