package org.aaron.domain.ranking;

import org.aaron.domain.team.Team;

import java.util.*;

public class FinalRank {
    private final int position;
    private final ArrayList<String> teams = new ArrayList<>();
    private final Map<String, Double> map = new HashMap<>();

    FinalRank(int position) {
        this.position = position;
    }

    public void addTeam(String teamName) {
        teams.add(teamName);
    }

    public void compute() {
        Set<String> set = new HashSet<>(teams);
        for(String team: set) {
            extractPercentageForTeam(team);
        }
    }

    private void extractPercentageForTeam(String team) {
        int numberOfTeams = teams.size();
        int numberOfApparitions = countApparitionsForTeam(team);
        double percentage = getChancesToFinishAtThisPosition(numberOfApparitions, numberOfTeams);
        map.put(team, percentage);
    }

    private int countApparitionsForTeam(String searchedTeam) {
        int numberOfApparitions = 0;
        for(String team: teams) {
            if(team.equals(searchedTeam)) numberOfApparitions += 1;
        }
        return numberOfApparitions;
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
