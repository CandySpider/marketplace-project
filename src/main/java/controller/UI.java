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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import managers.AESencryption;
import org.json.JSONObject;
import org.json.JSONArray;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;

public class UI implements Initializable {

    double x = 0;
    double y = 0;
    @FXML
    private VBox rootPane;

    @FXML
    private ListView<String> items;

    @FXML
    private TextField searchbar;


    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadCategories(ActionEvent event) throws IOException {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewCategories = FXMLLoader.load(getClass().getClassLoader().getResource("Categories.fxml"));
        Scene scene = new Scene(viewCategories);

        stage.setScene(scene);
    }

    @FXML
    void loadCart(ActionEvent event) throws IOException {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewCart = FXMLLoader.load(getClass().getClassLoader().getResource("CartScene.fxml"));
        Scene scene = new Scene(viewCart);

        stage.setScene(scene);
    }

    @FXML
    void loadSignIn(ActionEvent event) throws IOException {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSingIn = FXMLLoader.load(getClass().getClassLoader().getResource("SignIn.fxml"));
        Scene scene = new Scene(viewSingIn);

        stage.setScene(scene);
    }

    @FXML
    void searchFor(ActionEvent event) throws IOException {
        String name = searchbar.getText();
        search(name);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSingIn = FXMLLoader.load(getClass().getClassLoader().getResource("List.fxml"));
        Scene scene = new Scene(viewSingIn);

        stage.setScene(scene);
    }

    public void initialize(URL location, ResourceBundle resources) {

        String filePath2;
        String contents = null;
        try {
            File testFile = new File("");
            String currentPath = testFile.getAbsolutePath();
            filePath2 = currentPath + "/src/main/resources/productStorage.json";

            contents = new String((Files.readAllBytes(Paths.get(filePath2))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("product");
        for (int i = 0; i < myArray.length(); i++) {
            JSONObject temp = (JSONObject) myArray.get(i);

            items.getItems().add(temp.get("name").toString() + "\t" + temp.get("price") + "\t" + temp.get("category") + "\t\t\t" + temp.get("description"));
        }
        items.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }
    private static org.json.simple.JSONArray productsList = new org.json.simple.JSONArray();
    public static void search(String name) {

        String filePath2;
        String contents = null;

        try {
            File testFile = new File("");
            String currentPath = testFile.getAbsolutePath();
            filePath2 = currentPath + "/src/main/resources/productStorage.json";

            contents = new String((Files.readAllBytes(Paths.get(filePath2))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject myObject = new JSONObject(contents);
        JSONArray myArray = myObject.getJSONArray("product");
        int t = 0;
        for (int i = 0; i < myArray.length(); i++) {
            JSONObject temp = (JSONObject) myArray.get(i);

            if (temp.get("name").equals(name)) {

                JSONObject pr = new JSONObject();

                pr.put("name",temp.get("name"));
                pr.put("price",temp.get("price"));
                pr.put("category", temp.get("category"));
                pr.put("description", temp.get("description"));
                productsList.add(pr);


                try (FileWriter file = new FileWriter("src/main/resources/temp.json")) {

                    file.write(productsList.toString());
                    file.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                t = 1;
                productsList.clear();
            }

        }
        if(t == 0) {
        }
    }
}
