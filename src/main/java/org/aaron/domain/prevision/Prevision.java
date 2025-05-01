package org.aaron.domain.prevision;

import org.aaron.domain.ranking.FinalRankingStatistics;
import org.aaron.domain.ranking.Championship;
import org.aaron.domain.match.Score;

import java.util.List;
import java.util.Objects;

public class Prevision {
    private static final List<Score> POSSIBLE_SCORES = List.of(
            new Score(2, 0),
            new Score(2, 1),
            new Score(1, 2),
            new Score(0, 2));

    private static boolean isDone = false;

    Prevision parent;
    int depth;

    Championship championship;

    public Prevision(Championship pChampionship) {
        championship = pChampionship;
        processNextPrevision();
        depth = 0;
    }

    public Prevision(Championship championship, Score score, Prevision prevision) {
        depth = prevision.depth + 1;
        this.championship = new Championship(championship);
        parent = prevision;
        this.championship.playNextMatch(score);
        processNextPrevision();
    }

    private void processNextPrevision() {
        if(!(Objects.isNull(championship.getMatchList())) && !championship.getMatchList().isEmpty()) {
            for(Score possibleScore: POSSIBLE_SCORES) {
                Prevision child = new Prevision(championship, possibleScore , this);
            }
        } else {
            if(!isDone) {
                System.out.println("Depth : " + depth);
                isDone = true;
            }
            getAncestorFinalRankingStatistics().addList(championship.getPlacedTeams());
        }
    }

    private FinalRankingStatistics getAncestorFinalRankingStatistics() {
        if(Objects.isNull(parent)) return championship.getFinalRanking();
        return parent.getAncestorFinalRankingStatistics();
    }
}
