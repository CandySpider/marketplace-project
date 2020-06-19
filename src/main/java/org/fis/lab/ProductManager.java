package org.fis.lab;

import org.fis.lab.model.Product;
import org.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProductManager {
    private String filePath;
    public ProductManager()
    {
        File testFile = new File("");
        String currentPath = testFile.getAbsolutePath();             //magic spell for finding the path
        this.filePath  = currentPath+"/src/main/resources/productStorage.json";
    }
    public void init ()
    {
        try {
            JSONObject pr = new JSONObject();
            pr.put("product",new JSONArray());
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
    public void addJsonObj (Product theOne)
    { try {
        String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("product");
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
            JSONArray myArray = myObject.getJSONArray("product");
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



            Product experimentalProduct = new Product(1,"Dyson Hair Curler",120,"automatic ceramic hair curler");
            JSONObject iExp = new JSONObject(experimentalProduct);

            ProductManager manageStuff = new ProductManager();
            manageStuff.init();
            manageStuff.addJsonObj(experimentalProduct);
            manageStuff.addJsonObj(experimentalProduct);
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
