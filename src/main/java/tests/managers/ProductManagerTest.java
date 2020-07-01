package tests.managers;

import managers.ProductManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    private JSONObject saverUnit;
    @BeforeEach
    void setUp() throws IOException {
        ProductManager saver = new ProductManager();
        String contents;

        contents = new String((Files.readAllBytes(Paths.get(saver.getFilePath()))));

        saverUnit = new JSONObject(contents);
    }

    @Test
    void AdderRemover()
    {
        ProductManager manageStuff = new ProductManager();
        manageStuff.init();
        manageStuff.addJsonObj(new ProductManager.Product(0,"toys","Jucarie Mega",12.99,25,"Super faina!"));
        manageStuff.addJsonObj(new ProductManager.Product(1,"tech","Telfon",45.99,40,"Iphone"));
        manageStuff.addJsonObj(new ProductManager.Product(2,"food","Ciocolata",56.99,15,"Super faina!"));
        int []exp = {2};
        manageStuff.removeJsonArray(exp);
        JSONArray rememberFirst =manageStuff.showAll();

        manageStuff.init();
        manageStuff.addJsonObj(new ProductManager.Product(0,"toys","Jucarie Mega",45.99,25,"Super faina!"));
        manageStuff.addJsonObj(new ProductManager.Product(1,"tech","Telfon",34.99,40,"Iphone"));
        JSONArray rememberSecond = manageStuff.showAll();

        assertEquals(rememberFirst.toString(),rememberSecond.toString());
    }
    @Test
    void Searcher()
    {
        ProductManager manageStuff = new ProductManager();
        manageStuff.init();
        manageStuff.addJsonObj(new ProductManager.Product(0,"toys","Jucarie Mega",12.99,25,"Super faina!"));
        manageStuff.addJsonObj(new ProductManager.Product(1,"tech","Telfon",34.99,40,"Iphone"));
        JSONArray mem = manageStuff.searchJsonObj("toys",3);
        JSONObject mem2 =  mem.getJSONObject(0);
        assertEquals(mem2.get("orderId").toString(),"0");

    }

    @AfterEach
    void tearDown() throws FileNotFoundException {
        ProductManager saver = new ProductManager();
        PrintWriter writer;

        writer = new PrintWriter(saver.getFilePath());

        writer.print(saverUnit);
        writer.close();
    }
}