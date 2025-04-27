package org.aaron;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.ranking.Ranking;
import org.aaron.domain.team.RankedTeam;
import org.aaron.domain.team.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RankingTest {

    @Test
    void playNextMatch_2TeamsScore2vs1_SeriesAndGamesAreUpdated() {
        Team mcon = new Team("mCon esports");
        Team snsh = new Team("Senshi eports");
        List<Team> teamList = List.of(mcon, snsh);
        Match match = new Match(mcon, snsh);
        ArrayList<Match> matches = new ArrayList<>(List.of(match));
        Ranking ranking = new Ranking(teamList, matches);

        ranking.playNextMatch(new Score(2, 1));

        Team mconAfterMatch = ranking.getTeam(mcon.getName());
        Team snshAfterMatch = ranking.getTeam(snsh.getName());

        Assertions.assertEquals("1 - 0", mconAfterMatch.getSeriesInString());
        Assertions.assertEquals("2 - 1", mconAfterMatch.getGamesInString());

        Assertions.assertEquals("0 - 1", snshAfterMatch.getSeriesInString());
        Assertions.assertEquals("1 - 2", snshAfterMatch.getGamesInString());
    }

    @Nested
    class testWith4TeamsAnd6Matches {
        String mconName = "mCon esports";
        String snshName = "Senshi eports";
        String ouatName = "Once upon a time";
        String arseName = "Arsenal";
        Ranking ranking;

        @BeforeEach
        void setUp() {
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
            ranking.playNextMatch(new Score(2, 0)); // mcon 2-0 arse
            ranking.playNextMatch(new Score(2, 0)); // snsh 2-0 ouat
            // mcon     3-0     6-2
            // snsh     2-1     5-2
            // ouat     1-2     3-5
            // arse     0-3     1-6
        }

        @Test
        void verifySeriesAndGameAfterMatches() {
            Team mconAfterMatch = ranking.getTeam(mconName);
            Team snshAfterMatch = ranking.getTeam(snshName);

            Assertions.assertEquals("3 - 0", mconAfterMatch.getSeriesInString());
            Assertions.assertEquals("6 - 2", mconAfterMatch.getGamesInString());

            Assertions.assertEquals("2 - 1", snshAfterMatch.getSeriesInString());
            Assertions.assertEquals("5 - 2", snshAfterMatch.getGamesInString());
        }

        @Test
        void verifyRankingIsOrdered() {
            List<Team> teams = ranking.getOrderedList();
            Assertions.assertEquals(mconName, teams.getFirst().getName());
            Assertions.assertEquals(snshName, teams.get(1).getName());
            Assertions.assertEquals(ouatName, teams.get(2).getName());
            Assertions.assertEquals(arseName, teams.get(3).getName());
        }
    }

    @Nested
    class testRankedTeamWith4TeamsAnd6Matches {
        String mconName = "mCon esports";
        String snshName = "Senshi eports";
        String ouatName = "Once upon a time";
        String arseName = "Arsenal";
        Ranking ranking;

        @BeforeEach
        void setUp() {
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

            ranking.playNextMatch(new Score(2, 0)); // mcon 2-0 snsh
            ranking.playNextMatch(new Score(2, 0)); // ouat 2-0 arse
            ranking.playNextMatch(new Score(2, 0)); // mcon 2-0 ouat
            ranking.playNextMatch(new Score(2, 0)); // snsh 2-0 arse
            ranking.playNextMatch(new Score(0, 2)); // mcon 0-2 arse
            ranking.playNextMatch(new Score(0, 2)); // snsh 0-2 ouat
            // mcon     2-1     4-2
            // snsh     1-2     2-4
            // ouat     2-1     4-2
            // arse     1-2     2-4
        }

        @Test
        void verifySeriesAndGameAfterMatches() {
            Team mconAfterMatch = ranking.getTeam(mconName);
            Team snshAfterMatch = ranking.getTeam(snshName);

            Assertions.assertEquals("2 - 1", mconAfterMatch.getSeriesInString());
            Assertions.assertEquals("4 - 2", mconAfterMatch.getGamesInString());

            Assertions.assertEquals("1 - 2", snshAfterMatch.getSeriesInString());
            Assertions.assertEquals("2 - 4", snshAfterMatch.getGamesInString());
        }

        @Test
        void verifyRankingIsOrdered() {
            List<RankedTeam> teams = ranking.getPlacedTeams();
            for(RankedTeam team: teams) {
                if(team.getName().equals(mconName)) {
                    Assertions.assertEquals(1, team.getRank());
                }
                if(team.getName().equals(ouatName)) {
                    Assertions.assertEquals(2, team.getRank());
                }
                if(team.getName().equals(snshName)) {
                    Assertions.assertEquals(3, team.getRank());
                }
                if(team.getName().equals(arseName)) {
                    Assertions.assertEquals(4, team.getRank());
                }
            }
        }
    }
    /*
    @Nested
    class testPlacedTeamWith5TeamsAndNUMBERMatches {
        String mconName = "mCon esports";
        String snshName = "Senshi eports";
        String ouatName = "Once upon a time";
        String arseName = "Arsenal";
        String bobName = "Before Our Bears";
        Ranking ranking;

        @BeforeEach
        void setUp() {
            Team mcon = new Team(mconName);
            Team snsh = new Team(snshName);
            Team ouat = new Team(ouatName);
            Team arse = new Team(arseName);
            Team bob = new Team(bobName);

            List<Team> teamList = List.of(ouat, arse, mcon, snsh);
            Match match1 = new Match(mcon, snsh);
            Match match2 = new Match(ouat, arse);
            Match match3 = new Match(mcon, ouat);
            Match match4 = new Match(snsh, arse);
            Match match5 = new Match(mcon, arse);
            Match match6 = new Match(snsh, ouat);
            Match match7 = new Match(bob, mcon);
            Match match8 = new Match(bob, snsh);
            Match match9 = new Match(bob, ouat);
            Match match10 = new Match(bob, arse);
            ArrayList<Match> matches = new ArrayList<>(List.of(match1, match2, match3, match4, match5, match6,
                    match7, match8, match9, match10));
            ranking = new Ranking(teamList, matches);

            ranking.playNextMatch(new Score(2, 0)); // mcon 2-0 snsh
            ranking.playNextMatch(new Score(2, 0)); // ouat 2-0 arse
            ranking.playNextMatch(new Score(2, 0)); // mcon 2-0 ouat
            ranking.playNextMatch(new Score(2, 0)); // snsh 2-0 arse
            ranking.playNextMatch(new Score(0, 2)); // mcon 0-2 arse
            ranking.playNextMatch(new Score(0, 2)); // snsh 0-2 ouat
            //ranking.playNextMatch(new Score()); // bob, mcon
            //ranking.playNextMatch(new Score()); // bob, snsh
            //ranking.playNextMatch(new Score()); // bob, ouat
            //ranking.playNextMatch(new Score()); // bob, arse
            // mcon     2-1     4-2
            // snsh     1-2     2-4
            // ouat     2-1     4-2
            // arse     1-2     2-4
        }

        @Test
        void verifySeriesAndGameAfterMatches() {
            Team mconAfterMatch = ranking.getTeam(mconName);
            Team snshAfterMatch = ranking.getTeam(snshName);

            Assertions.assertEquals("2 - 1", mconAfterMatch.getSeriesInString());
            Assertions.assertEquals("4 - 2", mconAfterMatch.getGamesInString());

            Assertions.assertEquals("1 - 2", snshAfterMatch.getSeriesInString());
            Assertions.assertEquals("2 - 4", snshAfterMatch.getGamesInString());
        }

        @Test
        void verifyRankingIsOrdered() {
            List<PlacedTeam> teams = ranking.getPlacedTeams();
            for(PlacedTeam team: teams) {
                if(team.getName().equals(mconName)) {
                    Assertions.assertEquals(1, team.getRank());
                }
                if(team.getName().equals(ouatName)) {
                    Assertions.assertEquals(1, team.getRank());
                }
                if(team.getName().equals(snshName)) {
                    Assertions.assertEquals(3, team.getRank());
                }
                if(team.getName().equals(arseName)) {
                    Assertions.assertEquals(3, team.getRank());
                }
            }
        }
    }
    */
}