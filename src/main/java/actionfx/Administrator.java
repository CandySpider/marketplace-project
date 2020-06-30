package actionfx;

import org.json.JSONArray;
import managers.ClientManager;
import managers.StaffManager;
import managers.ProductManager;

public class Administrator {
    public JSONArray showAllStaff ()
    {
        StaffManager unique = new StaffManager();
       return  unique.showAll();
    }
    public  JSONArray showAllClients()
    {
        ClientManager unique = new ClientManager();
        return unique.showAll();
    }
    public  JSONArray showAllProducts()
    {
        ProductManager unique = new ProductManager();
        return  unique.showAll();
    }

}
