package com.cbt.tests.homeWork3;

import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class TestScripts1_5 {


    private WebDriver driver;
    private String URL="https://practice-cybertekschool.herokuapp.com/";
    private By registrationBy=By.linkText("Registration Form");
    private By dateOfBirthBy=By.cssSelector("[name=\"birthday\"]");//[placeholder="MM/DD/YYYY"]
    private By cPlusBy=By.xpath("(//label[@class='form-check-label'])[1]");
    private By javaBy=By.xpath("(//label[@class='form-check-label'])[2]");
    private By javaScriptBy=By.xpath("(//label[@class='form-check-label'])[3]");
    private By firstNameBy=By.cssSelector("input[placeholder='first name']");
    private By lastNameBy=By.cssSelector("[placeholder='last name']");
    private By userNameBy=By.cssSelector("[placeholder='username']");
    private By emailBy=By.xpath("(//input[@type='text'])[4]");
    private By passwordBy=By.xpath("//input[@name='password']");
    private By telBy=By.cssSelector("[type='tel']");
    private By maleGenderBy=By.xpath("(//input[@name='gender'])[1]");
    private By femaleGenderBy=By.cssSelector("[value='female']");
    private By otherBy=By.cssSelector("[value='other']");
    private By birthDayBy=By.cssSelector("[placeholder='MM/DD/YYYY']");
    private By depBy=By.xpath("(//select[@class='form-control selectpicker'])[1]");
    private By jobBy=By.name("job_title");
    //private By javaBy=By.id("inlineCheckbox2");
    private By signBy=By.cssSelector("#wooden_spoon");
    private By textActualBy=By.xpath("//h4//following-sibling::p");

    @BeforeMethod
    public void setup() throws Exception{
        WebDriverManager.chromedriver().version("79").setup();
        driver=new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.findElement(registrationBy).click();
        Thread.sleep(2);

    }


    @Test(description = "this test passed")
    public void testScript1() throws Exception{
        driver.findElement(dateOfBirthBy).sendKeys("wrong_dob");
        Thread.sleep(3);
        WebElement textActual=driver.findElement(By.xpath("//small[@class='help-block' and contains(text(),'The date of birth is not valid')]"));
        Assert.assertTrue(textActual.isDisplayed());
//[data-bv-validator="date"]
    }

    @Test(description = "all languages are displayed")
    public void testScript2() throws Exception{
        WebElement cPlusPlus=driver.findElement(cPlusBy);
        WebElement javaJava=driver.findElement(javaBy);
        WebElement javaScript=driver.findElement(javaScriptBy);

        List<WebElement> languages= new LinkedList<>();

        languages.add(cPlusPlus);
        languages.add(javaJava);
        languages.add(javaScript);

        for (WebElement each: languages) {

            Assert.assertTrue(each.isDisplayed());
            System.out.println(each.getText());

        }
    }

    @Test
    public void testScript3() throws Exception{

        driver.findElement(firstNameBy).sendKeys("B");
        Thread.sleep(3);
        WebElement textExpected=driver.findElement(By.xpath("(//small[@data-bv-for='firstname'])[2]"));
        //small[starts-with(text(),'first name must be more than 2')]
        // small[@class='help-block' and contains(text(),'first name must be more than 2')]
        //System.out.println(textExpected.getText());
        Assert.assertTrue(textExpected.isDisplayed());

    }
    @Test
    public void testScript4() throws Exception{

        driver.findElement(lastNameBy).sendKeys("B");
        Thread.sleep(3);
        WebElement textDisplayed=driver.findElement(By.xpath("(//small[@class='help-block'])[6]"));
        // System.out.println(textDisplayed.getText());
        Assert.assertTrue(textDisplayed.isDisplayed());


    }
    @Test
    public void testScript5() throws Exception{

        driver.findElement(firstNameBy).sendKeys("Mehtap");
        driver.findElement(lastNameBy).sendKeys("ERGUVEN");
        driver.findElement(userNameBy).sendKeys("UserStudent");
        driver.findElement(emailBy).sendKeys("mhtpergvn@gmail.com");
        Thread.sleep(3);
        driver.findElement(passwordBy).sendKeys("12345678");
        Thread.sleep(3);
        driver.findElement(telBy).sendKeys("532-568-9098");
        driver.findElement(femaleGenderBy).click();
        driver.findElement(birthDayBy).sendKeys("03/04/2000");
        Thread.sleep(3);
        Select selectDepartment = new Select(driver.findElement(depBy));
        selectDepartment.selectByVisibleText("Department of Engineering");
        Thread.sleep(3);
        Select selectJobTitle=new Select(driver.findElement(jobBy));
        selectJobTitle.selectByVisibleText("Developer");
        driver.findElement(javaBy).click();
        Thread.sleep(3);
        driver.findElement(signBy).click();
        Thread.sleep(3);
        WebElement text=driver.findElement(textActualBy);
        System.out.println(text.getText());
        Assert.assertTrue(text.isDisplayed());


    }


    @AfterMethod
    public void close(){
        if (driver!=null){
            driver.quit();
            driver=null;
        }

    }
//See you


}
