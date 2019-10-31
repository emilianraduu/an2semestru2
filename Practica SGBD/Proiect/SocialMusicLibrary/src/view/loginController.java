package view;

import com.sun.org.omg.CORBA.Initializer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import main.AppManager;
import main.MembersEntity;
import main.PasswordController;
import main.PersonController;

import javax.security.auth.login.LoginContext;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController{
    @FXML
    private TextField email = new TextField();
    @FXML
    private TextField password;
    static MembersEntity temp = new MembersEntity();
    AppManager app = new AppManager();
    PasswordController pw = new PasswordController(app.getEmf());
//    Boolean isLogged = pw.getBoolean();
    PersonController p = new PersonController(app.getEmf());


    public void goToRegister (ActionEvent event) throws IOException {
        Parent registerParent = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene registerScene = new Scene(registerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }

    public MembersEntity getLog(){
        return temp;
    }

    public void goToNewReleases (ActionEvent event) throws IOException {
        Parent newReleasesParent = FXMLLoader.load(getClass().getResource("newreleases.fxml"));
        if(app.checkLogin(email.getText(), password.getText())) {
            temp = p.findByEmail(email.getText());
            Scene newReleasesScene = new Scene(newReleasesParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(newReleasesScene);
            window.show();
        }
    }

}
