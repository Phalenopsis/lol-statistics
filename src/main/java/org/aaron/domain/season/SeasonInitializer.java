package org.aaron.domain.season;

import org.aaron.domain.match.Match;
import org.aaron.domain.match.Score;
import org.aaron.domain.ranking.Championship;
import org.aaron.domain.team.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.aaron.domain.season.Teams.*;
import static org.aaron.domain.season.Teams.ara;

public class SeasonInitializer {
    Team zntTeam = new Team(znt);
    Team mconTeam = new Team(mcon);
    Team snshTeam = new Team(snsh);
    Team aomaTeam = new Team(aoma);
    Team mythTeam = new Team(myth);
    Team ouatTeam = new Team(ouat);
    Team jmggTeam = new Team(jmgg);
    Team araTeam = new Team(ara);

    List<Team> teams = List.of(zntTeam, mconTeam, snshTeam, aomaTeam, mythTeam, ouatTeam, jmggTeam, araTeam);

    public Championship initialize(String matchTable) {
        SeasonFromCopyUtils utils = new SeasonFromCopyUtils();
        List<List<List<String>>> copiedWeekMatchList = utils.transformCopiedScoreTable(matchTable);
        List<List<String>> copiedMatchList = flatten(copiedWeekMatchList);

        ArrayList<Match> matchList = new ArrayList<>();
        List<Score> scoreList = new ArrayList<>();

        for (List<String> copiedStringMatch: copiedMatchList) {
            CopiedMatch match = new CopiedMatch(copiedStringMatch);
            matchList.add(match.getMatch());
            if(match.isPlayed()) scoreList.add(match.getScore());
        }

        Championship championship = new Championship(teams, matchList);
        for(Score score: scoreList) {
            championship.playNextMatch(score);
        }
        return championship;
    }

    public static<T> List<T> flatten(List<List<T>> listOfLists) {
        return listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
