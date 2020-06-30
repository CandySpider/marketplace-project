package managers;

import org.jetbrains.annotations.NotNull;
import org.json.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class ClientManager {
    private String filePath;
    private int customerCount=0 ;
    public ClientManager()
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
    public void addJsonObj (@NotNull Client theOne)
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

    public void removeJsonObj (int i )  //removes based on the given id
    {
        try {
            String contents = new String((Files.readAllBytes(Paths.get(this.filePath))));
            JSONObject myObject = new JSONObject(contents);
            JSONArray myArray = myObject.getJSONArray("personalData");

            int actualIndex=-1;
            for(int j=0;j<myArray.length();j++)
            {   JSONObject temp ;
                temp=myArray.getJSONObject(j);
                if(temp.get("clientId").equals(String.valueOf(i)))
                {
                    actualIndex = j;
                    break;
                }

            }
            if(actualIndex==-1)
                return;

            //remove all the orders  , before removing the client

               OrderManager orderRemover = new OrderManager();
               JSONObject seeker = myArray.getJSONObject(actualIndex);
               String seekerString = seeker.get("clientId").toString();
               JSONArray remember = orderRemover.searchJsonObj(seekerString,1);

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
            myArray.getJSONObject(i).put("clientId", String.valueOf(i));
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
    public static void main(String[] argv)  {
            AESencryption encrypt = new AESencryption();
            Client experimentalClient = new Client("Ion","Castan","0756444890","Jud. PH,Oras. Bacanesti","youexp@gmail.com","xxDemonSlayerxx",encrypt.encrypt("gigel"),encrypt.encrypt("4485790854113695"));
            Client experimentalClient2 = new Client("Mihai","Corneliu","0782443890","Jud. Timis,Oras. Timisoara","youexp2@gmail.com","Bravo",encrypt.encrypt("idk12"),encrypt.encrypt("4539535904808240"));
            Client experimentalClient3 = new Client("Maria","Castan","0756420897","Jud. Timis,Oras. Bacanesti","youexp3@gmail.com","Aistil",encrypt.encrypt("whatev"),encrypt.encrypt("4680771904751761284"));

            JSONObject iExp = new JSONObject(experimentalClient);

            ClientManager manageStuff = new ClientManager();
            manageStuff.init();
            manageStuff.addJsonObj(experimentalClient);
            manageStuff.addJsonObj(experimentalClient);
            manageStuff.addJsonObj(experimentalClient2);
//            manageStuff.removeJsonObj(2);
//            System.out.println(manageStuff.showAll());
            //manageStuff.addJsonObj(experimentalClient3);
            int []exp = {0,1,2};
            manageStuff.removeJsonArray(exp);

            System.out.println(manageStuff.showAll());






    }
}


class Client {
    private int clientId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String adress;
    private String email;
    private String username;
    private String encryptedPassword;
    private String cryptedCard;

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

    public String getCryptedCard() {
        return cryptedCard;
    }

    public Client(String firstName, String lastName, String phoneNumber, String adress, String email, String username, String encryptedPassword, String cryptedCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.adress = adress;
        this.email = email;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.cryptedCard=cryptedCard;
    }

    @Override
    public String toString() {
        return "{" +
                " \"clientId\": " + clientId +
                ", \"firstName\": " + "\"" + firstName + "\"" +
                ", \"lastName\": " + "\"" + lastName + "\""  +
                ", \"phoneNumber\": " + "\"" + phoneNumber + "\"" +
                ", \"adress\": " + "\"" + adress + "\""  +
                ", \"email\": " + "\"" + email  + "\"" +
                ", \"username\": " + "\"" + username  + "\"" +
                ", \"encryptedPassword\": " + "\"" + encryptedPassword  + "\"" +
                ", \"cryptedCard\": " + "\"" + cryptedCard  + "\"" +
                '}';
    }
}