package org.aaron.domain.ranking;

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

        int totalNbOfTeams = teams.size();
        for(String uniqueTeam: set) {
            int counter = 0;
            for(String team: teams) {
                if(team.equals(uniqueTeam)) counter += 1;
            }
            double chanceToFinishAtThisPosition = (double) counter / totalNbOfTeams * 100;
            double roundedValue = (double)Math.round(chanceToFinishAtThisPosition * 100d) / 100d;
            map.put(uniqueTeam, roundedValue);
        }
    }

    @Override
    public String toString() {
        String result = "| " + position + " |";
        for(Map.Entry<String, Double> entry: map.entrySet()) {
            result = result + " " + entry.getKey() + " " + entry.getValue() + " |";
        }
        return result;
    }
}
