package com.cbt.utilities;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class TitleVerification2 {

    public static void main(String[] args) throws Exception{
//https://www.luluandgeorgia.com/
        List<String> urls = Arrays.asList("https://www.luluandgeorgia.com", "https://wayfair.com/", "https://walmart.com ","https://www.westelm.com/");
        WebDriver driver=BrowserFactory.getDriver("chrome");



        String [] titles=new String[urls.size()];
        String [] urlOfTheWebSites=new String[urls.size()];

        for(int i=0;i<urls.size();i++){
            driver.get(urls.get(i));
            Thread.sleep(3000);
            titles[i]=driver.getTitle();
            urlOfTheWebSites[i]=driver.getCurrentUrl();
        }

        for (int i=0;i<urls.size();i++){
        if (titles[i].contains(" ")){
        titles[i]= titles[i].replace(" ","").toLowerCase();
            }

            if (urlOfTheWebSites[i].toLowerCase().contains(titles[i])){
                System.out.println("TEST PASSED URL CONTAINS its OWN TITLES");
            }else{
                System.out.println("TEST FAILED");
            }
        }


Thread.sleep(3000);
driver.quit();


    }

}
