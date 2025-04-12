package com.qa.satyam;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class herokuappAlert1 {

    private RemoteWebDriver driver;
    private String status = "failed";

    @BeforeClass
    @Parameters({ "browser", "version", "platform" })
    public void setup(@Optional("Edge") String browser,
            @Optional("latest") String version,
            @Optional("Windows 10") String platform) throws MalformedURLException {

        String username = System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY");
        String hub = "hub.lambdatest.com/wd/hub";

        if (username == null || authkey == null) {
            username = "satyam0711";
            authkey = "oTwI7nduWZDjeaMqoW9K3EWZucPp1tQCYucYT2zWMTgOxABzCW";
            System.out.println("Warning: Using hardcoded credentials.");
        }

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", platform);
        caps.setCapability("browserName", browser);
        caps.setCapability("version", version);
        caps.setCapability("build", "JavaScript Alerts Test");
        caps.setCapability("name", "herokuappAlertTest1");
        caps.setCapability("plugin", "TestNG");

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + "@" + hub), caps);
    }

    @Test
    public void javascriptAlertTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // JS Alert
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert1 = driver.switchTo().alert();
        System.out.println("JS Alert text: " + alert1.getText());
        waitForAlert();
        alert1.accept();

        // JS Confirm
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert2 = driver.switchTo().alert();
        System.out.println("JS Confirm text: " + alert2.getText());
        waitForAlert();
        alert2.dismiss();

        // JS Prompt
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert3 = driver.switchTo().alert();
        System.out.println("JS Prompt text: " + alert3.getText());
        alert3.sendKeys("Hello Lambdatest");
        waitForAlert();
        alert3.accept();

        status = "passed";
    }

    private void waitForAlert() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        driver.executeScript("lambda-status=" + status);
        driver.quit();
    }
}
