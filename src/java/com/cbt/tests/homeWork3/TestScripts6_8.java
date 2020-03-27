package com.cbt.tests.homeWork3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestScripts6_8 {
    private WebDriver driver;
    String URL="https://practice-cybertekschool.herokuapp.com";

    @BeforeMethod
    public void setup() throws Exception{
        WebDriverManager.chromedriver().version("79").setup();
        driver=new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
       Thread.sleep(3);


    }
    @Test
    public void testScript7() throws Exception{

        driver.findElement(By.linkText("File Upload")).click();
        Thread.sleep(2);
        //System.out.println();
        driver.findElement(By.name("file")).sendKeys("C:/Users/zynpr/OneDrive/Masaüstü/Document_to_upload.txt");
        Thread.sleep(2);
        driver.findElement(By.cssSelector("[id='file-submit']")).click();
        Thread.sleep(2);

        String textDisplayed=driver.findElement(By.id("uploaded-files")).getText();
        //System.out.println(textDisplayed);
        String textExpected="Document_to_upload.txt";
        Assert.assertEquals(textDisplayed,textExpected);

    }


    @Test
    public void Test8() throws Exception{
        driver.findElement(By.linkText("Autocomplete")).click();
        WebElement textBox=driver.findElement(By.cssSelector("[name='myCountry']"));
        textBox.sendKeys("United States of America");
        driver.findElement(By.cssSelector("[class='btn btn-primary']")).click();
        Thread.sleep(3);
        String textActual= driver.findElement(By.xpath("//p[@id='result']")).getText();
        //System.out.println(textActual);
        String textExpected="You selected: United States of America";
        Assert.assertEquals(textActual,textExpected);


    }


    @AfterMethod
    public void close() throws Exception{
       Thread.sleep(3);
        driver.quit();
    }
}

