package DriverSetup;


import ExtentReportManager.ExtentReport;
import Utils.DateUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Driver{

         public static WebDriver driver;
        public static Properties prop;
        //Creating instance of ExtentReport class so that it can be used by its child classes for report generation process
    //public ExtentReports report = ExtentReport.getReportInstance();
    //This will log each and every step and generate step by step report
    public static ExtentTest logger;
    public static ExtentReports report=ExtentReport.getReportInstance();

          public Driver() {
        try {
            prop = new Properties();
            FileInputStream propFile = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
            prop.load(propFile);
        } catch (FileNotFoundException e) {
            System.out.println("config file location incorrect");
        } catch (IOException e) {
            System.out.println("something went wrong");
            e.printStackTrace();
        }
    }
        protected static void startBrowser() {

        String browser = prop.getProperty("Browser");


        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.out.println("Enter chrome or firefox");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    // ********Method for taking ScreenShot**********
    public void takeScreenShot() {
        TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
        // sourceFile stores the file in binary format.
        File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
        // For storing file in file format,file is saved in destFile.
        File destFile = new File(System.getProperty("user.dir")+"\\Screenshots\\"+ DateUtils.getTimeStamp() + ".png");
        try {
            FileUtils.copyFile(sourceFile, destFile);
            logger.addScreenCaptureFromPath(
                    System.getProperty("user.dir")+"\\Screenshots\\"+ DateUtils.getTimeStamp() + ".png");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    }


