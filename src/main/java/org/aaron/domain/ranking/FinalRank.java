package org.aaron.domain.ranking;

import java.util.*;

public class FinalRank {
    private final int position;
    private final Map<String, Integer> teamCounter = new HashMap<>();
    private final Map<String, Double> map = new HashMap<>();

    FinalRank(int position) {
        this.position = position;
    }

    public void addTeam(String teamName) {
        if(Objects.nonNull(teamCounter.get(teamName))) {
            teamCounter.put(teamName, teamCounter.get(teamName) + 1);
        } else {
            teamCounter.put(teamName, 1);
        }
    }

    public void compute() {
        int numberOfTeams = getNumberOfTeamsApparitions();
        for(String team: teamCounter.keySet()) {
            extractPercentageForTeam(team, numberOfTeams);
        }
    }

    private int getNumberOfTeamsApparitions() {
        return teamCounter.values().stream().reduce(0, Integer::sum);
    }

    private void extractPercentageForTeam(String team, int totalNumberOfTeams) {
        int numberOfApparitions = teamCounter.get(team);
        double percentage = getChancesToFinishAtThisPosition(numberOfApparitions, totalNumberOfTeams);
        map.put(team, percentage);
    }

    private double getChancesToFinishAtThisPosition(int numberOfApparition, int totalNbOfTeams) {
        double chanceToFinishAtThisPosition = (double) numberOfApparition / totalNbOfTeams * 100;
        return  (double)Math.round(chanceToFinishAtThisPosition * 100d) / 100d;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("| " + position + " |");
        for(Map.Entry<String, Double> entry: map.entrySet()) {
            result.append(" ").append(entry.getKey()).append(" ").append(entry.getValue()).append(" |");
        }
        return result.toString();
    }
}
