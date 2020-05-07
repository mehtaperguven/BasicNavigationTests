package com.cbt.tests.pages;

import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.Driver;
import org.apache.commons.compress.compressors.lz4.XXHash32;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CalendarEventsPage extends AbstractPageBase {


    @FindBy(xpath = "//a[@title=\"Create Calendar event\"]")
    public WebElement createCalendarEventButton;

    public void clickTOCreateEventButton(){
        createCalendarEvent.click();
    }


    @FindBy(css = "[title='Create Calendar event']")
    private WebElement createCalendarEvent;

    @FindBy(className = "select2-chosen")
    private WebElement owner;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;

    @FindBy(css = "[id^=\"date_selector_oro_calendar_event_form_end\"]")
    public WebElement endDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public  WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(className = "grid-header-cell__label")
    private List<WebElement> columnNames;

    @FindBy(css = "iframe[id^='oro_calendar_event_form_description-uid']")
    private WebElement descriptionFrame;

    @FindBy(css = "[id^='oro_calendar_event_form_title-uid']")
    private WebElement title;

    @FindBy(id = "tinymce")
    private WebElement descriptionTextArea;

    @FindBy(css = "[class='btn-group pull-right'] > button")
    private WebElement saveAndClose;

    @FindBy(xpath = "(//div[@class='control-label'])[1]")
    private WebElement generalInfoTitle;

    @FindBy(xpath = "//label[text()='Description']/following-sibling::div//div")
    private WebElement generalInfoDescription;

    public void enterCalendarEventTitle(String titleValue) {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(title)).sendKeys(titleValue);
    }

    public void enterCalendarEventDescription(String description) {
        //wait until frame is available and switch to it
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(descriptionFrame));
        descriptionTextArea.sendKeys(description);
        driver.switchTo().defaultContent();//exit from the frame
    }

    public void clickOnSaveAndClose() {
        BrowserUtils.wait(3);
        wait.until(ExpectedConditions.elementToBeClickable(saveAndClose)).click();
    }

    public String getGeneralInfoTitleText() {
        BrowserUtils.waitForPageToLoad(20);
        return generalInfoTitle.getText();
    }

    public String getGeneralInfoDescriptionText() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()='Description']/following-sibling::div//div")));
        return generalInfoDescription.getText();
    }

    //#############################################################
    public List<String> getColumnNames() {
        BrowserUtils.waitForPageToLoad(20);
        return BrowserUtils.getTextFromWebElements(columnNames);
    }

    public String getStartTime() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }

    public String getEndTime() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");
    }

    public String getOwnerName() {
        BrowserUtils.waitForPageToLoad(20);
        //wait for element to be present in DOM
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("select2-chosen")));
        wait.until(ExpectedConditions.visibilityOf(owner));
        return owner.getText().trim();
    }

    public void clickToCreateCalendarEvent() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[title='Create Calendar event']")));
        wait.until(ExpectedConditions.elementToBeClickable(createCalendarEvent)).click();
        BrowserUtils.waitForPageToLoad(20);
    }

    public String getStartDate() {
        BrowserUtils.waitForPageToLoad(20);
        wait.until(ExpectedConditions.visibilityOf(startDate));
        BrowserUtils.scrollTo(startDate);
        return startDate.getAttribute("value");
    }

    @FindBy(css = "[href=\"/calendar/event/update/1846\"]")
    public WebElement editBox;

    @FindBy(xpath = "//*[@href='/calendar/event/update/1846']")
    public WebElement viewBox;

    @FindBy(xpath = "//*[@href='#']")
    public WebElement delete;

    @FindBy(css = "[title=\"Create Calendar event\"]")
    public WebElement calendarEventButton;

    public void clickCalendarEventButton(){
        calendarEventButton.click();
    }

    @FindBy(xpath = "//a[@href=\"#\" and  @data-toggle=\"dropdown\"]")
    public WebElement saveAndCloseButton;

    @FindBy(xpath = "//li//button[@type=\"submit\" and contains(text(),\"Save and Close\")]")
    public WebElement saveClose;

    @FindBy(xpath = "//li//button[@type=\"submit\" and contains(text(),\"Save and New\")]")
    public WebElement saveNew;

    @FindBy(xpath = "(//button[@type=\"submit\"])[4]")
    public WebElement saveOnly;

    public void clickSaveAndCloseButton(){
        saveAndCloseButton.click();
    }



    @FindBy(xpath = "//a[@data-action=\"cancel\" and contains(text(),\"Cancel\")]")
    public WebElement cancelButton;

    public void clickToCancelButton(){
        cancelButton.click();
    }


    public long getTimeDifference(String start, String end, String format){
        LocalTime startTime = LocalTime.parse(start, DateTimeFormatter.ofPattern(format));
        LocalTime endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern(format));
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    @FindBy(xpath = "//label[text()=\"All-day event\"]")
    public WebElement allDayEventButton;

    public void clickToAllDayEventButton(){
        allDayEventButton.click();
    }

    @FindBy(css = "[id^=\"oro_calendar_event_form_allDay-uid-\"]")
    public WebElement allDayEventCheckBox;

    //@FindBy(id = "recurrence-repeat-view1387")

   @FindBy(xpath = "//input[starts-with(@id,\"recurrence-repeat-view\")]")
    public WebElement repeatBox;


    public void clickToRepeatBox(){
        repeatBox.click();
    }

    @FindBy(xpath = "//select[starts-with(@id,\"recurrence-repeats-view\")]")
    public WebElement repeatOptionsBox;
    //@FindBy(xpath = "//div[@data-name=\"recurrence-daily\"]//label[text()=\"Repeat every\"]")
    @FindBy(xpath = "//input[@checked='checked']")
    public WebElement repeatEveryButton;

    @FindBy(xpath = "(//input[@checked])[2]")
    public WebElement neverBox;

    @FindBy(xpath = "//span[text()=\"Daily every 1 day\"]")
    public WebElement summaryText;



    @FindBy(xpath = "(//label[@class=\"fields-row\"]//input[@type=\"radio\"])[2]")
    public WebElement afterButton;

    public void clickToAfterButton() {
        afterButton.click();
    }

    @FindBy(xpath = "//input[@data-related-field=\"occurrences\"]")
    public WebElement entryBox;

    public void sendText(){
        entryBox.sendKeys("10");
    }

    //for more general implementation following helps //div[@data-name='recurrence-summary']//div/span[1]
    @FindBy(xpath = "//span[text()=\"Daily every 1 day\"]")
    public WebElement textPart1;
    //div[@data-name='recurrence-summary']//div/span[2]
    @FindBy(xpath = "//span[text()=\", end after 10 occurrences\"]")
    public WebElement textPart2;


    @FindBy(xpath = "//span[text()=\"occurrences\"]")
    public WebElement occurrences;

    public void clickToOccurrences(){
        occurrences.click();
    }
    @FindBy(xpath = "//span[text()=\"By\"]//preceding-sibling::input")
    public WebElement byButton;

    public void clickToByButton(){
        byButton.click();
    }
//id="dp1588869823389"
   // @FindBy(id = "dp1588869823389")>>>>it did not work???
    @FindBy(xpath = "//input[@class='datepicker-input hasDatepicker']")
    public WebElement chooseDateBox;

    public void clickChooseDateBox(){
        chooseDateBox.click();
    }

    @FindBy(className = "ui-datepicker-month")
    public WebElement monthBox;

    @FindBy(className = "ui-datepicker-year")
    public WebElement yearBox;

    public void selectDay(String dayNum){
        driver.findElement(By.xpath("//a[text()='"+dayNum+"']")).click();
    }

    @FindBy(xpath = "//select[starts-with(@id,\"recurrence-repeats-view\")]")
    public WebElement repeatsBox;

    @FindBy(css = "input[value=\"monday\"]")
    public WebElement dayM;

    @FindBy(css = "input[value=\"friday\"]")
    public WebElement dayF;

    public void clickDay(String str){
        driver.findElement(By.cssSelector("input[value='"+str+"']")).click();

    }

    @FindBy(xpath = "//span[text()=\"Weekly every 1 week on Monday, Friday\"]")
    public WebElement summary;


}
