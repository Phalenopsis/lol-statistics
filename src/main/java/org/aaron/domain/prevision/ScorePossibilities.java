package org.aaron.domain.prevision;

import org.aaron.domain.match.Score;

public enum ScorePossibilities {
    VICTORY_TWO_ZERO(new Score(2, 0)),
    VICTORY_TWO_ONE(new Score(2, 1)),
    DEFEAT_ONE_TWO(new Score(1, 2)),
    DEFEAT_ZERO_TWO(new Score(0, 2));

    private final Score score;

    ScorePossibilities(Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
}
