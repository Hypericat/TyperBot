import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String site = "https://www.nitrotype.com/race";
    static String site2 = "https://humanbenchmark.com/tests/typing";
    static String site3 = "https://play.typeracer.com/";
    static String chromeDriverPath = "C:\\Users\\Hypericats\\IdeaProjects\\TypingBot\\src\\driver\\chromedriver.exe";
    static HtmlParser parser;
    static Typer typer;
    static BrowserHandler browserHandler = new BrowserHandler(1000d);
    public static void main(String[] args) {
        String url = site2;
        System.out.println("Init Classes");
        typer = new Typer();
        browserHandler.initBrowser(url, chromeDriverPath);
        System.out.println("Init driver");
        while (true) {
            System.out.println("Press enter to type");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            try {
                reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (browserHandler.getURL().startsWith("https://play.typeracer.com")) {
                System.out.println("Parsing for Typeracer");
                parser = new TypeRacerParser();
            }
            if (browserHandler.getURL().startsWith("https://humanbenchmark.com/tests/typing")) {
                System.out.println("Parsing for Humanbenchmark");
                parser = new HumanBenchmarkParser();
            }
            String htmlCode = browserHandler.getHTML();
            String text = parser.parse(htmlCode);
            System.out.println("Full sentence : " + text);
            typer.type(text, 3024, browserHandler);
        }
    }
}