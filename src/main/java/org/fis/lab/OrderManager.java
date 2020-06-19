package org.fis.lab;

import org.fis.lab.model.Order;
import org.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OrderManager {
    private String filePath;
    public OrderManager()
    {
        File testFile = new File("");
        String currentPath = testFile.getAbsolutePath();             //magic spell for finding the path
        this.filePath  = currentPath+"/src/main/resources/orderStorage.json";
    }
    public void init ()
    {
        try {
            JSONObject pr = new JSONObject();
            pr.put("personalData",new JSONArray());
            PrintWriter writer = new PrintWriter(this.filePath);
            writer.print(pr.toString());
            writer.close();
            System.out.println(pr);
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
    }
    public void addJsonObj (Order theOne)
    { try {
        String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("personalData");
        myArray.put(new JSONObject(theOne.toString()));
        System.out.println(myObject);
        PrintWriter writer = new PrintWriter(this.filePath);
        writer.print(myObject);
        writer.close();
    }
    catch (IOException e )
    {
        e.printStackTrace();
    }
    }

    public void removeJsonObj (int i )
    {
        try {
            String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
            JSONObject myObject = new JSONObject(contents);
            JSONArray myArray = myObject.getJSONArray("personalData");
            myArray.remove(i);
            System.out.println(myObject);
            PrintWriter writer = new PrintWriter(this.filePath);
            writer.print(myObject);
            writer.close();
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] argv) {
//Creating a JSONObject object

        //Inserting key-value pairs into the json object

//        File testFile = new File("");
//        String currentPath = testFile.getAbsolutePath();             //magic spell for finding the path
//        String first = currentPath+"/src/main/resources/customerStorage.json";



        Order experimentalOrder = new Order(1,1,1,"done");
        JSONObject iExp = new JSONObject(experimentalOrder);

        OrderManager manageStuff = new OrderManager();
        manageStuff.init();
        manageStuff.addJsonObj(experimentalOrder);
        manageStuff.addJsonObj(experimentalOrder);
        manageStuff.removeJsonObj(1);
        //manageStuff.addJsonObj(experimentalClient);
        // manageStuff.addJsonObj(experimentalClient);

        // JSONArray emails = o.getJSONArray("emails");
        // for (int i = 0; i < emails.length(); i++) {
        //    System.out.println(emails.get(i));
        // }
        // JSONObject family = o.getJSONObject("family");
        // System.out.println(family.getString("spouse"));
//            JSONArray stocare = o.getJSONArray("personalData");
//            System.out.println(stocare.get(0));
//            System.out.println(stocare.get(1));
//            System.out.println(experimentalClient.toString());
//            System.out.println(iExp);
//            JSONObject what = new JSONObject(experimentalClient.toString());
//            System.out.println(what);



    }
}
