package org.aaron.domain.prevision;

import org.aaron.domain.ranking.FinalRanking;
import org.aaron.domain.ranking.Championship;
import org.aaron.domain.match.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private static final List<Score> POSSIBLE_SCORES = List.of(
            new Score(2, 0),
            new Score(2, 1),
            new Score(1, 2),
            new Score(0, 2));

    private static boolean isDone = false;

    Node parent;
    int depth;

    Championship championship;

    public Node(Championship pChampionship) {
        championship = pChampionship;
        processChildren();
        depth = 0;
    }

    public Node(Championship championship, Score score, Node node) {
        depth = node.depth + 1;
        this.championship = new Championship(championship);
        parent = node;
        this.championship.playNextMatch(score);
        processChildren();
    }

    private void processChildren() {
        if(!(Objects.isNull(championship.getMatchList())) && !championship.getMatchList().isEmpty()) {
            for(Score possibleScore: POSSIBLE_SCORES) {
                Node child = new Node(championship, possibleScore , this);
            }
        } else {
            if(!isDone) {
                System.out.println("Deepth : " + depth);
                isDone = true;
            }
            getAncestorFinalRanking().addList(championship.getPlacedTeams());
        }
    }

    private FinalRanking getAncestorFinalRanking() {
        if(Objects.isNull(parent)) return championship.getFinalRanking();
        return parent.getAncestorFinalRanking();
    }
}
