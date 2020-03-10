package com.cbt.tests;
import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.StringUtility;
import org.openqa.selenium.WebDriver;


public class NavigationTests {

    public static void main(String[] args) throws Exception {

testMethod("firefox");
//testMethod("chrome");
// testMethod("edge");

    }
    public static void testMethod(String browserName) throws Exception{
        WebDriver driver= BrowserFactory.getDriver(browserName);

        driver.get("http://google.com");
        Thread.sleep(5000);
        String title1=driver.getTitle();

        driver.get("https://etsy.com");
        Thread.sleep(5000);
        String title2=driver.getTitle();

        driver.navigate().back();

        StringUtility.verifyEquals(title1,title2);

        driver.navigate().forward();
        String title3=driver.getTitle();

        StringUtility.verifyEquals(title3,title2);

        driver.close();


    }
    }
