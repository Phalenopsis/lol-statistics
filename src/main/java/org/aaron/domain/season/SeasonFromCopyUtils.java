package org.aaron.domain.season;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeasonFromCopyUtils {
    public String cleanString(String string) {
        return string.replaceAll("⁠⁠", "");
    }

    public List<List<String>> transformCopiedStringToList(String string) {
        List<List<String>> result = new ArrayList<>();
        String[] array = string.split("\n");
        for(String subString: array) {
            String[] tempArray = subString.trim().split("\t");
            if(tempArray.length == 4 || tempArray.length == 3) {
                result.add(Arrays.stream(tempArray).map(this::cleanString).toList());
            }
        }

        return result;
    }

    public List<List<List<String>>> transformCopiedScoreTable(String copiedScoreTable) {
        String[] array = copiedScoreTable.split("Week");
        List<List<List<String>>> result = new ArrayList<>();
        for(String week: array) {
            List<List<String>> transformedCopiedStringToList = transformCopiedStringToList(week);
            if(!transformedCopiedStringToList.isEmpty()) result.add(transformedCopiedStringToList);
        }
        return result;
    }
}
