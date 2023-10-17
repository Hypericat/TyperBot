import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeRacerParser extends HtmlParser {
    @Override
    public String parse(String html) {
        System.out.println("Parsing HTML");
        String firstword = getStart(html);
        System.out.println("Found first word : " + firstword);

        String spans = getIncompletes(html, firstword);
        System.out.println("Compiled full list of chars : " + spans);
        return spans;
    }
    public String getStart(String html) {
        String regex = "<span\\s+unselectable=\"on\"\\s+class=\"(.*?)\">(.*?)</span>";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(html);

        // Find all matches
        StringBuilder builder = new StringBuilder();
        int rep = 0;
        while (matcher.find()) {
            if (rep > 1) break;
            // Group 1 contains the content between class attribute
            String classContent = matcher.group(1);

            // Group 2 contains the content between span tags
            String content = matcher.group(2);

            System.out.println("Content between class attribute: " + classContent);
            System.out.println("Content between span tags: " + content);
            builder.append(content);
            rep++;
        }
        return builder.toString();
    }
    @Override
    public String getIncompletes(String html, String firstChar) {
        String startSubstring = "<span unselectable=\"on\">";
        String endSubstring = "</span>";

        int startIndex = html.indexOf(startSubstring);
        int endIndex = html.indexOf(endSubstring, startIndex);

        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            // Extract characters between the substrings
            String result = firstChar + html.substring(startIndex + startSubstring.length(), endIndex);
            return result;
        }
        return null;
    }
}
