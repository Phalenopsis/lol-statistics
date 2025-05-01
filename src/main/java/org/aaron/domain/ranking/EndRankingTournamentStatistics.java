package org.aaron.domain.ranking;

import org.aaron.domain.team.RankedTeam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EndRankingTournamentStatistics {
    private final Map<Integer, StatisticsForGivenRank> statisticsForRanks = new HashMap<>();

    private final int numberOfTeams;

    public EndRankingTournamentStatistics(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
        initializeFinalMap();
    }

    public void addList(List<RankedTeam> list) {
        for(RankedTeam team: list) {
            statisticsForRanks.get(team.getRank()).addTeam(team.getName());
        }
    }

    private void initializeFinalMap() {
        for(int i = 1; i <= numberOfTeams; i++) {
            statisticsForRanks.put(i, new StatisticsForGivenRank(i));
        }
    }

    public void compute() {
        for(StatisticsForGivenRank statisticsForGivenRank : statisticsForRanks.values()) {
            statisticsForGivenRank.computePercent();
        }
    }

    public String getResults() {
        String result = "";
        for(int i = 1; i <= numberOfTeams; i++) {
            result = result + statisticsForRanks.get(i) + "\n";
        }
        return result;
    }
}
