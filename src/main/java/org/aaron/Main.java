package org.aaron;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.ranking.Championship;
import org.aaron.domain.team.Team;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String znt = "ZennIt";     //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        String mcon = "mCon esports";
        String snsh = "Senshi eports";
        String aoma = "A One Man Army";
        String myth = "Myth Esport";
        String ouat = "Once Upon A Team";
        String jmgg = "Jörmungang";
        String ara = "Aurora";


        Team zntTeam = new Team("ZennIt");     //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        Team mconTeam = new Team("mCon esports");
        Team snshTeam = new Team("Senshi eports");
        Team aomaTeam = new Team("A One Man Army");
        Team mythTeam = new Team("Myth Esport");
        Team ouatTeam = new Team("Once Upon A Team");
        Team jmggTeam = new Team("Jörmungang");
        Team araTeam = new Team("Aurora");

        List<Team> teams = List.of(zntTeam, mconTeam, snshTeam, aomaTeam, mythTeam, ouatTeam, jmggTeam, araTeam);

        Match matchToDoW1_1 = new Match(myth, snsh);
        Match matchToDoW1_2 = new Match(ouat, mcon);
        Match matchToDoW1_3 = new Match(aoma, jmgg);
        Match matchToDoW1_4 = new Match(znt, ara);

        Match matchToDoW2_1 = new Match(znt, mcon);
        Match matchToDoW2_2 = new Match(aoma,snsh);
        Match matchToDoW2_3 = new Match(ouat, ara);
        Match matchToDoW2_4 = new Match(myth, jmgg);

        List<Match> matchList1 = List.of(matchToDoW1_1, matchToDoW1_2, matchToDoW1_3, matchToDoW1_4, matchToDoW2_1, matchToDoW2_2, matchToDoW2_3, matchToDoW2_4);

        Match matchToDoW3_1 = new Match(myth, ara);
        Match matchToDoW3_2 = new Match(ouat, jmgg);
        Match matchToDoW3_3 = new Match(aoma, mcon);
        Match matchToDoW3_4 = new Match(znt, snsh);

        Match matchToDoW4_1 = new Match(znt, jmgg);
        Match matchToDoW4_2 = new Match(myth, mcon);
        Match matchToDoW4_3 = new Match(ouat, snsh);
        Match matchToDoW4_4 = new Match(aoma, ara);

        List<Match> matchList2 = List.of(matchToDoW3_1, matchToDoW3_2, matchToDoW3_3, matchToDoW3_4, matchToDoW4_1, matchToDoW4_2, matchToDoW4_3, matchToDoW4_4);

        Match matchToDoW5_1 = new Match(mcon, jmgg);
        Match matchToDoW5_2 = new Match(snsh, ara);
        Match matchToDoW5_3 = new Match(znt, aoma);
        Match matchToDoW5_4 = new Match(ouat, myth);

        List<Match> matchList3 = List.of(matchToDoW5_1, matchToDoW5_2, matchToDoW5_3, matchToDoW5_4);

        Match matchToDoW6_1 = new Match(mcon, ara);
        Match matchToDoW6_2 = new Match(znt, ouat);
        Match matchToDoW6_3 = new Match(snsh, jmgg);
        Match matchToDoW6_4 = new Match(aoma, myth);

        Match matchToDoW7_1 = new Match(ouat, aoma);
        Match matchToDoW7_2 = new Match(ara, jmgg);
        Match matchToDoW7_3 = new Match(znt, myth);
        Match matchToDoW7_4 = new Match(mcon, snsh);


        List<Match> matchList4 = List.of(matchToDoW6_1, matchToDoW6_2, matchToDoW6_3, matchToDoW6_4, matchToDoW7_1, matchToDoW7_2, matchToDoW7_3, matchToDoW7_4);

        ArrayList<Match> matchList = new ArrayList<>(matchList1);
        matchList.addAll(matchList2);
        matchList.addAll(matchList3);
        matchList.addAll(matchList4);

        Championship championship = new Championship(teams, matchList);

        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(1, 2));
        championship.playNextMatch(new Score(2, 0));
        championship.playNextMatch(new Score(2, 0));

        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(1, 2));
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(1, 2));

        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(0, 2));
        championship.playNextMatch(new Score(2, 0));

        championship.playNextMatch(new Score(2, 0));
        championship.playNextMatch(new Score(1, 2));
        championship.playNextMatch(new Score(1, 2));
        championship.playNextMatch(new Score(2, 0));

        championship.playNextMatch(new Score(2, 0));
        championship.playNextMatch(new Score(2, 1));
        championship.playNextMatch(new Score(0, 2));
        championship.playNextMatch(new Score(0, 2));

        /*
        List<PlacedTeam> list = ranking.getPlacedTeams();
        for(PlacedTeam team: list) {
            System.out.println(team.getName() + " " + team.getSeries() + " " + team.getGames());
        }
         */

        championship.compute();
        System.out.println(championship.giveResults());
    }
}