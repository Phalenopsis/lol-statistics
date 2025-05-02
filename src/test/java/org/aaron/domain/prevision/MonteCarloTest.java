package org.aaron.domain.prevision;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.ranking.Championship;
import org.aaron.domain.ranking.StatisticsForGivenRank;
import org.aaron.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class MonteCarloTest {
    String znt = "ZennIt";
    String mcon = "mCon esports";
    String snsh = "Senshi eports";
    String aoma = "A One Man Army";
    String myth = "Myth Esport";
    String ouat = "Once Upon A Team";
    String jmgg = "Jörmungang";
    String ara = "Aurora";

    @Test
    void testCompute_With2Teams() {
        Team mconTeam = new Team(mcon);
        Team snshTeam = new Team(snsh);

        List<Team> teamList = List.of(mconTeam, snshTeam);
        Match match = new Match(mcon, snsh);
        ArrayList<Match> matches = new ArrayList<>(List.of(match));
        Championship championship = new Championship(teamList, matches);

        championship.computeMonteCarlo();

        Map<Integer, StatisticsForGivenRank> result = championship.getStatisticsForRanks();

        StatisticsForGivenRank rank1 = result.get(1);
        double chancesForMconToFinish1 = rank1.getChances(mcon);
        double chancesForSnshToFinish1 = rank1.getChances(snsh);
        StatisticsForGivenRank rank2 = result.get(2);
        double chancesForMconToFinish2 = rank2.getChances(mcon);
        double chancesForSnshToFinish2 = rank2.getChances(snsh);

        assertThat(chancesForMconToFinish1).isCloseTo(50, within(1d));
        assertThat(chancesForMconToFinish2).isCloseTo(50, within(1d));
        
        assertThat(chancesForSnshToFinish1).isCloseTo(50, within(1d));
        assertThat(chancesForSnshToFinish2).isCloseTo(50, within(1d));
    }

    @Nested
    class TestWith8Teams {
        Championship championship;

        @BeforeEach
        void setUp() {
            Team zntTeam = new Team(znt);
            Team mconTeam = new Team(mcon);
            Team snshTeam = new Team(snsh);
            Team aomaTeam = new Team(aoma);
            Team mythTeam = new Team(myth);
            Team ouatTeam = new Team(ouat);
            Team jmggTeam = new Team(jmgg);
            Team araTeam = new Team(ara);

            List<Team> teams = List.of(zntTeam, mconTeam, snshTeam, aomaTeam, mythTeam, ouatTeam, jmggTeam, araTeam);

            ArrayList<Match> matchList = new ArrayList<>();
            matchList.addAll(prepareWeek1());
            matchList.addAll(prepareWeek2());
            matchList.addAll(prepareWeek3());
            matchList.addAll(prepareWeek4());
            matchList.addAll(prepareWeek5());
            matchList.addAll(prepareWeek6());
            matchList.addAll(prepareWeek7());

            championship = new Championship(teams, matchList);
        }

        @Test
        void testCompute_With8Teams_NoMatchPlayed() {
            championship.computeMonteCarlo();

            Map<Integer, StatisticsForGivenRank> result = championship.getStatisticsForRanks();
            StatisticsForGivenRank rank1 = result.get(1);
            double chancesForMconToFinish1 = rank1.getChances(znt);
            double chancesForSnshToFinish1 = rank1.getChances(znt);
            StatisticsForGivenRank rank2 = result.get(2);
            double chancesForMconToFinish2 = rank2.getChances(mcon);
            double chancesForSnshToFinish2 = rank2.getChances(snsh);

            double chances = 12.5;

            assertThat(chancesForMconToFinish1).isCloseTo(chances, within(1d));
            assertThat(chancesForSnshToFinish1).isCloseTo(chances, within(1d));
            assertThat(chancesForMconToFinish2).isCloseTo(chances, within(1d));
            assertThat(chancesForSnshToFinish2).isCloseTo(chances, within(1d));

        }

        @Test
        void test() {
            playWeek1(championship);
            playWeek2(championship);
            playWeek3(championship);
            playWeek4(championship);
            playWeek5(championship);
            championship.computeMonteCarlo();

            Map<Integer, StatisticsForGivenRank> result = championship.getStatisticsForRanks();
            StatisticsForGivenRank rank1 = result.get(1);
            double chancesForZntToFinish1 = rank1.getChances(znt);
            double chancesForMythToFinish1 = rank1.getChances(myth);

            StatisticsForGivenRank rank5 = result.get(5);
            double chancesForOuatToFinish5 = rank5.getChances(ouat);
            double chancesForJmggToFinish5 = rank5.getChances(jmgg);

            double chancesZnt = 34.74;
            double chancesMyth = 10.73;
            double chancesOuat = 18.31;
            double chancesJmgg = 1.56;

            assertThat(chancesForZntToFinish1).isCloseTo(chancesZnt, within(1d));
            assertThat(chancesForMythToFinish1).isCloseTo(chancesMyth, within(1d));
            assertThat(chancesForOuatToFinish5).isCloseTo(chancesOuat, within(1d));
            assertThat(chancesForJmggToFinish5).isCloseTo(chancesJmgg, within(1d));
        }
        
        private List<Match> prepareWeek1() {
            Match matchToDoW1_1 = new Match(myth, snsh);
            Match matchToDoW1_2 = new Match(ouat, mcon);
            Match matchToDoW1_3 = new Match(aoma, jmgg);
            Match matchToDoW1_4 = new Match(znt, ara);

            return List.of(matchToDoW1_1, matchToDoW1_2, matchToDoW1_3, matchToDoW1_4);
        }
        
        private List<Match> prepareWeek2() {
            Match matchToDoW2_1 = new Match(znt, mcon);
            Match matchToDoW2_2 = new Match(aoma,snsh);
            Match matchToDoW2_3 = new Match(ouat, ara);
            Match matchToDoW2_4 = new Match(myth, jmgg);

            return List.of(matchToDoW2_1, matchToDoW2_2, matchToDoW2_3, matchToDoW2_4);
        }
        
        private List<Match> prepareWeek3() {
            Match matchToDoW3_1 = new Match(myth, ara);
            Match matchToDoW3_2 = new Match(ouat, jmgg);
            Match matchToDoW3_3 = new Match(aoma, mcon);
            Match matchToDoW3_4 = new Match(znt, snsh);

            return List.of(matchToDoW3_1, matchToDoW3_2, matchToDoW3_3, matchToDoW3_4);
        }

        private List<Match> prepareWeek4() {
            Match matchToDoW4_1 = new Match(znt, jmgg);
            Match matchToDoW4_2 = new Match(myth, mcon);
            Match matchToDoW4_3 = new Match(ouat, snsh);
            Match matchToDoW4_4 = new Match(aoma, ara);

            return List.of(matchToDoW4_1, matchToDoW4_2, matchToDoW4_3, matchToDoW4_4);
        }

        private List<Match> prepareWeek5() {
            Match matchToDoW5_1 = new Match(mcon, jmgg);
            Match matchToDoW5_2 = new Match(snsh, ara);
            Match matchToDoW5_3 = new Match(znt, aoma);
            Match matchToDoW5_4 = new Match(ouat, myth);

            return List.of(matchToDoW5_1, matchToDoW5_2, matchToDoW5_3, matchToDoW5_4);
        }

        private List<Match> prepareWeek6() {
            Match matchToDoW6_1 = new Match(mcon, ara);
            Match matchToDoW6_2 = new Match(znt, ouat);
            Match matchToDoW6_3 = new Match(snsh, jmgg);
            Match matchToDoW6_4 = new Match(aoma, myth);

            return List.of(matchToDoW6_1, matchToDoW6_2, matchToDoW6_3, matchToDoW6_4);
        }

        private List<Match> prepareWeek7() {
            Match matchToDoW7_1 = new Match(ouat, aoma);
            Match matchToDoW7_2 = new Match(ara, jmgg);
            Match matchToDoW7_3 = new Match(znt, myth);
            Match matchToDoW7_4 = new Match(mcon, snsh);

            return List.of(matchToDoW7_1, matchToDoW7_2, matchToDoW7_3, matchToDoW7_4);
        }

        private void playWeek5(Championship championship) {
            championship.playNextMatch(new Score(2, 0));
            championship.playNextMatch(new Score(2, 1));
            championship.playNextMatch(new Score(0, 2));
            championship.playNextMatch(new Score(0, 2));
        }

        private void playWeek4(Championship championship) {
            championship.playNextMatch(new Score(2, 0));
            championship.playNextMatch(new Score(1, 2));
            championship.playNextMatch(new Score(1, 2));
            championship.playNextMatch(new Score(2, 0));
        }

        private void playWeek3(Championship championship) {
            championship.playNextMatch(new Score(2, 1));
            championship.playNextMatch(new Score(2, 1));
            championship.playNextMatch(new Score(0, 2));
            championship.playNextMatch(new Score(2, 0));
        }

        private void playWeek2(Championship championship) {
            championship.playNextMatch(new Score(2, 1));
            championship.playNextMatch(new Score(1, 2));
            championship.playNextMatch(new Score(2, 1));
            championship.playNextMatch(new Score(1, 2));
        }

        private void playWeek1(Championship championship) {
            championship.playNextMatch(new Score(2, 1));
            championship.playNextMatch(new Score(1, 2));
            championship.playNextMatch(new Score(2, 0));
            championship.playNextMatch(new Score(2, 0));
        }
    }
}