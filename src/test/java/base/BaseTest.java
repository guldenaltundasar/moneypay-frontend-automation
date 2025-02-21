package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Invalid browser: " + browser);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void takeScreenshot(String name) {
        try {
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                boolean isDirCreated = screenshotsDir.mkdir();
                if (!isDirCreated) {
                    throw new IOException("Failed to create screenshots directory");
                }
            }

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String targetPath = "screenshots/" + name + "_" + timestamp + ".png";
            Files.copy(source.toPath(), new File(targetPath).toPath());
        } catch (IOException e) {
            logger.error("Failed to take screenshot: {}", e.getMessage());
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
} 