package com.obsqura.testngcommands;

import com.obsqura.extentreportlistener.SendEmailTest;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportLogging {

    WebDriver driver;
    public ExtentReports report;
    static ExtentTest test;
    public void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "H:\\selenium\\driverfiles\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "H:\\selenium\\driverfiles\\geckodriver-v0.30.0-win32\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "H:\\selenium\\driverfiles\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @BeforeSuite
    public void sendingEmail(){
       SendEmailTest.sendEmail(System.getProperty("user.dir")+"//test-output//","Extent.html","sheenroniya@gmail.com");

    }
    @BeforeTest
    public void setReport(){
        report = new ExtentReports(System.getProperty("user.dir")+"//test-output//Extent.html",true);
        test = report.startTest("ExtentDemo");
    }

    @BeforeMethod
    @Parameters({"browser","url"})
    public void setup(String browserName,String baseUrl) {
        testInitialize(browserName);
        driver.get(baseUrl);
        test.log(LogStatus.PASS, "Navigated to the specified URL");
    }
    @AfterMethod
    public void tearDown() {
        driver.close();
        test.log(LogStatus.PASS, "Successfully driver closed");
        // driver.quit();
    }
    @AfterTest
    public void endReport(){
        report.endTest(test);
        report.flush();
    }

    @AfterSuite
    @Test(enabled = false)
    public void verifyTitle(){
        String expectedTitle="Demo Web Shop";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Values not matching");
        test.log(LogStatus.PASS, "Successfully Asserted");
    }


}
