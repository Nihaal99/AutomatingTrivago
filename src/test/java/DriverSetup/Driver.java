package DriverSetup;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Driver {

         public static WebDriver driver;
        public static Properties prop;
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
    public static void quitBrowser(){
              driver.close();
    }
    }


