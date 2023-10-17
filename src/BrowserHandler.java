import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Scanner;

public class BrowserHandler {
    double pageWait;
    public WebDriver driver;
    public BrowserHandler(double pageLoadTime) {
        this.pageWait = pageLoadTime;
    }
    public String getURL() {
        return driver.getCurrentUrl();
    }
    public URI stringToURI(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
            return null;
        }
    }
    public WebDriver getDriver() {
        return driver;
    }
    public void initBrowser(String url, String chromeDriverPath) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();
        try {
            driver.get(url);
            Thread.sleep((long) pageWait);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            //driver.quit();
        }
    }
    public String getHTML() {
        return driver.getPageSource();
    }
}
