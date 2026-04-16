package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class YouTubePage {

    WebDriver driver;

    public YouTubePage(WebDriver driver) {
        this.driver = driver;
    }

    By searchBox = By.name("search_query");
    By searchBtn = By.xpath("//button[@aria-label='Search']");

    public void openYouTube() {
   //     driver.get("https://www.youtube.com");
    }
    
    public void search(String text) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));

        driver.findElement(searchBox).sendKeys(text);
        driver.findElement(searchBtn).click();
    }

  // public void search(String text) {
      //  driver.findElement(searchBox).sendKeys(text);
      //  driver.findElement(searchBtn).click();
   // }
}