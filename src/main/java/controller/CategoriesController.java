package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CategoriesController {
    double x = 0;
    double y = 0;

    @FXML
    private VBox rootPane;


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
    @FXML
    void searchPhone(ActionEvent event) throws IOException {
        String name = "phone";
        search(name);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSingIn = FXMLLoader.load(getClass().getClassLoader().getResource("List.fxml"));
        Scene scene = new Scene(viewSingIn);

        stage.setScene(scene);
    }
    @FXML
    void searchLaptop(ActionEvent event) throws IOException {
        String name = "laptop";
        search(name);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSingIn = FXMLLoader.load(getClass().getClassLoader().getResource("List.fxml"));
        Scene scene = new Scene(viewSingIn);

        stage.setScene(scene);
    }
    @FXML
    void searchAC(ActionEvent event) throws IOException {
        String name = "ac";
        search(name);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSingIn = FXMLLoader.load(getClass().getClassLoader().getResource("List.fxml"));
        Scene scene = new Scene(viewSingIn);

        stage.setScene(scene);
    }
    @FXML
    void searchHome(ActionEvent event) throws IOException {
        String name = "home";
        search(name);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSingIn = FXMLLoader.load(getClass().getClassLoader().getResource("List.fxml"));
        Scene scene = new Scene(viewSingIn);

        stage.setScene(scene);
    }
    @FXML
    void searchTV(ActionEvent event) throws IOException {
        String name = "tv";
        search(name);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSingIn = FXMLLoader.load(getClass().getClassLoader().getResource("List.fxml"));
        Scene scene = new Scene(viewSingIn);

        stage.setScene(scene);
    }
    @FXML
    void searchAcc(ActionEvent event) throws IOException {
        String name = "acc";
        search(name);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSingIn = FXMLLoader.load(getClass().getClassLoader().getResource("List.fxml"));
        Scene scene = new Scene(viewSingIn);

        stage.setScene(scene);
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

            if (temp.get("category").equals(name)) {

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
