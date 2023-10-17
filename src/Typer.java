import org.openqa.selenium.interactions.Actions;

public class Typer {
    public void type(String str, double wpm, BrowserHandler handler) {
        Actions actions = new Actions(handler.getDriver());
        if (wpm < 0.3) {
            actions.sendKeys(str).perform();
            return;
        }
        long sleeptime = (long) ((1 / (wpm * 5)) * 1000 * 60);

        for (char cha : str.toCharArray()) {
            long lastTime = System.currentTimeMillis();
            actions.sendKeys(String.valueOf(cha)).perform();
            wait(sleeptime, lastTime);
        }
    }

    public void wait(long sleeptime, long lastTimeMillis) {
        try {
            long timebetweem = System.currentTimeMillis() - lastTimeMillis;
            long timeLeft = sleeptime - timebetweem;
            if (timeLeft > 0) Thread.sleep(timeLeft);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
