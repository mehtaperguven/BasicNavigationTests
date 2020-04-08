package com.cbt.tests.homeWork4;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Hw4TestsDays_Sorts {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){

        WebDriverManager.chromedriver().version("79").setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

    }
    @Test
    public void testDays() throws Exception{

        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
       // Thread.sleep(3000);
        Random randomBoxNum = new Random();


        List<WebElement> boxes=new LinkedList<>(driver.findElements(By.cssSelector("[type='checkbox']")));
        List<WebElement> days=new LinkedList<>(driver.findElements(By.xpath("//input[@type='checkbox']//following-sibling::label")));
        System.out.println(">>>>>>>>>"+days.get(0).getText());
        int count=0;

        while(count<4) {
            int num = randomBoxNum.nextInt(6);
            if (boxes.get(num).isEnabled()) {
                boxes.get(num).click();
              //  Thread.sleep(3000);
                System.out.println(days.get(num).getText());
                if (days.get(num).getText().equals("Friday")) {
                    count++;
                }
                boxes.get(num).click();
            }

        }


    }

    @Test
    public void testTodays_Date() throws Exception{
        driver.get("http://practice.cybertekschool.com/dropdown");
        Thread.sleep(3000);
        Select year=new Select(driver.findElement(By.cssSelector("[id='year']")));
        Select month=new Select(driver.findElement(By.id("month")));
        Select day=new Select(driver.findElement(By.xpath("//select[@id='day']")));


        String selectedYear=year.getFirstSelectedOption().getText();
        String selectedMonth=month.getFirstSelectedOption().getText();
        String selectedDay=day.getFirstSelectedOption().getText();
        String actualDate=selectedMonth +"/"+selectedDay+"/"+selectedYear;


         Date date = new Date();
         SimpleDateFormat obj= new SimpleDateFormat("MMMM/dd/yyyy");
         String expectedDate = obj.format(date);
         Assert.assertEquals(actualDate,expectedDate);
         System.out.println(actualDate);
         System.out.println(expectedDate);

    }

//    //solution 4 with data provider
//    @DataProvider(name="dropDown")
//    public Object[][] testData(){
//        return new Object[][]{
//                {"year"  ,    String.valueOf( LocalDate.now().getYear())},
//                {"month" ,   String.valueOf(LocalDate.now().getMonth())},
//                {"day"   ,     String.valueOf(LocalDate.now().getDayOfMonth())}
//        };
//    }
//    @Test (dataProvider = "dropDown")
//    public void dropTest(  String dropName, String expectedData){
//        String SelectedData=new Select(driver.findElement(By.id(dropName))).getFirstSelectedOption().getText().toLowerCase();
//        assertEquals(expectedData.toLowerCase(),SelectedData);
//    }



    @Test
    public void testYearsMonthDay() throws Exception{

        driver.get("http://practice.cybertekschool.com/dropdown");
        WebDriverWait wait = new WebDriverWait(driver, 15);
       // Thread.sleep(3000);
        Random rnd = new Random();

        Select year=new Select(driver.findElement(By.cssSelector("[id='year']")));
        int randomYear= rnd.nextInt(year.getOptions().size());
        year.selectByIndex(randomYear);
       // Thread.sleep(3000);

        Select month=new Select(driver.findElement(By.id("month")));
        Select day=new Select(driver.findElement(By.cssSelector("#day")));









//https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html#of-int-java.time.Month-int-
    }
    @Test
    public void test() {
        driver.get("http://practice.cybertekschool.com/dropdown");
        Select year = new Select(driver.findElement(By.id("year")));
        Select month = new Select(driver.findElement(By.id("month")));
        Select day = new Select(driver.findElement(By.id("day")));
        Random random = new Random();
        int yearToSelect = random.nextInt(year.getOptions().size());
        System.out.println("......."+yearToSelect);
        year.selectByIndex(yearToSelect);

        for (int i = 0; i < 12; i++) {
            //we create date object based on year and month
            LocalDate localDate = LocalDate.of(yearToSelect, i + 1, 1);
            //select a month
            month.selectByIndex(i);
            int actual = day.getOptions().size();//actual number of days
            int expected = Month.from(localDate).length(isLeapYear(yearToSelect)); //expected number of days in a month
            System.out.println("Month: " + month.getFirstSelectedOption().getText());
            System.out.println("Expected number of days: " + expected);
            System.out.println("Actual number of days: " + actual);
            System.out.println();
            Assert.assertEquals(actual, expected);
        }
        driver.quit();
    }
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
        }
        return year % 4 == 0;
    }




    @Test(description = "failed")
    public void testAmazon() {

        driver.get("https://www.amazon.com/gp/site-directory");
        //driver.findElement(By.id("searchDropdownBox")).click();
        List<WebElement> allTags=driver.findElements(By.xpath("//option[starts-with(@value,\"search-alias\")]"));
        List<WebElement> allSubTags=driver.findElements(By.xpath("//h2"));
        List<String> allTagsNames=new LinkedList<>();
        List<String> allSubTagsNames=new LinkedList<>();

        for (WebElement each:allTags){
            allTagsNames.add(each.getText());
        }

        for (WebElement each:allSubTags){
            allSubTagsNames.add(each.getText());
        }

        for (String each:allSubTagsNames){
            if (!allTagsNames.contains(each)){

                System.out.println(each);//prints the names of the sub departments which are not in all dep. names
            }
        }

        Assert.assertEquals(allSubTagsNames,allTagsNames);



//Result is beleow
//        java.lang.AssertionError:
//        Expected :32
//        Actual   :57
    }


@Test
public void w3TagsTest(){

        driver.get("https://www.w3schools.com/");
        //int count=0;
        List<WebElement> allTags=driver.findElements(By.tagName("a"));
        for(WebElement each:allTags){
            if (each.isDisplayed()){
                System.out.println(each.getText());
                System.out.println(each.getAttribute("href"));
               // count++;
            }
        }
    //System.out.println(">>>>>>"+count);

}




    @Test
    public void validLinksTest() {

        driver.get("https://www.selenium.dev/documentation/en/");
        List<WebElement> allTagsWithA = driver.findElements(By.tagName("a"));

//        for (WebElement each:allTagsWithA){
//            try {
//                new URI(each.getAttribute("href")).parseServerAuthority();// parseServerAuthority() ensures that it is a URL (absolute or relative) and not a URN.
//                System.out.println(each.getAttribute("href")+" is valid url");
//            } catch (URISyntaxException e) {
//                System.out.println(each.getAttribute("href")+" is not valid url");
//            }
//        }

        int responseCode = 0;

        for (WebElement each : allTagsWithA) {
            try {
                URL u=new URL(each.getAttribute("href"));
                HttpURLConnection hConnection = (HttpURLConnection) u.openConnection();//down casting
                // hConnection.setRequestMethod("HEAD");
                hConnection.connect();
                responseCode = hConnection.getResponseCode();//response code should be 200!

//                if (respCode >= 400) {
//                    System.out.println(each.getAttribute("href") + " is a broken link");
//                } else {
//                    System.out.println(each.getAttribute("href") + " is a valid link");
//                }
                Assert.assertEquals(responseCode,200);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test(description = "")

    public void cartTest(){

        driver.get("https://amazon.com");
        WebElement searchBox= driver.findElement(By.cssSelector("[id=\"twotabsearchtextbox\"]"));
        searchBox.sendKeys("wooden spoon", Keys.ENTER);

        List<WebElement> prices=driver.findElements(By.cssSelector("[class=\"a-price-whole\"]"));
        Random num=new Random();

        int randomNum= num.nextInt(prices.size());
        //System.out.println(randomNum);

        String expectedTitleOfProduct=driver.findElement(By.xpath("(//span[starts-with(@class,\"a-size-base-plus a-color-\")])["+randomNum+"]")).getText();

        String expectedPrice="$"+driver.findElement(By.xpath("(//span[@class=\"a-price-whole\"])["+randomNum+"]")).getText()+"."
                          +driver.findElement(By.xpath("(//span[@class=\"a-price-fraction\"])["+randomNum+"]")).getText();

       // System.out.println(expectedPrice);
        driver.findElement(By.xpath("(//span[starts-with(@class,\"a-size-base-plus a-c\")])["+randomNum+"]")).click();

          //(//span[@class="a-dropdown-prompt"])[1] WHY does not work???? it gives feature instead of 1
        Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Qty:']/following-sibling::span")).getText(), "1");


        Assert.assertEquals(driver.findElement(By.xpath("//h1[@id=\"title\"]")).getText(),expectedTitleOfProduct);


       Assert.assertEquals(driver.findElement(By.xpath("//td//span[starts-with(@class,\"a-size-medium a-color-price \")]")).getText(),expectedPrice);
        Assert.assertTrue(driver.findElement(By.cssSelector("[id=\"add-to-cart-button\"]")).isDisplayed());


    }


    @Test(description = "first assertion is verified, second is not verified")
    public void primeTest() {

        driver.get("https://amazon.com");
        driver.findElement(By.cssSelector("[id=\"twotabsearchtextbox\"]")).sendKeys("wooden spoon", Keys.ENTER);

        String nameOfFirstPrimeItem=driver.findElement(By.xpath("(//span[@class=\"sb_3XafbQtX sb_25yic0YU sb_CyFsQ0hU\"])[1]")).getText();
        String str=nameOfFirstPrimeItem;
        System.out.println(">>>>"+nameOfFirstPrimeItem);
        driver.findElement(By.xpath("(//i[@class=\"a-icon a-icon-checkbox\"])[1]")).click();

        String actualNameOfItemAfterClick=driver.findElement(By.xpath("(//span[@class=\"sb_3XafbQtX sb_25yic0YU sb_CyFsQ0hU\"])[1]")).getText();

        System.out.println(">>>>"+actualNameOfItemAfterClick);
        Assert.assertEquals(actualNameOfItemAfterClick,nameOfFirstPrimeItem);

        //driver.findElement(By.xpath("(//i[@class=\"a-icon a-icon-checkbox\"])[18]")).click();
       driver.findElement(By.xpath("//div[@id='brandsRefinements']//ul/li[last()]//i")).click();

       // String firstItemInTheBrand=driver.findElement(By.xpath("//span[starts-with(text(),\"Scanwood Olive Wood Utensil \")]")).getText();
        String firstItemInTheBrand=driver.findElement(By.xpath("//span[starts-with(@class,\"a-size-base-plus a-color-\") and contains(text(),\"Spoon Ladle\")]")).getText();

        System.out.println("......."+firstItemInTheBrand);
        Assert.assertEquals(firstItemInTheBrand,str);

        //https://github.com/nikitakharchenko95/Homewwork4/blob/master/src/test/java/Tests/Homework4.java
    }


//hello
    @Test
    public void moreSpoonsTest(){

        driver.get("https://amazon.com");
        driver.findElement(By.cssSelector("[id=\"twotabsearchtextbox\"]")).sendKeys("wooden spoon", Keys.ENTER);



    }

@AfterMethod
    public void close(){
        driver.quit();


}


}
