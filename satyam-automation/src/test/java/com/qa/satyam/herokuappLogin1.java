
package com.qa.satyam;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class herokuappLogin1 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeClass
    @Parameters({ "browser", "version", "platform" })
    public void setup(@Optional("Firefox") String browser,
            @Optional("dev") String version,
            @Optional("Windows 10") String platform) throws MalformedURLException {

        String username = System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY");
        String hub = "hub.lambdatest.com/wd/hub";

        if (username == null || authkey == null) {
            username = "satyam0711";
            authkey = "oTwI7nduWZDjeaMqoW9K3EWZucPp1tQCYucYT2zWMTgOxABzCW";
            System.out.println("Warning: Using hardcoded credentials.");
        }

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName(platform);
        browserOptions.setBrowserVersion(version);

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", username);
        ltOptions.put("accessKey", authkey);
        ltOptions.put("project", "herokuappLogin1");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + "@" + hub), browserOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void loginTest() {
        driver.get("https://the-internet.herokuapp.com/login");

        // Valid login
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button.radius")).click();

        sleep(5000);

        WebElement successMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(successMessage.getText().contains("You logged into a secure area!"),
                "Valid login test failed");
        System.out.println("Valid login test passed");

        // Logout
        driver.findElement(By.cssSelector(".icon-signout")).click();

        sleep(10000);

        // Invalid login
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("username")).sendKeys("Lambdatest");
        driver.findElement(By.id("password")).sendKeys("Lambdatest@123");
        driver.findElement(By.cssSelector("button.radius")).click();

        sleep(5000);

        WebElement errorMessage = driver.findElement(By.id("flash"));
        Assert.assertTrue(errorMessage.getText().contains("Your username is invalid!"),
                "Invalid login test failed");
        System.out.println("Invalid login test passed");

        Status = "passed";
    }

    @AfterClass
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}