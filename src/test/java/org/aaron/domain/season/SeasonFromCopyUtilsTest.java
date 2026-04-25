package org.aaron.domain.season;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SeasonFromCopyUtilsTest {
    String unPlayedMatch = "MCON⁠⁠	2	1	⁠⁠SNSH";

    String unPlayedWeek = """
            Week 6
            			
            Tue 2025-08-19
            MCON⁠⁠	19:00	⁠⁠JMGG
            ZNT⁠⁠	20:00	⁠⁠OUAT
            Thu 2025-08-21
            AOMA⁠⁠	19:00	⁠⁠MYTH
            SNSH⁠⁠	20:00	⁠⁠ARA""";

    String playedWeek = """
            Week 3
            			
            Tue 2025-07-29
            ARA⁠⁠	1	2	⁠⁠JMGG
            OUAT⁠⁠	0	2	⁠⁠MYTH
            Thu 2025-07-31
            MCON⁠⁠	2	1	⁠⁠SNSH
            ZNT⁠⁠	2	0	⁠⁠AOMA""";

    String weeks = """
                    Week 1
                    			
                    Tue 2025-07-15
                    AOMA⁠⁠	2	1	⁠⁠ARA
                    OUAT⁠⁠	0	2	⁠⁠MCON
                    Thu 2025-07-17
                    MYTH⁠⁠	1	2	⁠⁠SNSH
                    ZNT⁠⁠	2	0	⁠⁠JMGG
                    Patch: 25.14
                    [hide]
                    Week 2
                    			
                    Tue 2025-07-22
                    ZNT⁠⁠	2	0	⁠⁠MCON
                    AOMA⁠⁠	0	2	⁠⁠SNSH
                    Thu 2025-07-24
                    OUAT⁠⁠	2	1	⁠⁠JMGG
                    MYTH⁠⁠	2	0	⁠⁠ARA
                    Patch: 25.14
                    [hide]
                    Week 3
                    			
                    Tue 2025-07-29
                    ARA⁠⁠	1	2	⁠⁠JMGG
                    OUAT⁠⁠	0	2	⁠⁠MYTH
                    Thu 2025-07-31
                    MCON⁠⁠	2	1	⁠⁠SNSH
                    ZNT⁠⁠	2	0	⁠⁠AOMA
                    Patch: 25.15
                    [hide]
                    Week 4
                    			
                    Tue 2025-08-05
                    ZNT⁠⁠	2	0	⁠⁠MYTH
                    MCON⁠⁠	2	0	⁠⁠ARA
                    Thu 2025-08-07
                    OUAT⁠⁠	2	0	⁠⁠AOMA
                    SNSH⁠⁠	2	0	⁠⁠JMGG
                    Patch: 25.15
                    [hide]
                    Week 5
                    			
                    Tue 2025-08-12
                    ZNT⁠⁠	1	2	⁠⁠SNSH
                    AOMA⁠⁠	0	2	⁠⁠JMGG
                    Thu 2025-08-14
                    MYTH⁠⁠	0	2	⁠⁠MCON
                    OUAT⁠⁠	2	0	⁠⁠ARA
                    Patch: TBD
                    [hide]
                    Week 6
                    			
                    Tue 2025-08-19
                    MCON⁠⁠	19:00	⁠⁠JMGG
                    ZNT⁠⁠	20:00	⁠⁠OUAT
                    Thu 2025-08-21
                    AOMA⁠⁠	19:00	⁠⁠MYTH
                    SNSH⁠⁠	20:00	⁠⁠ARA
                    Patch: TBD
                    [hide]
                    Week 7
                    			
                    Tue 2025-08-26
                    OUAT⁠⁠	19:00	⁠⁠SNSH
                    MYTH⁠⁠	20:00	⁠⁠JMGG
                    Thu 2025-08-28
                    ZNT⁠⁠	19:00	⁠⁠ARA
                    AOMA⁠⁠	20:00	⁠⁠MCON""";

    @Test
    void testCleanString_OneLineString_ShouldDeleteBadCharacter() {
        SeasonFromCopyUtils season = new SeasonFromCopyUtils();
        String result = season.cleanString(unPlayedMatch);
        String expected = "MCON	2	1	SNSH";
        assertThat(result).isEqualTo(expected);
    }


    @Test
    void transformCopiedStringToList_unplayedWeek_ShouldTransformToList() {
        SeasonFromCopyUtils seasonTest = new SeasonFromCopyUtils();
        List<List<String>> expected = List.of(
                List.of("MCON", "19:00", "JMGG"),
                List.of("ZNT", "20:00", "OUAT"),
                List.of("AOMA", "19:00", "MYTH"),
                List.of("SNSH", "20:00", "ARA")
        );
        List<List<String>> result = seasonTest.transformCopiedStringToList(unPlayedWeek);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void transformCopiedStringToList_playedWeek_ShouldTransformToList() {
        SeasonFromCopyUtils seasonTest = new SeasonFromCopyUtils();
        List<List<String>> expected = List.of(
                List.of("ARA", "1", "2", "JMGG"),
                List.of("OUAT", "0", "2", "MYTH"),
                List.of("MCON", "2", "1", "SNSH"),
                List.of("ZNT", "2", "0", "AOMA")
        );
        List<List<String>> result = seasonTest.transformCopiedStringToList(playedWeek);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testTransformCopiedScoreTable() {
        SeasonFromCopyUtils seasonTest = new SeasonFromCopyUtils();
        List<List<String>> expectedWeek3 = List.of(
                List.of("ARA", "1", "2", "JMGG"),
                List.of("OUAT", "0", "2", "MYTH"),
                List.of("MCON", "2", "1", "SNSH"),
                List.of("ZNT", "2", "0", "AOMA")
        );
        List<List<String>> expectedWeek6 = List.of(
                List.of("MCON", "19:00", "JMGG"),
                List.of("ZNT", "20:00", "OUAT"),
                List.of("AOMA", "19:00", "MYTH"),
                List.of("SNSH", "20:00", "ARA")
        );
        List<List<List<String>>> result = seasonTest.transformCopiedScoreTable(weeks);
        assertThat(result.get(2)).isEqualTo(expectedWeek3);
        assertThat(result.get(5)).isEqualTo(expectedWeek6);
    }
}