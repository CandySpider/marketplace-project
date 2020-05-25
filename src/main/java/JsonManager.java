import org.json.*;
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
        return "Client{" +
                "clientId=" + clientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                '}';
    }
}
public class JsonManager {
   public static void main (String [] argv)
   {
       // creating JSONObject
       JSONObject jo = new JSONObject();

       // putting data to JSONObject
       jo.put("firstName", "John");
       jo.put("lastName", "Smith");
       jo.put("age", 25);
   }
}
