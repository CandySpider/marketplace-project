package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    double x = 0;
    double y = 0;

    @FXML
    private VBox rootPane;
    
    @FXML
    private ListView<String> list;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void loadMainScene(ActionEvent event) throws IOException {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewUI = FXMLLoader.load(getClass().getClassLoader().getResource("UI.fxml"));
        Scene scene = new Scene(viewUI);

        stage.setScene(scene);
    }
    private JSONArray s = new JSONArray();
    public void initialize(URL location, ResourceBundle resources) {

        Object p;
        JSONParser parser = new JSONParser();
        try {
            FileReader readFile = new FileReader("src/main/resources/temp.json");
            BufferedReader read = new BufferedReader(readFile);
            p = parser.parse(read);
            if (p instanceof JSONArray) {
                s = (JSONArray) p;
            }

        } catch ( IOException | ParseException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < s.size(); i++) {
            JSONObject temp = (JSONObject) s.get(i);

            list.getItems().add(temp.get("name").toString() + "\t" + temp.get("price") + "\t" + temp.get("category") + "\t\t\t" + temp.get("description"));
        }
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



        try (FileWriter file = new FileWriter("src/main/resources/temp.json")) {
            JSONArray l = new JSONArray();
            file.write(l.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
