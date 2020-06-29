package managers;

import org.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

class Staff {
    private int staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String encryptedPassword;
    private  float salary;

    public Staff(String firstName, String lastName, String email, String username, String encryptedPassword, float salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.salary = salary;
    }

    public float getSalary() {
        return salary;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }


    @Override
    public String toString() {
        return "{" +
                " \"staffId\": " +  staffId +
                ", \"firstName\": " + "\"" + firstName + "\"" +
                ", \"lastName\": " + "\"" + lastName + "\""  +
                ", \"email\": " + "\"" + email  + "\"" +
                ", \"username\": " + "\"" + username  + "\"" +
                ", \"encryptedPassword\": " + "\"" + encryptedPassword  + "\"" +
                ", \"salary\": " + "\"" + salary  + "\"" +
                '}';
    }
}



public class StaffManager {
    private String filePath;
    private int staffCount=0 ;
    public StaffManager ()
    {
        File testFile = new File("");
        String currentPath = testFile.getAbsolutePath();             //magic spell for finding the path
        this.filePath  = currentPath+"/src/main/resources/staffStorage.json";
    }
    public void init ()
    {
        try {
            JSONObject unu = new JSONObject();
            unu.put("personalData",new JSONArray());
            PrintWriter writer = new PrintWriter(this.filePath);  // initialize the whole database from zero
            writer.print(unu.toString());
            writer.close();
            System.out.println(unu);
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
    }
    public void addJsonObj (Staff theOne)
    { try {
        String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("personalData");
        myArray.put(new JSONObject(theOne.toString()));
        //System.out.println(myObject);
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
                if(temp.get("staffId").equals(String.valueOf(i)))
                {
                    actualIndex = j;
                    break;
                }

            }
            if(actualIndex==-1)
                return;

            //remove all the orders  , before removing the staff

            OrderManager orderRemover = new OrderManager();
            JSONObject seeker = myArray.getJSONObject(actualIndex);
            String seekerString = seeker.get("staffId").toString();
            JSONArray remember = orderRemover.searchJsonObj(seekerString,2);

            ArrayList<Integer> seekerArray = new ArrayList<>();

            for(int value=0 ; value < remember.length();value++)
            {
                seekerArray.add(Integer.parseInt(String.valueOf(remember.getJSONObject(value).get("orderId"))));
            }
            int []done = new int[seekerArray.size()];
            for (int k=0 ; k<done.length;k++)
            {
                done[k]= seekerArray.get(k);
            }
            orderRemover.removeJsonArray(done);

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
            myArray.getJSONObject(i).put("staffId", String.valueOf(i));
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
    public JSONArray searchJsonObj (String searchFor , int choose )  // 1-firstName 2-lastName 3-email 4-username
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

                        if (temp.get("firstName").equals(searchFor))
                            totalJsons.put(temp);
                    }
                    break;
                case 2:
                    for(int i=0;i<myArray.length();i++) {
                        JSONObject temp = (JSONObject) myArray.get(i);

                        if (temp.get("lastName").equals(searchFor))
                            totalJsons.put(temp);
                    }
                    break;
                case  3:
                    for(int i=0;i<myArray.length();i++) {
                        JSONObject temp = (JSONObject) myArray.get(i);

                        if (temp.get("email").equals(searchFor))
                            totalJsons.put(temp);
                    }
                    break;
                case 4:
                    for(int i=0;i<myArray.length();i++) {
                        JSONObject temp = (JSONObject) myArray.get(i);

                        if (temp.get("username").equals(searchFor))
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
            myArray = myObject.getJSONArray("personalData");

        }catch (IOException e)
        {e.printStackTrace();}
        return myArray;
    }
    public  static void main (String[] argv)
    {

        AESencryption encrypt = new AESencryption();
        Staff experimentalStaff = new Staff ("Marin","Costea","interzis@yahoo.com","mar23",encrypt.encrypt("nice"),2500);
        Staff experimentalStaff2 = new Staff("Ioana","Gheorghe","rockit@gmai.com","nope12",encrypt.encrypt("super"),4000);
        Staff experimentalStaff3 =new Staff("Jane","Daria","lol@gmail.com","Dar12",encrypt.encrypt("parolamea24"),3000);
        JSONObject initialExp = new JSONObject(experimentalStaff);
        StaffManager godManager = new StaffManager();
        godManager.init();
        godManager.addJsonObj(experimentalStaff);
        godManager.addJsonObj(experimentalStaff2);
        godManager.addJsonObj(experimentalStaff3);
        int []exp ={2};
        godManager.removeJsonArray(exp);
        System.out.println(godManager.showAll());
        //System.out.println(godManager.searchJsonObj("Jane",1));
    }
}
