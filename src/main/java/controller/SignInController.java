package controller;

import exceptions.UsernameDoesNotAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

import managers.ClientManager;

public class SignInController {
    double x = 0;
    double y = 0;

    @FXML
    private VBox rootPane;

    @FXML
    public TextField Username;

    @FXML
    public PasswordField Password;

    @FXML
    public Text Erroror;


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
    void loadSignUp(ActionEvent event) throws IOException {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        Parent viewSignUp = FXMLLoader.load(getClass().getClassLoader().getResource("SignUp.fxml"));
        Scene scene = new Scene(viewSignUp);

        stage.setScene(scene);
    }

    @FXML
    void logInVerification(ActionEvent event) throws IOException {
        String username = Username.getText();
        String password = Password.getText();

        if(username == null || username.isEmpty()){
            Erroror.setText("Please type in a username");
            return;
        }

        if(password == null || password.isEmpty()){
            Erroror.setText("Password cannot be empty");
            return;
        }



        if(username.equals("admin") && password.equals("admin")){
            try{
                Stage stage = (Stage) Erroror.getScene().getWindow();
                Parent viewAdmin = FXMLLoader.load(getClass().getClassLoader().getResource("ADMIN.fxml"));
                Scene scene = new Scene(viewAdmin);
                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        } else if (username.equals("staff") && password.equals("staff")){
            try{
                Stage stage = (Stage) Erroror.getScene().getWindow();
                Parent viewAdmin = FXMLLoader.load(getClass().getClassLoader().getResource("StaffScene.fxml"));
                Scene scene = new Scene(viewAdmin);

                stage.setScene(scene);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ClientManager.verifyAccount(username, password);
                Stage stage = (Stage) Erroror.getScene().getWindow();
                Parent viewAdminPage = FXMLLoader.load(getClass().getClassLoader().getResource("UI.fxml"));
                Scene scene = new Scene(viewAdminPage);

                stage.setScene(scene);
            } catch (UsernameDoesNotAlreadyExistsException | IOException e) {
                Erroror.setText(e.getMessage());
            }
            return;
        }
    }


}
