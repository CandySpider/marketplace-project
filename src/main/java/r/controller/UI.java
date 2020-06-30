package r.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UI {

    double x = 0;
    double y = 0;
    @FXML
    private VBox rootPane;

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
    void loadWishlist(ActionEvent event) throws IOException {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewWishlist = FXMLLoader.load(getClass().getClassLoader().getResource("WishlistScene.fxml"));
        Scene scene = new Scene(viewWishlist);

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
}
