package com.cbt.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    //we use this class just to load configuration.properties file and to use it when we need properties,key's values
    private static Properties configFile;

    static{
        try{
            //location of properties file,,,,like java file
            String path=System.getProperty("user.dir")+"\\configuration.properties";
            //get that file as stream
            FileInputStream input=new FileInputStream(path);//stream akis demek
            configFile=new Properties();//we generate object of Properties class
            configFile.load(input);//load properties file into Properties object
            input.close();//close the input stream at the end

        }catch (Exception e){
            e.printStackTrace();//yigin problem izini print ettik
            throw new RuntimeException("Failed to load properties file!");

        }
    }
//this method returns property value from configuration.properties file

public static String getProperty(String keyName){
        return configFile.getProperty(keyName);

}



}
