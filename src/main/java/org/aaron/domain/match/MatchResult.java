package org.aaron.domain.match;

public class MatchResult {
    private String oppositeTeam;
    private Result result;

    public MatchResult(String oppositeTeam, Result result) {
        this.oppositeTeam = oppositeTeam;
        this.result = result;
    }

    public String getOppositeTeam() {
        return oppositeTeam;
    }

    public Result getResult() {
        return result;
    }
}
