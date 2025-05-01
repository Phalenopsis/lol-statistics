package org.aaron.domain.ranking;

import java.util.*;

public class StatisticsForGivenRank {
    private final int rank;
    private final Map<String, Integer> teamAllocationsAtThisRank = new HashMap<>();
    private final Map<String, Double> chancesToFinishAtThisPositionByTeam = new HashMap<>();

    StatisticsForGivenRank(int rank) {
        this.rank = rank;
    }

    public void addTeam(String teamName) {
        if(Objects.nonNull(teamAllocationsAtThisRank.get(teamName))) {
            teamAllocationsAtThisRank.put(teamName, teamAllocationsAtThisRank.get(teamName) + 1);
        } else {
            teamAllocationsAtThisRank.put(teamName, 1);
        }
    }

    public void computePercent() {
        int teamAllocations = totalTeamAllocations();
        for(String team: teamAllocationsAtThisRank.keySet()) {
            extractPercentageFor(team, teamAllocations);
        }
    }

    private int totalTeamAllocations() {
        return teamAllocationsAtThisRank.values().stream().reduce(0, Integer::sum);
    }

    private void extractPercentageFor(String team, int teamAllocations) {
        int numberOfAllocations = teamAllocationsAtThisRank.get(team);
        double percentage = getChancesToFinishAtThisPosition(numberOfAllocations, teamAllocations);
        chancesToFinishAtThisPositionByTeam.put(team, percentage);
    }

    private double getChancesToFinishAtThisPosition(int numberOfAllocations, int teamAllocations) {
        double chanceToFinishAtThisPosition = (double) numberOfAllocations / teamAllocations * 100;
        return  (double)Math.round(chanceToFinishAtThisPosition * 100d) / 100d;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("| " + rank + " |");
        for(Map.Entry<String, Double> entry: chancesToFinishAtThisPositionByTeam.entrySet()) {
            result.append(" ").append(entry.getKey()).append(" ").append(entry.getValue()).append(" |");
        }
        return result.toString();
    }
}
