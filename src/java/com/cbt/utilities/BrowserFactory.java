package com.cbt.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class BrowserFactory {

    public static void wait(int seconds){

        try {
            Thread.sleep(1000 * seconds);
        }catch(InterruptedException e){
            e.printStackTrace();
            //it shows on the console which kind of exception,
            // reason of exception
            //throws postpone the solution to someone else

        }
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