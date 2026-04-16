package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public static WebDriver driver;
    public static ConfigReader config;

    public static void setup() {

        config = new ConfigReader();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // use config file
        driver.get(config.getUrl());
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}