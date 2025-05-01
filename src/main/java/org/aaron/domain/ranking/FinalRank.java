package org.aaron.domain.ranking;

import java.util.*;

public class FinalRank {
    private final int position;
    private final Map<String, Integer> numberOfTimeTeamsAppearAtThisPosition = new HashMap<>();
    private final Map<String, Double> chancesToFinishAtThisPositionByTeam = new HashMap<>();

    FinalRank(int position) {
        this.position = position;
    }

    public void addTeam(String teamName) {
        if(Objects.nonNull(numberOfTimeTeamsAppearAtThisPosition.get(teamName))) {
            numberOfTimeTeamsAppearAtThisPosition.put(teamName, numberOfTimeTeamsAppearAtThisPosition.get(teamName) + 1);
        } else {
            numberOfTimeTeamsAppearAtThisPosition.put(teamName, 1);
        }
    }

    public void compute() {
        int numberOfTeams = getNumberOfTeamsApparitions();
        for(String team: numberOfTimeTeamsAppearAtThisPosition.keySet()) {
            extractPercentageForTeam(team, numberOfTeams);
        }
    }

    private int getNumberOfTeamsApparitions() {
        return numberOfTimeTeamsAppearAtThisPosition.values().stream().reduce(0, Integer::sum);
    }

    private void extractPercentageForTeam(String team, int totalNumberOfTeams) {
        int numberOfApparitions = numberOfTimeTeamsAppearAtThisPosition.get(team);
        double percentage = getChancesToFinishAtThisPosition(numberOfApparitions, totalNumberOfTeams);
        chancesToFinishAtThisPositionByTeam.put(team, percentage);
    }

    private double getChancesToFinishAtThisPosition(int numberOfApparition, int totalNbOfTeams) {
        double chanceToFinishAtThisPosition = (double) numberOfApparition / totalNbOfTeams * 100;
        return  (double)Math.round(chanceToFinishAtThisPosition * 100d) / 100d;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("| " + position + " |");
        for(Map.Entry<String, Double> entry: chancesToFinishAtThisPositionByTeam.entrySet()) {
            result.append(" ").append(entry.getKey()).append(" ").append(entry.getValue()).append(" |");
        }
        return result.toString();
    }
}
