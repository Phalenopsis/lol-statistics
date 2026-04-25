package org.aaron;

import org.aaron.domain.match.Match;
import org.aaron.domain.ranking.Championship;
import org.aaron.domain.season.Spring2025;
import org.aaron.domain.season.Spring2026;
import org.aaron.domain.season.Summer2025;
import org.aaron.domain.team.Team;

import java.util.Map;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    /**
     * copie/colle la section des matchs sur une nouvelle classe copiée sur Summer2025
     * met à jour les équipes dans Teams (si des équipes ont changé)
     *      il faut faire les équipes dans initializeMap (avec initiales + nom)
     *      et les équipes static
     * @param args
     */
    public static void main(String[] args) {
        //Championship championship = Spring2025.getChampionship();
        //Championship championship = Summer2025.createChampionship();
        Championship championship = Spring2026.createChampionship();
        long start = System.currentTimeMillis();

        championship.computeAllRemainingMatches();
        //championship.computeMonteCarlo();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("computed in : " + timeElapsed + " ms");
        System.out.println(championship.giveResults());
    }
}