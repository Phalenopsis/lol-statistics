package org.aaron.domain.season;

import org.aaron.domain.ranking.Championship;

public class Spring2026 {
    private static String copiedWeeks = """
            Week 1
            			
            Tue 2026-03-31
            BAN⁠⁠	2	0	⁠⁠DYN
            MCON⁠⁠	2	1	⁠⁠ZNT
            Thu 2026-04-02
            SNSH⁠⁠	0	2	⁠⁠FEC
            MYTH⁠⁠	0	2	⁠⁠OUAT
            Patch: 26.07
            [hide]
            Week 2
            			
            Tue 2026-04-07
            ZNT⁠⁠	0	2	⁠⁠BAN
            OUAT⁠⁠	0	2	⁠⁠FEC
            Thu 2026-04-09
            MCON⁠⁠	1	2	⁠⁠DYN
            MYTH⁠⁠	0	2	⁠⁠SNSH
            Patch: 26.07
            [hide]
            Week 3
            			
            Tue 2026-04-14
            MYTH⁠⁠	1	2	⁠⁠DYN
            SNSH⁠⁠	2	0	⁠⁠MCON
            Thu 2026-04-16
            FEC⁠⁠	2	0	⁠⁠ZNT
            OUAT⁠⁠	0	2	⁠⁠BAN
            Patch: 26.08
            [hide]
            Week 4
            			
            Tue 2026-04-21
            SNSH⁠⁠	0	2	⁠⁠BAN
            MYTH⁠⁠	1	2	⁠⁠ZNT
            Thu 2026-04-23
            OUAT⁠⁠	2	1	⁠⁠MCON
            FEC⁠⁠	2	0	⁠⁠DYN
            Patch: TBD
            [hide]
            Week 5
            			
            Tue 2026-04-28
            FEC⁠⁠	19:00	⁠⁠BAN
            OUAT⁠⁠	20:00	⁠⁠DYN
            Thu 2026-04-30
            SNSH⁠⁠	19:00	⁠⁠ZNT
            MYTH⁠⁠	20:00	⁠⁠MCON
            Patch: TBD
            [hide]
            Week 6
            			
            Tue 2026-05-05
            ZNT⁠⁠	19:00	⁠⁠DYN
            MCON⁠⁠	20:00	⁠⁠BAN
            Thu 2026-05-07
            OUAT⁠⁠	19:00	⁠⁠SNSH
            MYTH⁠⁠	20:00	⁠⁠FEC
            Patch: TBD
            [hide]
            Week 7
            			
            Tue 2026-05-12
            FEC⁠⁠	19:00	⁠⁠MCON
            SNSH⁠⁠	20:00	⁠⁠DYN
            Thu 2026-05-14
            MYTH⁠⁠	19:00	⁠⁠BAN
            OUAT⁠⁠	20:00	⁠⁠ZNT""";

    public static Championship createChampionship() {
        SeasonInitializer initializer = new SeasonInitializer();
        return initializer.initialize(copiedWeeks);
    }
}
