package com.cbt.utilities;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class TitleVerification {

    public static void main(String[] args) throws Exception {
        List<String> urls = Arrays.asList("http://practice.cybertekschool.com/", "http://practice.cybertekschool.com/dropdown", "http://practice.cybertekschool.com/login");
        WebDriver driver= BrowserFactory.getDriver("chrome");


        String [] titles=new String[urls.size()];
        String [] urlOfTheSites=new String[urls.size()];


        for(int i=0;i<urls.size();i++){
            driver.get(urls.get(i));
            Thread.sleep(2000);
            titles[i]=driver.getTitle();
        }

       // System.out.println(Arrays.toString(titles));

        if (titles[0].equals(titles[1])&&titles[1].equals(titles[2])){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
        }



        for(int i=0;i<urls.size();i++){
            driver.get(urls.get(i));
            Thread.sleep(2000);
            urlOfTheSites[i]=driver.getCurrentUrl();
        }

        //System.out.println(Arrays.toString(urlOfTheSites));


        if (urlOfTheSites[0].startsWith("http://practice.cybertekschool.com")&&
            urlOfTheSites[1].startsWith("http://practice.cybertekschool.com")&&
            urlOfTheSites[2].startsWith("http://practice.cybertekschool.com")){

            System.out.println("SECOND TEST FOR THE URL PASSED");
        }else{
            System.out.println("SECOND TEST FOR THE URL FAILED");
        }



        Thread.sleep(3000);
        driver.quit();

    }


}
