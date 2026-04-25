package org.aaron.domain.season;

import java.util.HashMap;
import java.util.Map;

public class Teams {
    public static String znt = "ZennIt";
    public static String mcon = "mCon esports";
    public static String snsh = "Senshi eports";
    public static String aoma = "Bandits";
    public static String myth = "Myth Esport";
    public static String ouat = "Once Upon A Team";
    public static String jmgg = "Frites Esports Club";
    public static String ara = "Dynasty";

    private static final Map<String, String> teamMap = new HashMap<>();

    public static Map<String, String> getTeamMap() {
        if(teamMap.isEmpty()) initializeMap();
        return teamMap;
    }

    private static void initializeMap() {
        teamMap.put("ZNT", "ZennIt");
        teamMap.put("MCON", "mCon esports");
        teamMap.put("SNSH", "Senshi eports");
        teamMap.put("BAN", "Bandits");
        teamMap.put("MYTH", "Myth Esport");
        teamMap.put("OUAT", "Once Upon A Team");
        teamMap.put("FEC", "Frites Esports Club");
        teamMap.put("DYN", "Dynasty");
    }
}
