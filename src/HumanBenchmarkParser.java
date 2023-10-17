import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanBenchmarkParser extends HtmlParser {
    @Override
    public String parse(String html) {
        System.out.println("Parsing HTML");
        String firstChar = String.valueOf(html.charAt(html.indexOf("<span class=\"incomplete current\">") + 33));
        System.out.println("Found first char : " + firstChar);
        String spans = getIncompletes(html, firstChar);
        System.out.println("Compiled full list of chars : " + spans);
        return spans;
    }
    @Override
    public String getIncompletes(String html, String firstChar) {
        List<String> spans = new ArrayList<>();
        spans.add(firstChar);
        Pattern pattern = Pattern.compile("<span class=\"incomplete\">(.)</span>");

        // Create a matcher object
        Matcher matcher = pattern.matcher(html);

        // Find all matches
        while (matcher.find()) {
            String content = matcher.group(1);
            spans.add(content);
        }
        StringBuilder builder = new StringBuilder();
        for (String str : spans) {
            builder.append(str);
        }
        return builder.toString();
    }
}
