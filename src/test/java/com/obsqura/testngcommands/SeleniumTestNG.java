package com.obsqura.testngcommands;


import com.obsqura.extentreportlistener.SendEmailTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class SeleniumTestNG {
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

    @DataProvider(name="loginData")
    public Object[][] userCredentials(){

        Object[][] data=new Object[2][2];

        data[0][0]="aleenamariya97@gmail.com";
        data[0][1]="aleena97";


        data[1][0]="navyanaveenam@gmail.com";
        data[1][1]="Rithul@12";

        return data;
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
    @Test(enabled = false,dataProvider = "loginData")
    public void verifyUserLogin(String uName,String pword) {
        WebElement loginMenu = driver.findElement(By.className("ico-login"));
        loginMenu.click();
        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys(uName);
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys(pword);
        WebElement submit = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        submit.click();
        WebElement userName = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualUserName = userName.getText();
        String expectedUserName = uName;
        Assert.assertEquals(actualUserName, expectedUserName, "log-in failed");
    }

    @Test(enabled = true)
    public void verifyTitle(){
        String expectedTitle="Demo Web Shop";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Values not matching");
        test.log(LogStatus.PASS, "Successfully Asserted");
    }


}

