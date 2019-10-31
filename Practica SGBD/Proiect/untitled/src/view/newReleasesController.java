package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class newReleasesController {
    public void goToMyLibrary (ActionEvent event) throws IOException {
        Parent myLibraryParent = FXMLLoader.load(getClass().getResource("library.fxml"));
        Scene myLibraryScene = new Scene(myLibraryParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(myLibraryScene);
        window.show();
    }
    public void goToNewReleases (ActionEvent event) throws IOException {
        Parent newReleasesParent = FXMLLoader.load(getClass().getResource("newreleases.fxml"));
        Scene newReleasesScene = new Scene(newReleasesParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newReleasesScene);
        window.show();
    }
    public void goToFriends (ActionEvent event) throws IOException {
        Parent friendsParent = FXMLLoader.load(getClass().getResource("friends.fxml"));
        Scene friendsScene = new Scene(friendsParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(friendsScene);
        window.show();
    }
    public void goToSearch (ActionEvent event) throws IOException {
        Parent searchParent = FXMLLoader.load(getClass().getResource("search.fxml"));
        Scene searchScene = new Scene(searchParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(searchScene);
        window.show();
    }
    public void goToLogOut (ActionEvent event) throws IOException {
        Parent logOutParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene logOutScene = new Scene(logOutParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logOutScene);
        window.show();
    }
}
