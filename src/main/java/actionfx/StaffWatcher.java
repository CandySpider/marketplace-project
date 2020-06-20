package actionfx;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import org.json.JSONArray;
import org.json.JSONObject;
import r.*;
import p.StaffManager;
public class StaffWatcher {
    private JSONArray personalizedOrders = new JSONArray();  //we get all the orders for the current staff
    public StaffWatcher(String username)
    {
        StaffManager temp = new StaffManager();
        try {
            JSONObject getcurrentPerson = temp.searchJsonObj(username, 6).getJSONObject(0);
            int tempStaffId = getcurrentPerson.getInt("staffId");

            OrderManager tempOrder = new OrderManager();
            personalizedOrders = tempOrder.searchJsonObj(String.valueOf(tempStaffId),2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static  void main (String[] argv)
    {

    }
}
