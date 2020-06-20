package r;

import com.sun.corba.se.impl.resolver.ORBDefaultInitRefResolverImpl;
import com.sun.org.apache.xpath.internal.operations.Or;
import r.model.Order;
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
        indexAll();
    }
    public void indexAll ()  // from 0 to length
    {  try {
        String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("personalData");
        for (int i = 0; i < myArray.length(); i++) {
            myArray.getJSONObject(i).put("orderId", i);
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

        Order experimentalOrder = new Order(30,1,1,"done");
        JSONObject iExp = new JSONObject(experimentalOrder);

        OrderManager manageStuff = new OrderManager();
        manageStuff.init();
        manageStuff.addJsonObj(experimentalOrder);
        manageStuff.addJsonObj(experimentalOrder);
        manageStuff.addJsonObj(new Order(5,1,2,"processing"));
        manageStuff.addJsonObj(new Order(7,1,3,"processing"));
        manageStuff.addJsonObj(new Order(8,2,3,"processing"));
        manageStuff.addJsonObj(new Order(9,3,3,"processing"));
        System.out.println(manageStuff.searchJsonObj("processing",3));




    }
}
