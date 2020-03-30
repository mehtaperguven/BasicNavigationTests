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
       // driver.get(URL);
        driver.manage().window().maximize();
       Thread.sleep(3);


    }

    /*Step 1. Go to "https://www.tempmailaddress.com/"
Step 2. Copy and save email as a string.
Step 3. Then go to “https://practicecybertekschool.herokuapp.com”
Step 4. And click on “Sign Up For Mailing List".
Step 5. Enter any valid name.
Step 6. Enter email from the Step 2.
Step 7. Click Sign Up
Step 8. Verify that following message is displayed:
“Thank you for signing up. Click the button below to

return to the home page.”
Step 9. Navigate back to the “https://
www.tempmailaddress.com/”
Step 10. Verify that you’ve received an email from
“do-not-reply@practice.cybertekschool.com”
Step 11. Click on that email to open it.
Step 12. Verify that email is from: “do-notreply@practice.cybertekschool.com”
Step 13. Verify that subject is: “Thanks for
subscribing to practice.cybertekschool.com!”*/

    @Test
    public void testScript6() throws Exception{


        driver.get("https://temp-mail.org/");
        Thread.sleep(4000);
        String emailTemp=driver.findElement(By.id("mail")).getAttribute("value");

        Thread.sleep(3000);
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("[name='full_name']")).sendKeys("Erva Erguven");
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(emailTemp);
        driver.findElement(By.cssSelector("[name=\"wooden_spoon\"]")).click();
        Thread.sleep(3000);
        String actual=driver.findElement(By.tagName("h3")).getText();
       //System.out.println(actual);
        String expected="Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actual,expected);

        driver.get("https://temp-mail.org/");
        Thread.sleep(5000);
        WebElement actualMail=driver.findElement(By.xpath("//span[@class='inboxSenderEmail' and contains(text(),'do-not-reply@practice.cybertekschool.com')]"));
        Assert.assertTrue(actualMail.isDisplayed());

        actualMail.click();
        String text=driver.findElement(By.cssSelector("[class='from-email']")).getText();
        Thread.sleep(3000);
        Assert.assertTrue(text.equals("do-not-reply@practice.cybertekschool.com"));
        Thread.sleep(3000);
        String actualTextInMail=driver.findElement(By.tagName("h4")).getText();
        String expectedTextFromMail="Thanks for subscribing to practice.cybertekschool.com!";

        Assert.assertTrue(actualTextInMail.equals(expectedTextFromMail));

//the end:)

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

