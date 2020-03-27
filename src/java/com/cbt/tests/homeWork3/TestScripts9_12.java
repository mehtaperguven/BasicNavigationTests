package com.cbt.tests.homeWork3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestScripts9_12 {

    private WebDriver driver;
    String URL = "https://practice-cybertekschool.herokuapp.com";


    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();

    }

    @Test
    public void testScript9_10_12() throws Exception{
        driver.findElement(By.linkText("Status Codes")).click();
        String arr[]={"200","301","404","500"};

        for (String each:arr) {
            driver.findElement(By.linkText(each)).click();
            Thread.sleep(3);
            String actual = driver.findElement(By.xpath("//p")).getText();
            Thread.sleep(3);
            //System.out.println(actual);
            String expected = "This page returned a "+each+" status code.";
            System.out.println(expected);
            if (actual.contains(expected)) {
                System.out.println(each+"Test passed");
            } else {
                System.out.println(each+"Test failed");
            }
            if (each.equals("500")){
                driver.close();
            }else {
                driver.get(URL);

                driver.findElement(By.linkText("Status Codes")).click();
                Thread.sleep(3);
            }
        }
    }


    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }
}