package org.aaron.domain.ranking;

import org.aaron.domain.team.RankedTeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinalRanking {
    private ArrayList<List<RankedTeam>> finalList = new ArrayList<>();
    private Map<Integer, FinalRank> finalMap = new HashMap<>();

    private final int numberOfTeams;

    public FinalRanking(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
        initializeFinalMap();
    }

    public void addList(List<RankedTeam> list) {
        finalList.add(list);
    }

    private void initializeFinalMap() {
        for(int i = 1; i <= numberOfTeams; i++) {
            finalMap.put(i, new FinalRank(i));
        }
    }

    public void compute() {
        for(List<RankedTeam> list: finalList) {
            for(RankedTeam team: list) {
                finalMap.get(team.getRank()).addTeam(team.getName());
            }
        }
        for(FinalRank finalRank: finalMap.values()) {
            finalRank.compute();
        }
    }

    public String getResults() {
        String result = "";
        for(int i = 1; i <= numberOfTeams; i++) {
            result = result + finalMap.get(i) + "\n";
        }
        return result;
    }
}
