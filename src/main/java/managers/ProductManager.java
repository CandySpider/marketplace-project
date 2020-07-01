package managers;

import org.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProductManager {
    private String filePath;

    public  String getFilePath()
    {
        return this.filePath;
    }
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

            //remove all the orders  , before removing the client

//            OrderManager orderRemover = new OrderManager();
//            JSONObject seeker = myArray.getJSONObject(i);
//            String seekerString = seeker.get("clientId").toString();
//            JSONArray remember = orderRemover.searchJsonObj(seekerString,1);


            //
            int actualIndex=-1;
            for(int j=0;j<myArray.length();j++)
            {   JSONObject temp ;
                temp=myArray.getJSONObject(j);
                if(temp.get("productId").equals(String.valueOf(i)))
                {
                    actualIndex = j;
                    break;
                }

            }
            if(actualIndex==-1)
                return;
            myArray.remove(actualIndex);
            //System.out.println(myObject);
            PrintWriter writer = new PrintWriter(this.filePath);
            writer.print(myObject);
            writer.close();
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }

    }
    public void removeJsonArray (int[] inRemoval)
    {
        for (int value : inRemoval) {
            removeJsonObj(value);
        }
        indexAll();
    }
    public void indexAll ()  // from 0 to length
    {  try {
        String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("product");
        for (int i = 0; i < myArray.length(); i++) {
            myArray.getJSONObject(i).put("productId", String.valueOf(i));
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
    { JSONArray myArray = new JSONArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
            JSONObject myObject = new JSONObject(contents);
            myArray = myObject.getJSONArray("product");

        } catch (IOException e)
        {e.printStackTrace();}
        return myArray;
    }
    public static void main(String[] argv) {

            Product experimentalProduct = new Product(0,"careProducts","Dyson Hair Curler",249.99,120,"automatic ceramic hair curler");

            ProductManager manageStuff = new ProductManager();
            manageStuff.init();
            manageStuff.addJsonObj(experimentalProduct);
            manageStuff.addJsonObj(new Product(0,"toys","Jucarie Mega",12.99,25,"Super faina!"));
            manageStuff.addJsonObj(new Product(1,"tech","Telefon",299.99,40,"Iphone"));
            manageStuff.addJsonObj(new Product(2,"food","Ciocolata",14.99,15,"Super faina!"));
//            int exp [] = {0,1,2,3};
//            manageStuff.removeJsonArray(exp);
            System.out.println(manageStuff.showAll());


    }

    public static class Product {  //if orderId = -1 => inStock , otherwise taken
        private int productId;
        private String name;
        private int quantity;
        private String description;
        private int orderId;
        private String category;
        private double price;

        public String getCategory() {
            return category;
        }

        public int getOrderId() {
            return orderId;
        }

        public int getProductId() {
            return productId;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public String getDescription() {
            return description;
        }

        public double getPrice() { return price; }


        public Product(int orderId, String category, String name, double price, int quantity, String description) {
            this.orderId=orderId;
            this.category=category;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.description = description;

        }

        @Override
        public String toString() {
            return "{" +
                    " \"productId\": " + productId +
                    ", \"orderId\": " +  "\"" + + orderId  +  "\"" +   // =-1 => inStock
                    ", \"category\": " + "\"" + category + "\"" +
                    ", \"name\": " + "\"" + name + "\"" +
                    ", \"price\": " + "\"" + price + "\"" +
                    ", \"quantity\": " + "\"" + quantity + "\"" +
                    ", \"description\": " + "\"" + description + "\""  +
                    '}';
        }
    }
}
