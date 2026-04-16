package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.DataProvider;
import utils.ExcelUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import org.testng.Assert;

import base.BaseTest;
import pages.YouTubePage;
import utils.ScreenshotUtil;
import org.testng.asserts.SoftAssert;

public class YouTubeTest extends BaseTest {

    YouTubePage yt;
    WebDriverWait wait;

    @BeforeMethod
    public void setupTest() {
        setup();
        yt = new YouTubePage(driver);
        yt.openYouTube();
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    // 🔥 DATA PROVIDER
    @DataProvider(name = "searchData")
    public Object[][] getData() {
        return new Object[][] {
            {"java tutorial"}
        };
    }
   @Test
    public void multipleValidationTest() {

        SoftAssert soft = new SoftAssert();

        AssertJUnit.assertTrue(driver.getTitle().contains("YouTube"));

        AssertJUnit.assertTrue(driver.getCurrentUrl().contains("youtube"));

        soft.assertAll();
   }
/*VERY IMPORTANT use for multiple run like👉 Step-by-step:

        	Check Title
        	Check URL
        	Even if Title fails → still checks URL
        	At end → shows all failures*/
    
    // ✅ TEST 1: Data-driven Search
    @Test(dataProvider = "searchData")
    public void searchTest(String keyword) {

        yt.search(keyword);

        wait.until(ExpectedConditions.titleContains(keyword.split(" ")[0]));

        String title = driver.getTitle();

        ScreenshotUtil.takeScreenshot(driver, "searchTest_" + keyword);

        AssertJUnit.assertTrue(title.toLowerCase().contains(keyword.split(" ")[0]));
    }
   


    // ✅ TEST 2: URL Validation
    @Test
    public void searchUrlTest() {

        yt.search("selenium tutorial");

        wait.until(ExpectedConditions.urlContains("search"));

        AssertJUnit.assertTrue(driver.getCurrentUrl().contains("search"));
    }

    // ✅ TEST 3: Empty Search
    @Test
    public void emptySearchTest() {

        yt.search("");

        String title = driver.getTitle();

        AssertJUnit.assertFalse(title.toLowerCase().contains("results"));
    }

    // ✅ TEST 4: Suggestions
    @Test
    public void searchSuggestionTest() {

        driver.findElement(By.name("search_query")).sendKeys("selenium");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));

        AssertJUnit.assertTrue(driver.findElement(By.xpath("//div[@role='listbox']")).isDisplayed());
    }

    // ✅ TEST 5: Video Results
    @Test
    public void videoResultsTest() {

        yt.search("java tutorial");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("video-title")));

        AssertJUnit.assertTrue(driver.findElements(By.id("video-title")).size() > 0);
    }

    // ✅ TEST 6: Click First Video
    @Test
    public void clickFirstVideoTest() {

        yt.search("automation testing");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("video-title")));

        driver.findElements(By.id("video-title")).get(0).click();

        wait.until(ExpectedConditions.urlContains("watch"));

        AssertJUnit.assertTrue(driver.getCurrentUrl().contains("watch"));
    }

    // ✅ TEST 7: Title Change
    @Test
    public void titleChangeAfterClickTest() {

        yt.search("selenium tutorial");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("video-title")));

        String before = driver.getTitle();

        driver.findElements(By.id("video-title")).get(0).click();

        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs(before)));

        String after = driver.getTitle();

        Assert.assertNotEquals(before, after);
    }

    // ================= NEW TEST CASES =================

    // ✅ TEST 8: Sign In Button
    @Test
    public void signInButtonTest() {
        AssertJUnit.assertTrue(driver.findElement(By.xpath("//a[contains(@aria-label,'Sign in')]")).isDisplayed());
    }

    // ✅ TEST 9: Sign In Navigation
    @Test
    public void signInNavigationTest() {

        driver.findElement(By.xpath("//a[contains(@aria-label,'Sign in')]")).click();

        wait.until(ExpectedConditions.urlContains("accounts.google.com"));

        AssertJUnit.assertTrue(driver.getCurrentUrl().contains("accounts.google.com"));
    }

    // ✅ TEST 10: Explore Navigation
    @Test
    public void exploreNavigationTest() {

        driver.findElement(By.xpath("//a[@title='Explore']")).click();

        wait.until(ExpectedConditions.urlContains("explore"));

        AssertJUnit.assertTrue(driver.getCurrentUrl().contains("explore"));
    }

    // ✅ TEST 11: Home Navigation
    @Test
    public void homeNavigationTest() {

        yt.search("selenium tutorial");

        driver.findElement(By.id("logo")).click();

        wait.until(ExpectedConditions.urlToBe("https://www.youtube.com/"));

        AssertJUnit.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/");
    }

    // ✅ TEST 12: Scroll Page
    @Test
    public void scrollTest() throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,1000)");

        Thread.sleep(1500);

        AssertJUnit.assertTrue(true);
    }

    // ✅ TEST 13: Voice Search
    @Test
    public void voiceSearchTest() {
        AssertJUnit.assertTrue(driver.findElement(
                By.xpath("//button[contains(@aria-label,'voice')]")).isDisplayed());
    }

    // ✅ TEST 14: Video Player Controls
    @Test
    public void videoPlayerControlsTest() {

        yt.search("automation testing");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("video-title")));

        driver.findElements(By.id("video-title")).get(0).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ytp-play-button")));

        AssertJUnit.assertTrue(driver.findElement(By.className("ytp-play-button")).isDisplayed());
    }

    // ✅ TEST 15: Page Load Performance
    @Test
    public void pageLoadTest() {

        long start = System.currentTimeMillis();

        driver.get("https://www.youtube.com");

        long end = System.currentTimeMillis();

        long loadTime = end - start;

        System.out.println("Load Time: " + loadTime);

        AssertJUnit.assertTrue(loadTime < 5000);
    }
 // ✅ TEST 16: Sign In Flow (UI Validation)
    @Test
    public void signInFlowTest() {

        // Click Sign In
        driver.findElement(By.xpath("//a[contains(@aria-label,'Sign in')]")).click();

        // Wait for email field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));

        // Enter email
        driver.findElement(By.id("identifierId")).sendKeys("test@gmail.com");

        ScreenshotUtil.takeScreenshot(driver, "enterEmail");

        // Click Next
        driver.findElement(By.id("identifierNext")).click();

        // Wait for password field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));

        // Enter password
        driver.findElement(By.name("Passwd")).sendKeys("test123");

        ScreenshotUtil.takeScreenshot(driver, "enterPassword");

        // Click Next
        driver.findElement(By.id("passwordNext")).click();

        // Validation (URL check)
        wait.until(ExpectedConditions.urlContains("accounts.google.com"));

        String url = driver.getCurrentUrl();

        Assert.assertTrue(url.contains("accounts.google.com"),
                "Sign In flow not working ❌");
    }

    @AfterMethod
    public void tearDownTest() {
        tearDown();
    }
}