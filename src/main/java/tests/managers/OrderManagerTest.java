package tests.managers;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import managers.OrderManager;
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

class OrderManagerTest {
    private JSONObject saverUnit;
    @BeforeEach
    void setUp() throws IOException {
        OrderManager saver = new OrderManager();
        String contents;

            contents = new String((Files.readAllBytes(Paths.get(saver.getFilePath()))));

        saverUnit = new JSONObject(contents);
    }
    @Test
    void AdderRemoverTester()
    {   OrderManager tester = new OrderManager();
        OrderManager.Order experimentalOrder = new OrderManager.Order(1,1,"done");
        tester.init();
        tester.addJsonObj(experimentalOrder);
        tester.addJsonObj(experimentalOrder);
        tester.addJsonObj(new OrderManager.Order(1,2,"processing"));
        tester.addJsonObj(new OrderManager.Order(1,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(2,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(3,3,"processing"));
        int [] exp={0,1};
        tester.removeJsonArray(exp);
        JSONArray memory = tester.showAll();

        OrderManager tester2 = new OrderManager();
        tester2.init();
        tester2.addJsonObj(new OrderManager.Order(1,2,"processing"));
        tester2.addJsonObj(new OrderManager.Order(1,3,"processing"));
        tester2.addJsonObj(new OrderManager.Order(2,3,"processing"));
        tester2.addJsonObj(new OrderManager.Order(3,3,"processing"));
        JSONArray memory2 = tester2.showAll();


        assertEquals(memory.length(),memory2.length());


    }

    @Test
    void AdderRemoverTester2()
    {
        OrderManager tester = new OrderManager();
        OrderManager.Order experimentalOrder = new OrderManager.Order(1,1,"done");
        tester.init();
        tester.addJsonObj(new OrderManager.Order(1,2,"processing"));
        tester.addJsonObj(new OrderManager.Order(1,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(2,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(3,3,"processing"));
        int [] exp={0,1,4,5,6};
        tester.removeJsonArray(exp);
        JSONArray memory = tester.showAll();

        OrderManager tester2 = new OrderManager();
        tester2.init();
        tester2.addJsonObj(new OrderManager.Order(2,3,"processing"));
        tester2.addJsonObj(new OrderManager.Order(3,3,"processing"));
        JSONArray memory2 = tester2.showAll();


        assertEquals(memory.length(),memory2.length());
    }
    @Test
    void AdderRemoverTester3()
    {
        OrderManager tester = new OrderManager();
        OrderManager.Order experimentalOrder = new OrderManager.Order(1,1,"done");
        tester.init();
        tester.addJsonObj(new OrderManager.Order(1,2,"processing"));
        tester.addJsonObj(new OrderManager.Order(1,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(2,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(3,3,"processing"));
        int [] exp={0,1,4,5,6};
        tester.removeJsonArray(exp);
        JSONArray memory = tester.showAll();

        OrderManager tester2 = new OrderManager();
        tester2.init();
        tester2.addJsonObj(new OrderManager.Order(2,3,"processing"));
        tester2.addJsonObj(new OrderManager.Order(3,3,"processing"));
        JSONArray memory2 = tester2.showAll();


        assertEquals(memory.toString(),memory2.toString());

    }

    @Test
    void AdderRemoverSearcher()
    {
        OrderManager tester = new OrderManager();
        OrderManager.Order experimentalOrder = new OrderManager.Order(1,1,"done");
        tester.init();
        tester.addJsonObj(new OrderManager.Order(1,2,"processing"));
        tester.addJsonObj(new OrderManager.Order(1,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(2,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(3,3,"processing"));
        JSONArray one = tester.searchJsonObj("3",1);
        int [] exp={0,1,2};
        tester.removeJsonArray(exp);

        JSONArray two = tester.searchJsonObj("3",1);
        JSONObject one1 = one.getJSONObject(0);
        JSONObject two2 = two.getJSONObject(0);
        assertEquals(one1.get("clientId").toString(),two2.get("clientId").toString());



    }
    @Test
    void AdderRemoverSearcher2()
    {
        OrderManager tester = new OrderManager();
        OrderManager.Order experimentalOrder = new OrderManager.Order(1,1,"done");
        tester.init();
        tester.addJsonObj(new OrderManager.Order(1,2,"processing"));
        tester.addJsonObj(new OrderManager.Order(1,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(2,3,"processing"));
        tester.addJsonObj(new OrderManager.Order(3,3,"processing"));
        JSONArray one = tester.searchJsonObj("3",1);
        int [] exp={0,1,2};
        tester.removeJsonArray(exp);
        JSONArray two = tester.searchJsonObj("3",1);


         JSONObject  one1 = one.getJSONObject(0);
         JSONObject  two2 = two.getJSONObject(0);
         System.out.println(tester.showAll());

        assertEquals(one1.get("clientId").toString(),two2.get("clientId").toString());



    }
    @AfterEach
    void tearDown() throws FileNotFoundException {
        OrderManager saver = new OrderManager();
        PrintWriter writer;

            writer = new PrintWriter(saver.getFilePath());

        writer.print(saverUnit);
        writer.close();
    }
}