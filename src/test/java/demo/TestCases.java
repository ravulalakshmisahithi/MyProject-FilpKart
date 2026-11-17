package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

@Test
public void TestcaseO1 () throws InterruptedException{
    driver.navigate().to("https://www.flipkart.com/");
    Wrappers.implictwait(driver, 10);
    Wrappers.popup(driver);
    Thread.sleep(1000);
    Wrappers.sendKeys(driver, By.xpath("//form[@action='/search']//input[@name='q']"), "Washing Machine");
    Thread.sleep(1000);
    Wrappers.explicitWait(driver, "Washing", 10);
    Thread.sleep(1000);
    Wrappers.clickElement(driver, By.xpath("//div[text()='Popularity']"));
    Thread.sleep(1000);
  Wrappers.listOfElements(driver, By.xpath("//span[contains(@id, 'productRating')]"), 4);
   Thread.sleep(1000);
}

@Test
public void TestcaseO2 () throws InterruptedException{
    Wrappers.sendKeys(driver, By.xpath("//form[@action='/search']//input[@name='q']"), "iPhone");
    Thread.sleep(1000);
    Wrappers.explicitWait(driver, "iPhone", 10);
    Thread.sleep(1000);
    Wrappers.clickElement(driver, By.xpath("//div[text()='Relevance']"));
    Thread.sleep(1000);
  Wrappers.listOfElementsfordiscountandTitles(driver, By.xpath("//div[@class='HQe8jr']"), By.xpath("//div[@class='HQe8jr']/ancestor::div[4]//div[@class='RG5Slk']"), 17);
   Thread.sleep(1000);
}


@Test
public void TestcaseO3 () throws InterruptedException{
    Wrappers.sendKeys(driver, By.xpath("//form[@action='/search']//input[@name='q']"), "Coffee Mug");
    Thread.sleep(1000);
     Wrappers.explicitWait(driver, "Coffee", 10);
    Thread.sleep(1000);
    Wrappers.scroll(driver);
    Wrappers.clickElement(driver, By.xpath("//div[contains(text(),'4')]/..//div[@class='ybaCDx']"));
    Thread.sleep(1000);
    Wrappers.PrintImageandTitles(driver, By.xpath("//span[@class='PvbNMB']"));
    Thread.sleep(1000);
}

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}