package org.aaron;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.ranking.Championship;
import org.aaron.domain.team.RankedTeam;
import org.aaron.domain.team.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ChampionshipTest {

    @Test
    void playNextMatch_2TeamsScore2vs1_SeriesAndGamesAreUpdated() {
        String mconName = "mCon esports";
        String snshName = "Senshi eports";
        Team mcon = new Team(mconName);
        Team snsh = new Team(snshName);

        List<Team> teamList = List.of(mcon, snsh);
        Match match = new Match(mconName, snshName);
        ArrayList<Match> matches = new ArrayList<>(List.of(match));
        Championship championship = new Championship(teamList, matches);

        championship.playNextMatch(new Score(2, 1));

        Team mconAfterMatch = championship.getTeam(mcon.getName());
        Team snshAfterMatch = championship.getTeam(snsh.getName());

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
        Championship championship;

        @BeforeEach
        void setUp() {
            Team mcon = new Team(mconName);
            Team snsh = new Team(snshName);
            Team ouat = new Team(ouatName);
            Team arse = new Team(arseName);

            List<Team> teamList = List.of(ouat, arse, mcon, snsh);
            Match match1 = new Match(mconName, snshName);
            Match match2 = new Match(ouatName, arseName);
            Match match3 = new Match(mconName, ouatName);
            Match match4 = new Match(snshName, arseName);
            Match match5 = new Match(mconName, arseName);
            Match match6 = new Match(snshName, ouatName);
            ArrayList<Match> matches = new ArrayList<>(List.of(match1, match2, match3, match4, match5, match6));
            championship = new Championship(teamList, matches);

            championship.playNextMatch(new Score(2, 1)); // mcon 2-1 snsh
            championship.playNextMatch(new Score(2, 1)); // ouat 2-1 arse
            championship.playNextMatch(new Score(2, 1)); // mcon 2-1 ouat
            championship.playNextMatch(new Score(2, 0)); // snsh 2-0 arse
            championship.playNextMatch(new Score(2, 0)); // mcon 2-0 arse
            championship.playNextMatch(new Score(2, 0)); // snsh 2-0 ouat
            // mcon     3-0     6-2
            // snsh     2-1     5-2
            // ouat     1-2     3-5
            // arse     0-3     1-6
        }

        @Test
        void verifySeriesAndGameAfterMatches() {
            Team mconAfterMatch = championship.getTeam(mconName);
            Team snshAfterMatch = championship.getTeam(snshName);

            Assertions.assertEquals("3 - 0", mconAfterMatch.getSeriesInString());
            Assertions.assertEquals("6 - 2", mconAfterMatch.getGamesInString());

            Assertions.assertEquals("2 - 1", snshAfterMatch.getSeriesInString());
            Assertions.assertEquals("5 - 2", snshAfterMatch.getGamesInString());
        }

        @Test
        void verifyRankingIsOrdered() {
            List<Team> teams = championship.getOrderedList();
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
        Championship championship;

        @BeforeEach
        void setUp() {
            Team mcon = new Team(mconName);
            Team snsh = new Team(snshName);
            Team ouat = new Team(ouatName);
            Team arse = new Team(arseName);

            List<Team> teamList = List.of(ouat, arse, mcon, snsh);
            Match match1 = new Match(mconName, snshName);
            Match match2 = new Match(ouatName, arseName);
            Match match3 = new Match(mconName, ouatName);
            Match match4 = new Match(snshName, arseName);
            Match match5 = new Match(mconName, arseName);
            Match match6 = new Match(snshName, ouatName);
            ArrayList<Match> matches = new ArrayList<>(List.of(match1, match2, match3, match4, match5, match6));
            championship = new Championship(teamList, matches);

            championship.playNextMatch(new Score(2, 0)); // mcon 2-0 snsh
            championship.playNextMatch(new Score(2, 0)); // ouat 2-0 arse
            championship.playNextMatch(new Score(2, 0)); // mcon 2-0 ouat
            championship.playNextMatch(new Score(2, 0)); // snsh 2-0 arse
            championship.playNextMatch(new Score(0, 2)); // mcon 0-2 arse
            championship.playNextMatch(new Score(0, 2)); // snsh 0-2 ouat
            // mcon     2-1     4-2
            // snsh     1-2     2-4
            // ouat     2-1     4-2
            // arse     1-2     2-4
        }

        @Test
        void verifySeriesAndGameAfterMatches() {
            Team mconAfterMatch = championship.getTeam(mconName);
            Team snshAfterMatch = championship.getTeam(snshName);

            Assertions.assertEquals("2 - 1", mconAfterMatch.getSeriesInString());
            Assertions.assertEquals("4 - 2", mconAfterMatch.getGamesInString());

            Assertions.assertEquals("1 - 2", snshAfterMatch.getSeriesInString());
            Assertions.assertEquals("2 - 4", snshAfterMatch.getGamesInString());
        }

        @Test
        void verifyRankingIsOrdered() {
            List<RankedTeam> teams = championship.getPlacedTeams();
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
}