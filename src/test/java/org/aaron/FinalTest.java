package org.aaron;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.ranking.Ranking;
import org.aaron.domain.team.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FinalTest {
    String mconName = "mCon esports";
    String snshName = "Senshi eports";
    Ranking ranking;

    @Test
    void testFinal() {
        Team mcon = new Team(mconName);
        Team snsh = new Team(snshName);
        List<Team> teamList = List.of(mcon, snsh);
        Match match1 = new Match(mcon, snsh);
        ArrayList<Match> matchList = new ArrayList<>(List.of(match1));

        ranking = new Ranking(teamList, matchList);

        ranking.compute();
        String result = ranking.giveResults();

        String expected = "| 1 | mCon esports 50.0 | Senshi eports 50.0 |\n" +
                "| 2 | mCon esports 50.0 | Senshi eports 50.0 |\n";

        Assertions.assertEquals(expected, result);
    }

    @Nested
    class FinalTestWith4Teams {
        String ouatName = "Once upon a time";
        String arseName = "Arsenal";

        @Test
        void testFinalWith4TeamsAnd4MatchesPlayed() {
            Team mcon = new Team(mconName);
            Team snsh = new Team(snshName);
            Team ouat = new Team(ouatName);
            Team arse = new Team(arseName);

            List<Team> teamList = List.of(ouat, arse, mcon, snsh);
            Match match1 = new Match(mcon, snsh);
            Match match2 = new Match(ouat, arse);
            Match match3 = new Match(mcon, ouat);
            Match match4 = new Match(snsh, arse);
            Match match5 = new Match(mcon, arse);
            Match match6 = new Match(snsh, ouat);
            ArrayList<Match> matches = new ArrayList<>(List.of(match1, match2, match3, match4, match5, match6));
            ranking = new Ranking(teamList, matches);

            ranking.playNextMatch(new Score(2, 1)); // mcon 2-1 snsh
            ranking.playNextMatch(new Score(2, 1)); // ouat 2-1 arse
            ranking.playNextMatch(new Score(2, 1)); // mcon 2-1 ouat
            ranking.playNextMatch(new Score(2, 0)); // snsh 2-0 arse
            // mcon     2-0     4-2
            // snsh     1-1     3-2
            // ouat     1-1     3-3
            // arse     0-2     1-4

            ranking.compute();
            String result = ranking.giveResults();

            String expected = "| 1 | Once upon a time 18.75 | mCon esports 56.25 | Senshi eports 25.0 |\n" +
                            "| 2 | Once upon a time 31.25 | mCon esports 43.75 | Senshi eports 25.0 |\n" +
                            "| 3 | Once upon a time 43.75 | Arsenal 6.25 | Senshi eports 50.0 |\n" +
                            "| 4 | Once upon a time 6.25 | Arsenal 93.75 |\n";

            Assertions.assertEquals(expected, result);
        }
    }


}
