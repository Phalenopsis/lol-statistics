package org.aaron.domain.season;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;

import java.util.List;

public class CopiedMatch {
    private boolean played = false;
    private final String firstTeam;
    private final String secondTeam;
    private int firstScore;
    private int secondScore;

    public CopiedMatch(List<String> stringList) {
        firstTeam = Teams.getTeamMap().get(stringList.getFirst());
        secondTeam = Teams.getTeamMap().get(stringList.getLast());
        if(stringList.size() == 4) initializePlayedMatch(stringList);
    }

    private void initializePlayedMatch(List<String> stringList) {
        played = true;
        firstScore = Integer.parseInt(stringList.get(1));
        secondScore = Integer.parseInt(stringList.get(2));
    }

    public Match getMatch() {
        return new Match(firstTeam, secondTeam);
    }

    public Score getScore() {
        return new Score(firstScore, secondScore);
    }

    public boolean isPlayed() {
        return played;
    }
}
