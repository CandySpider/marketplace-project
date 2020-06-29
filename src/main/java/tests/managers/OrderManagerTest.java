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
    void setUp() {
        OrderManager saver = new OrderManager();
        String contents = null;
        try {
            contents = new String((Files.readAllBytes(Paths.get(saver.getFilePath()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert contents != null;
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
    @AfterEach
    void tearDown() {
        OrderManager saver = new OrderManager();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(saver.getFilePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.print(saverUnit);
        writer.close();
    }
}