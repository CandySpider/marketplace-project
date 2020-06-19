import org.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
            PrintWriter writer = new PrintWriter(this.filePath);
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
    public static void main(String[] argv) {
//Creating a JSONObject object

        //Inserting key-value pairs into the json object

//        File testFile = new File("");
//        String currentPath = testFile.getAbsolutePath();             //magic spell for finding the path
//        String first = currentPath+"/src/main/resources/customerStorage.json";



            Client experimentalClient = new Client(1,"Ion","Castan","0756444890","Jud. PH,Oras. Bacanesti","youexp@gmail.com","xxDemonSlayerxx","gigel");
            JSONObject iExp = new JSONObject(experimentalClient);

            JsonManager manageStuff = new JsonManager();
            manageStuff.init();
            manageStuff.addJsonObj(experimentalClient);
            manageStuff.addJsonObj(experimentalClient);
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
