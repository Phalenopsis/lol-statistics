package org.aaron.domain.match;

public class MatchResult {
    private final String oppositeTeam;
    private final Result result;

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
