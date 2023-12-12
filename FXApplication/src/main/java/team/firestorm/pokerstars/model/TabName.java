package team.firestorm.pokerstars.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TabName {
    private final CsvParser csvParser;

    public TabName(CsvParser csvParser) {
        this.csvParser = csvParser;
    }

    public String getTabName() {
        String firstString = csvParser.getFirstString();
        String regex = "Аудит игровой истории для\\s+([^ ]+)|Playing History Audit\\s+'([^']+)'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(firstString);
        String tabName = "";
        while (matcher.find()) {
            String tabNameVer1 = matcher.group(1);
            String tabNameVer2 = matcher.group(2);
            if (tabNameVer1 != null) {
                tabName = tabNameVer1;
            } else if (tabNameVer2 != null) {
                tabName = tabNameVer2;
            }
        }
        return tabName;
    }
}
