package actionfx;

import org.json.JSONArray;
import p.JsonManager;
import p.StaffManager;
import r.ProductManager;

public class Administrator {
    public JSONArray showAllStaff ()
    {
        StaffManager unique = new StaffManager();
       return  unique.showAll();
    }
    public  JSONArray showAllClients()
    {
        JsonManager unique = new JsonManager();
        return unique.showAll();
    }
    public  JSONArray showAllProducts()
    {
        ProductManager unique = new ProductManager();
        return  unique.showAll();
    }

}
