package org.aaron;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.ranking.Championship;
import org.aaron.domain.team.RankedTeam;
import org.aaron.domain.team.Team;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static String znt = "ZennIt";
    private static String mcon = "mCon esports";
    private static String snsh = "Senshi eports";
    private static String aoma = "A One Man Army";
    private static String myth = "Myth Esport";
    private static String ouat = "Once Upon A Team";
    private static String jmgg = "Jörmungang";
    private static String ara = "Aurora";

    public static void main(String[] args) {
        Championship championship = getBeneluxChampionship();

        playWeek1(championship);

        playWeek2(championship);

        playWeek3(championship);

        playWeek4(championship);

        playWeek5(championship);

        playWeek6(championship);

        //playWeek7(championship);


        List<RankedTeam> list = championship.getPlacedTeams();

        System.out.println(list);

        long start = System.currentTimeMillis();
        championship.computeAllRemainingMatches();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("computed in : " + timeElapsed + " ms");
        System.out.println(championship.giveResults());
    }

    private static List<Match> prepareWeek7() {
        Match matchToDo1 = new Match(ouat, aoma);
        Match matchToDo2 = new Match(ara, jmgg);
        Match matchToDo3 = new Match(znt, myth);
        Match matchToDo4 = new Match(mcon, snsh);

        return List.of(matchToDo1, matchToDo2, matchToDo3, matchToDo4);
    }

    private static void playWeek7(Championship championship) {
        championship.playNextMatch(new Score(0, 2));
        championship.playNextMatch(new Score(0, 2));
        //championship.playNextMatch(new Score(2, 1));
        //championship.playNextMatch(new Score(2, 0));
    }

    private static List<Match> prepareWeek6() {
        Match matchToDo1 = new Match(mcon, ara);
        Match matchToDo2 = new Match(znt, ouat);
        Match matchToDo3 = new Match(snsh, jmgg);
        Match matchToDo4 = new Match(aoma, myth);

        return List.of(matchToDo1, matchToDo2, matchToDo3, matchToDo4);
    }

    private static void playWeek6(Championship championship) {
        championship.playNextMatch(new Score(2, 0));
        championship.playNextMatch(new Score(2, 0));
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(2, 0));
    }

    private static List<Match> prepareWeek5() {
        Match matchToDo1 = new Match(mcon, jmgg);
        Match matchToDo2 = new Match(snsh, ara);
        Match matchToDo3 = new Match(znt, aoma);
        Match matchToDo4 = new Match(ouat, myth);

        return List.of(matchToDo1, matchToDo2, matchToDo3, matchToDo4);
    }

    private static void playWeek5(Championship championship) {
        championship.playNextMatch(new Score(2, 0));
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(0, 2));
        championship.playNextMatch(new Score(0, 2));
    }

    private static List<Match> prepareWeek4() {
        Match matchToDo1 = new Match(znt, jmgg);
        Match matchToDo2 = new Match(myth, mcon);
        Match matchToDo3 = new Match(ouat, snsh);
        Match matchToDo4 = new Match(aoma, ara);

        return List.of(matchToDo1, matchToDo2, matchToDo3, matchToDo4);
    }

    private static void playWeek4(Championship championship) {
        championship.playNextMatch(new Score(2, 0));
        championship.playNextMatch(new Score(1, 2));
        championship.playNextMatch(new Score(1, 2));
        championship.playNextMatch(new Score(2, 0));
    }

    private static List<Match> prepareWeek3() {
        Match matchToDo1 = new Match(myth, ara);
        Match matchToDo2 = new Match(ouat, jmgg);
        Match matchToDo3 = new Match(aoma, mcon);
        Match matchToDo4 = new Match(znt, snsh);

        return List.of(matchToDo1, matchToDo2, matchToDo3, matchToDo4);
    }

    private static void playWeek3(Championship championship) {
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(0, 2));
        championship.playNextMatch(new Score(2, 0));
    }

    private static List<Match> prepareWeek2() {
        Match matchToDo1 = new Match(znt, mcon);
        Match matchToDo2 = new Match(aoma,snsh);
        Match matchToDo3 = new Match(ouat, ara);
        Match matchToDo4 = new Match(myth, jmgg);

        return List.of(matchToDo1, matchToDo2, matchToDo3, matchToDo4);
    }

    private static void playWeek2(Championship championship) {
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(1, 2));
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(1, 2));
    }

    private static List<Match> prepareWeek1() {
        Match matchToDo1 = new Match(myth, snsh);
        Match matchToDo2 = new Match(ouat, mcon);
        Match matchToDo3 = new Match(aoma, jmgg);
        Match matchToDo4 = new Match(znt, ara);

        return List.of(matchToDo1, matchToDo2, matchToDo3, matchToDo4);
    }

    private static void playWeek1(Championship championship) {
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(1, 2));
        championship.playNextMatch(new Score(2, 0));
        championship.playNextMatch(new Score(2, 0));
    }

    private static Championship getBeneluxChampionship() {
        Team zntTeam = new Team(znt);
        Team mconTeam = new Team(mcon);
        Team snshTeam = new Team(snsh);
        Team aomaTeam = new Team(aoma);
        Team mythTeam = new Team(myth);
        Team ouatTeam = new Team(ouat);
        Team jmggTeam = new Team(jmgg);
        Team araTeam = new Team(ara);

        List<Team> teams = List.of(zntTeam, mconTeam, snshTeam, aomaTeam, mythTeam, ouatTeam, jmggTeam, araTeam);

        List<Match> week1 = prepareWeek1();
        List<Match> week2 = prepareWeek2();
        List<Match> week3 = prepareWeek3();
        List<Match> week4 = prepareWeek4();
        List<Match> week5 = prepareWeek5();
        List<Match> week6 = prepareWeek6();
        List<Match> week7 = prepareWeek7();

        ArrayList<Match> matchList = new ArrayList<>();
        matchList.addAll(week1);
        matchList.addAll(week2);
        matchList.addAll(week3);
        matchList.addAll(week4);
        matchList.addAll(week5);
        matchList.addAll(week6);
        matchList.addAll(week7);

        return new Championship(teams, matchList);
    }
}