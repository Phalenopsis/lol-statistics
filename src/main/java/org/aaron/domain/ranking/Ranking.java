package org.aaron.domain.ranking;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.prevision.Node;
import org.aaron.domain.team.PlacedTeam;
import org.aaron.domain.team.Team;

import java.util.*;

public class Ranking {
    Map<String, Team> teams = new HashMap<>();
    ArrayList<Match> matchList;
    FinalRanking finalRanking;

    public Ranking(List<Team> teamList, ArrayList<Match> matchList) {
        setTeam(teamList);
        this.matchList = matchList;
        this.finalRanking = new FinalRanking(teamList.size());
    }

    public Ranking(Ranking ranking) {
        teams = duplicate(ranking.teams);
        matchList = duplicate(ranking.matchList);
        this.finalRanking = ranking.finalRanking;
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

    public void setMatchesToPlay(ArrayList<Match> matchList) {
        this.matchList = matchList;
    }

    public void playNextMatch(Score score) {
        Match match = matchList.removeFirst();
        Team team1 = match.getTeam1();
        Team team2 = match.getTeam2();
        Team team1AfterMatch = teams.get(team1.getName()).computeScore(team2.getName(), score.getScoreTeam1(), score.getScoreTeam2());
        Team team2AfterMatch = teams.get(team2.getName()).computeScore(team1.getName(), score.getScoreTeam2(), score.getScoreTeam1());

        teams.put(team1.getName(), team1AfterMatch);
        teams.put(team2.getName(), team2AfterMatch);
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

    public List<PlacedTeam> getPlacedTeams() {
        List<Team> orderedList = getOrderedList();
        List<PlacedTeam> teamList = new ArrayList<>();
        int actualRank = 0;
        int potentialRank = 0;
        Team previousTeam = new Team("error");

        for(Team team: orderedList) {
            if(actualRank == 0) {
                actualRank = 1;
                potentialRank = 1;
                teamList.add(new PlacedTeam(team, actualRank));
            } else {
                if(team.compareTo(previousTeam) == 0) {
                    teamList.add(new PlacedTeam(team, actualRank));
                } else {
                    actualRank = potentialRank;
                    teamList.add(new PlacedTeam(team, actualRank));
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
