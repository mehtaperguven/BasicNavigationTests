package com.cbt.utilities;

import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BrowserFactory {
    public static void main(String[] args) {
//In all other cases,
// return a new WebDriver object   for   ChromeDriver,FirefoxDriver, EdgeDriver or   SafariDriver b ased on the value of the stringargument.

    }

    public static WebDriver getDriver(String newBrowser) {

//
//        List<String> browsers=new LinkedList<>(Arrays.asList("chrome","firefox","edge","safari"));
//        System.out.println(browsers);

            if (newBrowser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().version("81").setup();
                return new ChromeDriver();
            } else if (newBrowser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }else if (newBrowser.equalsIgnoreCase("edge")){
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
          }
            else{
                return null;
            }


    }
}