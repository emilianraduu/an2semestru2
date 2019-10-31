package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;

public class loginController {
    public void goToRegister (ActionEvent event) throws IOException {
        Parent registerParent = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene registerScene = new Scene(registerParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.show();
    }

    public void goToNewReleases (ActionEvent event) throws IOException {
        Parent newReleasesParent = FXMLLoader.load(getClass().getResource("newreleases.fxml"));
        Scene newReleasesScene = new Scene(newReleasesParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newReleasesScene);
        window.show();
    }

}
