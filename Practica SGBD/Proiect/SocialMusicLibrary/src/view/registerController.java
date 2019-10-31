package view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import main.AppManager;

import java.io.IOException;

public class registerController {

    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;

    AppManager appManager = new AppManager();
    public void goToLogin (ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        appManager.createPerson(email.getText(), password.getText(), fname.getText(), lname.getText(), " " );
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }


}