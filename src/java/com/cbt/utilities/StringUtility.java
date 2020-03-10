package com.cbt.utilities;

public class StringUtility {

    public static void main(String[] args) {
verifyEquals("elmal","Elma");


    }
    public static void verifyEquals(String expected, String actual){
//title1=expected title2=actual
        if (expected.equalsIgnoreCase(actual)){
            System.out.println("PASS");
        }else{
            System.out.println("FAIL");
        }
        System.out.println("expected is  "+expected+"\n"+"actual is  "+actual);
    }


}
