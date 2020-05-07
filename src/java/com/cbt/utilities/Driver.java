package com.cbt.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {/////SINGLETON DESIGN PATTERN

    private static WebDriver driver;//static object
    private Driver(){}//private constructor

    public static WebDriver getDriver(){
        //if webDriver object doesn't exist
        //generate new one
        if (driver == null) {
            //specify browser type in configuration.properties file
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().version("81").setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().version("81").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driver = new ChromeDriver(options);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driver;

    }

//public static WebDriver getDriver1(){//public static getter method
//
//    //if WebDriver object doesn't exist
//    //generate it
//    if (driver == null) {
//        //specify browser type in configuration.properties file
//        String browser = ConfigurationReader.getProperty("browser").toLowerCase();
//        switch (browser) {
//            case "chrome":
//                WebDriverManager.chromedriver().version("79").setup();
//                driver = new ChromeDriver();
//                break;
//            case "firefox":
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                break;
//            default:
//                throw new RuntimeException("Wrong browser name!");
//        }
//    }
//    return driver;
//}

public static void closeDriver(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }
}
}
