package com.cbt.utilities;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class TitleVerification3 {
    public static void main(String[] args) throws Exception {
        List<String> urls = Arrays.asList("https://www.luluandgeorgia.com", "https://wayfair.com/", "https://walmart.com ", "https://www.westelm.com/");

        String[] titles = new String[urls.size()];
        String[] urlOfTheWebSites = new String[urls.size()];


        for (int i = 0; i < urls.size(); i++) {
            WebDriver driver = BrowserFactory.getDriver("chrome");
            driver.get(urls.get(i));
            Thread.sleep(3000);
            titles[i] = driver.getTitle();
            urlOfTheWebSites[i] = driver.getCurrentUrl();
            titles[i] = titles[i].replace(" ", "").toLowerCase();

            if (urlOfTheWebSites[i].toLowerCase().contains(titles[i])) {
                System.out.println("TEST PASSED URL CONTAINS its OWN TITLES");
            } else {
                System.out.println("TEST FAILED");
            }
            Thread.sleep(3000);
            driver.quit();

        }


    }

}
