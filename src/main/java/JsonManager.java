import org.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Client {
    private int clientId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String adress;
    private String email;
    private String username;
    private String encryptedPassword;

    public int getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Client(int clientId, String firstName, String lastName, String phoneNumber, String adress, String email, String username, String encryptedPassword) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.email = email;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public String toString() {
        return "{" +
                " \"clientId\": " +  clientId +
                ", \"firstName\": " + "\"" + firstName + "\"" +
                ", \"lastName\": " + "\"" + lastName + "\""  +
                ", \"phoneNumber\": " + "\"" + phoneNumber + "\"" +
                ", \"adress\": " + "\"" + adress + "\""  +
                ", \"email\": " + "\"" + email  + "\"" +
                ", \"username\": " + "\"" + username  + "\"" +
                ", \"encryptedPassword\": " + "\"" + encryptedPassword  + "\"" +
                '}';
    }
}

public class JsonManager {
    private String filePath;
    private int customerCount=0 ;
    public JsonManager ()
    {
        File testFile = new File("");
        String currentPath = testFile.getAbsolutePath();             //magic spell for finding the path
        this.filePath  = currentPath+"/src/main/resources/customerStorage.json";
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
    public void addJsonObj (Client theOne)
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
    public void indexAll ()  // from 0 to length
    {  try {
        String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("personalData");
        for (int i = 0; i < myArray.length(); i++) {
            myArray.getJSONObject(i).put("clientId", i);
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
    public JSONArray searchJsonObj (String searchFor , int choose )  // 1-firstName 2-lastName 3-phoneNumber 4-adress 5-email 6-username
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
            case 3:
                for(int i=0;i<myArray.length();i++) {
                    JSONObject temp = (JSONObject) myArray.get(i);

                    if (temp.get("phoneNumber").equals(searchFor))
                        totalJsons.put(temp);
                }
                break;
            case 4:
                for(int i=0;i<myArray.length();i++) {
                    JSONObject temp = (JSONObject) myArray.get(i);

                    if (temp.get("adress").equals(searchFor))
                        totalJsons.put(temp);
                }
                break;
            case  5:
                for(int i=0;i<myArray.length();i++) {
                    JSONObject temp = (JSONObject) myArray.get(i);

                    if (temp.get("email").equals(searchFor))
                        totalJsons.put(temp);
                }
                break;
            case 6:
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
    public static void main(String[] argv) {

            Client experimentalClient = new Client(1,"Ion","Castan","0756444890","Jud. PH,Oras. Bacanesti","youexp@gmail.com","xxDemonSlayerxx","gigel");
            JSONObject iExp = new JSONObject(experimentalClient);

            JsonManager manageStuff = new JsonManager();
            manageStuff.init();
            manageStuff.addJsonObj(experimentalClient);
            manageStuff.addJsonObj(experimentalClient);
            System.out.println(manageStuff.searchJsonObj("Ion",1));
            manageStuff.indexAll();
    }
}
