package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.AppManager;
import main.MembersEntity;
import main.PersonController;

import java.io.IOException;

public class searchController {
    @FXML
    private Text findpeople;
    @FXML
    private TextField searchbar;
    @FXML
    private Button find;

    static loginController lg = new loginController();
    static MembersEntity loggedUser = lg.getLog();
    static AppManager app = new AppManager();
    static PersonController p = new PersonController(app.getEmf());

    public void search(ActionEvent event) {
        findpeople.setText(p.findByEmail(searchbar.getText()).getEmail());
    }

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
        AppManager appManager = new AppManager();
        appManager.signOut();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logOutScene);
        window.show();
    }
}
