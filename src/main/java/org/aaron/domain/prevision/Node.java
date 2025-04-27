package org.aaron.domain.prevision;

import org.aaron.domain.ranking.FinalRanking;
import org.aaron.domain.ranking.Ranking;
import org.aaron.domain.match.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private static int counter = 0;
    private static final List<Score> POSSIBLE_SCORES = List.of(
            new Score(2, 0),
            new Score(2, 1),
            new Score(1, 2),
            new Score(0, 2));
    Node parent;

    List<Node> children = new ArrayList<>();

    Ranking ranking;

    public Node(Ranking pRanking) {
        ranking = pRanking;
        processChildren();
    }

    public Node(Ranking ranking, Score score, Node node) {
        counter += 1;
        this.ranking = new Ranking(ranking);
        parent = node;
        this.ranking.playNextMatch(score);
        processChildren();
    }

    private void processChildren() {
        if(!(Objects.isNull(ranking.getMatchList())) && !ranking.getMatchList().isEmpty()) {
            for(Score possibleScore: POSSIBLE_SCORES) {
                Node child = new Node(ranking, possibleScore , this);
            }
        } else {
            getAncestorFinalRanking().addList(ranking.getPlacedTeams());
        }

    }

    private FinalRanking getAncestorFinalRanking() {
        if(Objects.isNull(parent)) return ranking.getFinalRanking();
        return parent.getAncestorFinalRanking();
    }
}
