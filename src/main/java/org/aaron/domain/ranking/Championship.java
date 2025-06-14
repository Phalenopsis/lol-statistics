package org.aaron.domain.ranking;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.prevision.Node;
import org.aaron.domain.team.RankedTeam;
import org.aaron.domain.team.Team;

import java.util.*;

public class Championship {
    Map<String, Team> teams = new HashMap<>();
    ArrayList<Match> matchList;
    FinalRanking finalRanking;

    public Championship(List<Team> teamList, ArrayList<Match> matchList) {
        setTeam(teamList);
        this.matchList = matchList;
        this.finalRanking = new FinalRanking(teamList.size());
    }

    public Championship(Championship championship) {
        teams = duplicate(championship.teams);
        matchList = duplicate(championship.matchList);
        this.finalRanking = championship.finalRanking;
    }

    private Map<String, Team> duplicate(Map<String, Team> teamsMap) {
        HashMap<String, Team> map = new HashMap<>();
        for(Team team: teamsMap.values()) {
            map.put(team.getName(), new Team(team));
        }
        return map;
    }

    private ArrayList<Match> duplicate(ArrayList<Match> matchList) {
        ArrayList<Match> list = new ArrayList<>();
        for(Match match: matchList) {
            list.add(new Match(match));
        }
        return list;
    }

    public void playNextMatch(Score score) {
        Match match = matchList.removeFirst();
        String team1 = match.getTeam1();
        String team2 = match.getTeam2();
        Team team1AfterMatch = teams.get(team1).computeScore(team2, score.scoreTeam1(), score.scoreTeam2());
        Team team2AfterMatch = teams.get(team2).computeScore(team1, score.scoreTeam2(), score.scoreTeam1());

        teams.put(team1, team1AfterMatch);
        teams.put(team2, team2AfterMatch);
    }

    private void setTeam(List<Team> teamList) {
        for(Team team: teamList) {
            teams.put(team.getName(), team);
        }
    }

    public Team getTeam(String name) {
        return teams.get(name);
    }

    public FinalRanking getFinalRanking() {
        return finalRanking;
    }

    public ArrayList<Match> getMatchList() {
        return matchList;
    }

    public List<Team> getOrderedList() {
        List<Team> unorderedList = new ArrayList<>(teams.values());

        return unorderedList.stream().sorted().toList();
    }

    public List<RankedTeam> getPlacedTeams() {
        List<Team> orderedList = getOrderedList();
        List<RankedTeam> teamList = new ArrayList<>();
        int actualRank = 0;
        int potentialRank = 0;
        Team previousTeam = new Team("error");

        for(Team team: orderedList) {
            if(actualRank == 0) {
                actualRank = 1;
                potentialRank = 1;
                teamList.add(new RankedTeam(team, actualRank));
            } else {
                if(team.compareTo(previousTeam) == 0) {
                    teamList.add(new RankedTeam(team, actualRank));
                } else {
                    actualRank = potentialRank;
                    teamList.add(new RankedTeam(team, actualRank));
                }
            }
            previousTeam = team;
            potentialRank += 1;
        }
        return teamList;
    }

    public void compute() {
        Node node = new Node(this);
        finalRanking.compute();
    }

    public String giveResults() {
        return finalRanking.getResults();
    }
}
