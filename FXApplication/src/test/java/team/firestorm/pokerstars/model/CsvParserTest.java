package team.firestorm.pokerstars.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@Disabled
public class CsvParserTest {
    private final String str1 = """
            "2023/12/03 6:42 AM,Tournament Registration,3681453647$5.00 NLHE Spin & Go Flash,"" NL Hold'em Sit&Go Buy-In: 4.60/0.40"",USD,""-5.00"",""0.00"",""0.00"",""0.00"",""1,569.39"",""192.56"",""0.00"",""0.00""\" 
            """;
    private final String str2 = """
            2022/12/21 11:42 PM,Tournament Registration,3519171310$25.00 NLHE Spin & Go," NL Hold'em Sit&Go Buy-In: 23.50/1.50",,USD,"-25.00","0.00","0.00","0.00","1,900.94","3.00","0.00","0.00"
            """;

    private final String str3 = """
            "02.08.2023 1:06 PM,Регистрация в турнире,3625823603$5.00 NLHE Spin & Go,"" NL Hold'em Sit&Go Buy-In: 4,60/0,40"",USD,""-5,00"",""0,00"",""0,00"",""0,00"",""374,25"",""300,00"",""0,00"",""0,00""\"
            """;

    private final String str4 = """
            02.08.2023 1:04 PM,Регистрация в турнире,3625822961$5.00 NLHE Spin & Go," NL Hold'em Sit&Go Buy-In: 4,60/0,40",USD,"-5,00","0,00","0,00","0,00","379,25","300,00","0,00","0,00"
            """;

    @Test
    void parseString1() {
        String processedString = processString(str1);

        String[] stringsArray = splitStringToArray(processedString);
        Arrays.stream(stringsArray).forEach(s -> System.out.println(s));
    }

    @Test
    void parseString2() {
        String processedString = processString(str2);

        String[] stringsArray = splitStringToArray(processedString);
        Arrays.stream(stringsArray).forEach(s -> System.out.println(s));
    }

    @Test
    void parseString3() {
        String processedString = processString(str3);

        String[] stringsArray = splitStringToArray(processedString);
        Arrays.stream(stringsArray).forEach(s -> System.out.println(s));
    }

    @Test
    void parseString4() {
        String processedString = processString(str4);

        String[] stringsArray = splitStringToArray(processedString);
        Arrays.stream(stringsArray).forEach(s -> System.out.println(s));
    }

    private String processString(String str) {
        str = replaceTripleQuotesToDouble(str);
        str = replaceDoubleQuoteToSingle(str);
        str = trim(str);
        str = deleteSingleQuote(str);
        return str;
    }

    private String replaceTripleQuotesToDouble(String str) {
        return replace(str, "\"\"\"", "\"\"");
    }

    private String replaceDoubleQuoteToSingle(String str) {
        return replace(str, "\"\"", "\"");
    }

    private String trim(String str) {
        return replace(str, "\" ", "\"");
    }

    private String deleteSingleQuote(String str) {
        return replace(str, "\"", "");
    }

    private String replace(String str, String input, String output) {
        StringBuilder stringBuilder = new StringBuilder(str);
        int index = stringBuilder.indexOf(input);
        while (index != -1) {
            stringBuilder.replace(index, index + input.length(), output);
            index = stringBuilder.indexOf(input, index + output.length());
        }
        return stringBuilder.toString();
    }

    private String[] splitStringToArray(String str) {
        return str.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }
}
