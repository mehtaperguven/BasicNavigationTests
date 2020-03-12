package com.cbt.utilities;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class TitleVerification2 {

    public static void main(String[] args) throws Exception{

        List<String> urls = Arrays.asList("https://lulugandgeorgia.com", "https://wayfair.com/", "https://walmart.com ","https://westelm.com/");
        WebDriver driver=BrowserFactory.getDriver("chrome");
driver.get(urls.get(0));


        String [] titles=new String[urls.size()];
        String [] urlOfTheWebSites=new String[urls.size()];

        for(int i=0;i<urls.size();i++){
            driver.get(urls.get(i));
            Thread.sleep(2000);
            titles[i]=driver.getTitle();
            urlOfTheWebSites[i]=driver.getCurrentUrl();
        }
int count=0;
        for (int i=0;i<urls.size();i++){
        if (titles[i].contains(" ")){
   titles[i]= titles[i].replace(" ","").toLowerCase();
}
            if (urlOfTheWebSites[i].toLowerCase().contains(titles[i])){
                count++;
            }
        }
if (count==4){
    System.out.println("TEST PASSED ALL URLs CONTAINS THEIR OWN TITLES");
}else {
    System.out.println("TEST FAILED");
}

Thread.sleep(3);
driver.quit();


    }

}
