package org.aaron.domain.prevision;

import org.aaron.domain.match.Match;
import org.aaron.domain.ranking.Championship;

public class MonteCarlo {
    Championship championship;
    int numberOfIteration = 10000;

    public MonteCarlo(Championship championship) {
        this.championship = championship;
        compute();
    }

    public void compute() {
        for(int i = 0; i < numberOfIteration; i += 1) {
            processPrevision();
        }
    }

    private void processPrevision() {
        Championship championship1 = new Championship(championship);
        int numberOfMatchToPlay = championship1.getMatchList().size();
        for(int i = 0; i < numberOfMatchToPlay; i += 1) {
            championship1.playNextMatch(ScorePossibilities.getRandomPossibilities());
        }
        championship.getFinalRanking().addList(championship1.getPlacedTeams());
    }

}
