package actionfx;

import org.json.JSONArray;
import p.StaffManager;

public class Administrator {
    public JSONArray showAll ()
    {
        StaffManager unique = new StaffManager();
       return  unique.showAll();
    }

}
