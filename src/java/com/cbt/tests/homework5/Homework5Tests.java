package com.cbt.tests.homework5;

import com.cbt.tests.pages.CalendarEventsPage;
import com.cbt.tests.pages.LoginPage;
import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;

public class Homework5Tests extends AbstractTestBase{

    protected  WebDriver driver=Driver.getDriver();

    CalendarEventsPage calendarEventsPage=new CalendarEventsPage();
    LoginPage loginPage=new LoginPage();



@Test
    public void testCase1(){

    test=report.createTest("Verify that delete,view, edit options are available");
    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");


    WebElement abc=driver.findElement(By.xpath("//a[@class=\"dropdown-toggle\" and text()=\"...\"]"));
    Actions actions = new Actions(driver);

    BrowserUtils.wait(4);
    actions.moveToElement(abc). pause(2000).perform();

    BrowserUtils.wait(4);
    Assert.assertTrue(calendarEventsPage.delete.isDisplayed());
    Assert.assertTrue(calendarEventsPage.viewBox.isDisplayed());
    Assert.assertTrue(calendarEventsPage.editBox.isDisplayed());

    BrowserUtils.wait(4);
    test.pass("Delete,view, edit options are displayed");
}

@Test
    public void testCase2(){
    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");
    driver.findElement(By.cssSelector("[class=\"column-manager dropdown\"]>a")).click();

    List<WebElement> lst=driver.findElements(By.cssSelector("[class=\"title-cell\"]"));
    int lstSize=lst.size();
    for (int i=1;i<lstSize;i++){
        lst.get(i).click();
    }
    BrowserUtils.wait(3);
     WebElement title= driver.findElement(By.xpath("(//span[text()=\"Title\"])[1]"));

     Assert.assertTrue(title.isDisplayed());

}

@Test
    public void testCase3(){
    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");

    calendarEventsPage.clickCalendarEventButton();
    BrowserUtils.wait(3);
    calendarEventsPage.clickSaveAndCloseButton();
    BrowserUtils.wait(4);

    Assert.assertTrue(calendarEventsPage.saveClose.isDisplayed());
    Assert.assertTrue(calendarEventsPage.saveNew.isDisplayed());
    Assert.assertTrue(calendarEventsPage.saveOnly.isDisplayed());


}

@Test
    public void testCase4(){

    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");

    calendarEventsPage.clickCalendarEventButton();
    BrowserUtils.wait(3);

    calendarEventsPage.clickToCancelButton();
    BrowserUtils.wait(3);

    WebElement allCalendarEventsButton= driver.findElement(By.cssSelector("[class=\"oro-subtitle\"]"));

    Assert.assertTrue(allCalendarEventsButton.isDisplayed());


}


@Test
    public void testCase5(){

    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");
    calendarEventsPage.clickToCreateCalendarEvent();
    BrowserUtils.wait(4);
    //  way1:
//    WebElement startingTime=driver.findElement(By.cssSelector("[id^=\"time_selector_oro_calendar_event_form_start\"]"));
//    WebElement endingTime=driver.findElement(By.cssSelector("[id^=\"time_selector_oro_calendar_event_form_end\"]"));
//    BrowserUtils.wait(3);
//    String startTimeValue=startingTime.getAttribute("value");
//    String endTimeValue=endingTime.getAttribute("value");
//    String format="h:mm a";
//    LocalTime startTime = LocalTime.parse(startTimeValue, DateTimeFormatter.ofPattern(format));
//    LocalTime endTime = LocalTime.parse(endTimeValue, DateTimeFormatter.ofPattern(format));
//
//    Assert.assertEquals(ChronoUnit.HOURS.between(startTime, endTime),1);

//way2:
    String start=calendarEventsPage.getStartTime();
    String end=calendarEventsPage.getEndTime();
    String format="h:mm a";
    long difference=calendarEventsPage.getTimeDifference(start,end,format);
    Assert.assertEquals(difference,1);

}

@Test
    public void testcase6(){
    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");
    calendarEventsPage.clickToCreateCalendarEvent();
    BrowserUtils.wait(4);
//    @FindBy(xpath = "//li[text()='9:00 PM']")
//    public WebElement time900Pm;
    WebElement time=driver.findElement(By.cssSelector("[id^=\"time_selector_oro_calendar_event_form_start\"]"));
        time.click();
   BrowserUtils.wait(4);
    //time.click();
    driver.findElement(By.xpath("//li[@class=\"ui-timepicker-pm\"and text()=\"9:00 PM\"]")).click();
    String text=driver.findElement(By.cssSelector("[name^=\"time_selector_oro_calendar_event_form_end\"]")).getAttribute("value");
    System.out.println(">>>>>>"+text);

    Assert.assertEquals(text,"10:00 PM");

}

@Test
    public void testCase7(){

    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");
    calendarEventsPage.clickToCreateCalendarEvent();
    BrowserUtils.wait(4);
   // driver.findElement(By.xpath("//label[text()=\"All-day event\"]")).click();
    calendarEventsPage.clickToAllDayEventButton();
    BrowserUtils.wait(5);
    //WebElement checkBox=driver.findElement(By.cssSelector("[id^=\"oro_calendar_event_form_allDay-uid-\"]"));
    //Assert.assertTrue(checkBox.isSelected());
    Assert.assertTrue(calendarEventsPage.allDayEventCheckBox.isSelected());
    Assert.assertTrue(calendarEventsPage.startDate.isDisplayed());
    Assert.assertTrue(calendarEventsPage.endDate.isDisplayed());
    Assert.assertTrue(!calendarEventsPage.startTime.isDisplayed());
    Assert.assertTrue(!calendarEventsPage.endTime.isDisplayed());

}
@Test
    public void testCase8(){
    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");
    calendarEventsPage.clickToCreateCalendarEvent();
    BrowserUtils.wait(4);
    calendarEventsPage.clickToRepeatBox();
    Assert.assertTrue(calendarEventsPage.repeatBox.isSelected());

    Select select=new Select(calendarEventsPage.repeatOptionsBox);
    String firstText=select.getFirstSelectedOption().getText();
   // System.out.println(firstText);
    Assert.assertEquals(firstText,"Daily");

    List<WebElement> repeatBoxTexts=select.getOptions();
    List<String> str= Arrays.asList("Daily","Weekly","Monthly","Yearly");
   // System.out.println(str.get(1));

        for (int j=0;j<repeatBoxTexts.size();j++) {
            Assert.assertEquals(repeatBoxTexts.get(j).getText(),str.get(j) );
           // System.out.println(repeatBoxTexts.get(j).getText());
        }


}
@Test
public void testCase9(){
    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");
    calendarEventsPage.clickToCreateCalendarEvent();
    BrowserUtils.wait(4);
    calendarEventsPage.clickToRepeatBox();

    BrowserUtils.wait(3);
    Assert.assertTrue(calendarEventsPage.repeatBox.isSelected());
    Assert.assertTrue(calendarEventsPage.repeatEveryButton.isSelected());
    Assert.assertTrue(calendarEventsPage.neverBox.isSelected());
    Assert.assertEquals(calendarEventsPage.summaryText.getText(),"Daily every 1 day");



}
@Test
    public void testCase10(){
    loginPage.login();
    loginPage.navigateTo("Activities","Calendar Events");
    calendarEventsPage.clickToCreateCalendarEvent();
    BrowserUtils.wait(4);
    calendarEventsPage.clickToRepeatBox();
    calendarEventsPage.clickToAfterButton();
    calendarEventsPage.sendText();
    calendarEventsPage.clickToOccurrences();
    BrowserUtils.wait(3);
    String str=calendarEventsPage.textPart1.getText()+calendarEventsPage.textPart2.getText();
    System.out.println(str);
    Assert.assertEquals(str,"Daily every 1 day, end after 10 occurrences");

}
    @Test
    public void testCase11(){

        loginPage.login();
        loginPage.navigateTo("Activities","Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(4);
        calendarEventsPage.clickToRepeatBox();
        calendarEventsPage.clickToByButton();
        BrowserUtils.wait(3);
        calendarEventsPage.clickChooseDateBox();
        //BrowserUtils.wait(4);
        Select select=new Select(calendarEventsPage.monthBox);
        select.selectByVisibleText("Nov");
        Select select1=new Select(calendarEventsPage.yearBox);
        select1.selectByVisibleText("2021");
        calendarEventsPage.selectDay("18");
        String text2=driver.findElement(By.xpath("//span[text()=\", end by Nov 18, 2021\"]")).getText();
        String string=calendarEventsPage.textPart1.getText()+text2;

        Assert.assertEquals(string,"Daily every 1 day, end by Nov 18, 2021");


    }
    @Test
    public void testCase12(){
        loginPage.login();
        loginPage.navigateTo("Activities","Calendar Events");
        calendarEventsPage.clickToCreateCalendarEvent();
        BrowserUtils.wait(4);
        calendarEventsPage.clickToRepeatBox();
        Select select=new Select(calendarEventsPage.repeatsBox);
        select.selectByVisibleText("Weekly");
        calendarEventsPage.clickDay("monday");
        calendarEventsPage.clickDay("friday");
        Assert.assertTrue(calendarEventsPage.dayM.isSelected());
        Assert.assertTrue(calendarEventsPage.dayF.isSelected());
        String actual=calendarEventsPage.summary.getText();
        System.out.println(actual);
        String expected="Weekly every 1 week on Monday, Friday";
        Assert.assertEquals(actual,expected);



    }

}
