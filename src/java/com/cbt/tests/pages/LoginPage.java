package com.cbt.tests.pages;

import com.cbt.utilities.BrowserUtils;
import com.cbt.utilities.ConfigurationReader;
import com.cbt.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  extends AbstractPageBase{
    @FindBy(id="prependedInput")
    public WebElement username;

    @FindBy(id="prependedInput2")
    public WebElement password;

    @FindBy(id="_submit")
    public WebElement login;

    @FindBy(linkText = "Forgot your password?")
    public WebElement forgotPassword;

    @FindBy(xpath = "//div[@class='alert alert-error']//div")
    public WebElement warningMessage;

    public LoginPage(){//constructor to connect our WebDriver, page class and PageFactory
        //PageFactory is used for @FindBy annotations
        //PageFactory helps to find elements easier

        PageFactory.initElements(Driver.getDriver(),this);
    }
//login Method version1:
//Login as a specific user
    public void login(String usernameValue, String passwordValue){

        username.sendKeys(usernameValue);
        password.sendKeys(passwordValue,Keys.ENTER);
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(3);
    }

   //login Method version2:
    //Below, Credentials will be retrieved from configuration.properties file
    //It is just like taking default values
    //Login as a default user
    public void login(){

        username.sendKeys(ConfigurationReader.getProperty("store_manager"));
        password.sendKeys(ConfigurationReader.getProperty("password"),Keys.ENTER);
        BrowserUtils.waitForPageToLoad(10);
        BrowserUtils.wait(3);
    }
    public String getWarningMessageText() {
        return warningMessage.getText();
    }

}
