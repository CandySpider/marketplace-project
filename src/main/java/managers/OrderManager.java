package managers;

import org.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class OrderManager {
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

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
            JSONArray myArray = myObject.getJSONArray("personalData");

            int actualIndex=-1;
            for(int j=0;j<myArray.length();j++)
            {   JSONObject temp ;
                temp=myArray.getJSONObject(j);
                if(temp.get("orderId").equals(String.valueOf(i)))
                {
                    actualIndex = j;
                    break;
                }

            }
            if(actualIndex==-1)
                return;

            //remove all the products  , before removing the order

            ProductManager productRemover = new ProductManager();
            JSONObject seeker = myArray.getJSONObject(actualIndex);
            String seekerString = seeker.get("orderId").toString();
            JSONArray remember = productRemover.searchJsonObj(seekerString,2);

            ArrayList<Integer> seekerArray = new ArrayList<>();

            for(int value=0 ; value < remember.length();value++)
            {
                seekerArray.add(Integer.parseInt(String.valueOf(remember.getJSONObject(value).get("productId"))));
            }
            int []done = new int[seekerArray.size()];
            for (int k=0 ; k<done.length;k++)
            {
                done[k]= seekerArray.get(k);
            }
            productRemover.removeJsonArray(done);

            //

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
        JSONArray myArray = myObject.getJSONArray("personalData");
        for (int i = 0; i < myArray.length(); i++) {
            myArray.getJSONObject(i).put("orderId",String.valueOf(i) ) ;
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

    public JSONArray showAll ()
    { JSONArray myArray=new JSONArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
            JSONObject myObject = new JSONObject(contents);
            myArray = myObject.getJSONArray("personalData");

        }catch (IOException e)
        {e.printStackTrace();}
        return myArray;
    }

    public JSONArray searchJsonObj (String searchFor , int choose )  // 1-clientId 2-staffId 3-status
    { JSONArray totalJsons= new JSONArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
            JSONObject myObject = new JSONObject(contents);
            JSONArray myArray = myObject.getJSONArray("personalData");
            switch (choose)
            {
                case 1:
                    for(int i=0;i<myArray.length();i++) {
                        JSONObject temp = (JSONObject) myArray.get(i);

                        if (temp.get("clientId").equals(searchFor))
                            totalJsons.put(temp);
                    }
                    break;
                case 2:
                    for(int i=0;i<myArray.length();i++) {
                        JSONObject temp = (JSONObject) myArray.get(i);

                        if (temp.get("staffId").equals(searchFor))
                            totalJsons.put(temp);
                    }
                    break;
                case 3:
                    for(int i=0;i<myArray.length();i++) {
                        JSONObject temp = (JSONObject) myArray.get(i);

                        if (temp.get("status").equals(searchFor))
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

    public static void main(String[] argv) {

        Order experimentalOrder = new Order(1,1,"done");
        JSONObject iExp = new JSONObject(experimentalOrder);

        OrderManager manageStuff = new OrderManager();
        manageStuff.init();
        manageStuff.addJsonObj(experimentalOrder);
        manageStuff.addJsonObj(experimentalOrder);
        int []exp = {0,1};
        manageStuff.removeJsonArray(exp);
        System.out.println(manageStuff.showAll());





    }

    public static class Order {
        private int orderId;
        private int clientId;
        private int staffId;
        private String status;


        public int getOrderId() {
            return orderId;
        }

        public int getClientId() {
            return clientId;
        }

        public int getStaffId() {
            return staffId;
        }

        public String getStatus() {
            return status;
        }

        public Order(int clientId, int staffId, String status) {
            this.clientId = clientId;
            this.staffId = staffId;
            this.status = status;
        }

        @Override
        public String toString() {
            return "{" +
                    " \"orderId\": " + orderId +
                    ", \"clientId\": " + "\"" + clientId + "\"" +
                    ", \"staffId\": " + "\"" + staffId + "\"" +
                    ", \"status\": " + "\"" + status + "\""  +
                    '}';
        }
    }
}
