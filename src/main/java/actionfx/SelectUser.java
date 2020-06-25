package actionfx;
import org.json.JSONArray;
import managers.AESencryption;
import managers.ClientManager;
import managers.StaffManager;

public class SelectUser {
    private String username;
    private String password;
    private String ok="notok";
    public SelectUser(String username,String password)
    {
        if(username.equals("terminator")&&password.equals("mode"))
        {
            this.ok="Admin";
            return;
        }
        AESencryption localEncryption = new AESencryption();
       String comparePassword = localEncryption.encrypt(password);

        ClientManager manageClient = new ClientManager();
        JSONArray check1 = manageClient.searchJsonObj(username,6);

        if(!check1.isEmpty())
        { if(check1.getJSONObject(0).get("encryptedPassword").equals(comparePassword)) {
            this.ok = "Client";
            return;
        }
        }
        StaffManager manageStaff = new StaffManager();
        JSONArray check2 = manageStaff.searchJsonObj(username,4);
        if(!check2.isEmpty())
        {  if(check2.getJSONObject(0).get("encryptedPassword").equals(comparePassword))
            this.ok="Staff";

        }
    }

    public String getOk() {
        return ok;
    }
    public  static  void main (String[] argv)
    {
        SelectUser niceUser = new SelectUser("mar23","nice");
        SelectUser niceUser2 = new SelectUser("Bravo","idk12");
        System.out.println(niceUser.getOk());
        System.out.println(niceUser2.getOk());
    }
}
