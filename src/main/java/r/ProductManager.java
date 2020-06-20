package r;

import r.model.Product;
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
        PrintWriter writer = new PrintWriter(this.filePath);
        writer.print(myObject);
        writer.close();
    }
    catch (IOException e )
    {
        e.printStackTrace();
    }
    indexAll();
    }

    public void removeJsonObj (int i )
    {
        try {
            String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
            JSONObject myObject = new JSONObject(contents);
            JSONArray myArray = myObject.getJSONArray("product");
            myArray.remove(i);
            PrintWriter writer = new PrintWriter(this.filePath);
            writer.print(myObject);
            writer.close();
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
        indexAll();
    }
    public void indexAll ()  // from 0 to length
    {  try {
        String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("product");
        for (int i = 0; i < myArray.length(); i++) {
            myArray.getJSONObject(i).put("productId", i);
        }
        PrintWriter writer = new PrintWriter(this.filePath);
        writer.print(myObject);
        writer.close();
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    }
    public JSONArray searchJsonObj (String searchFor, int choose)  //1-name 2-orderId 3-category
    { JSONArray totalJsons= new JSONArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
            JSONObject myObject = new JSONObject(contents);
            JSONArray myArray = myObject.getJSONArray("product");
             switch (choose) {
                 case 1:
                 for (int i = 0; i < myArray.length(); i++) {
                     JSONObject temp = (JSONObject) myArray.get(i);

                     if (temp.get("name").equals(searchFor))
                         totalJsons.put(temp);
                 }
                 break;
                 case 2:
                     for (int i = 0; i < myArray.length(); i++) {
                         JSONObject temp = (JSONObject) myArray.get(i);

                         if (temp.get("orderId").equals(searchFor))
                             totalJsons.put(temp);
                     }
                     break;
                 case 3:
                     for (int i = 0; i < myArray.length(); i++) {
                         JSONObject temp = (JSONObject) myArray.get(i);

                         if (temp.get("category").equals(searchFor))
                             totalJsons.put(temp);
                     }
                     break;

             }

        }
        catch (IOException e ) {
            e.printStackTrace();
        }

        return totalJsons;


    }
    public JSONArray showAll ()
    { JSONArray myArray=new JSONArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
            JSONObject myObject = new JSONObject(contents);
            myArray = myObject.getJSONArray("product");

        }catch (IOException e)
        {e.printStackTrace();}
        return myArray;
    }
    public static void main(String[] argv) {

            Product experimentalProduct = new Product(45,0,"careProducts","Dyson Hair Curler",120,"automatic ceramic hair curler");

            ProductManager manageStuff = new ProductManager();
            manageStuff.init();
            manageStuff.addJsonObj(experimentalProduct);
            manageStuff.addJsonObj(new Product(2,0,"toys","Jucarie Mega",25,"Super faina!"));
            manageStuff.addJsonObj(new Product(3,1,"tech","Telfon",40,"Iphone"));
            manageStuff.addJsonObj(new Product(2,1,"food","Ciocolata",15,"Super faina!"));
            //System.out.println(manageStuff.searchJsonObj("Dyson Hair Curler"));


    }
}
