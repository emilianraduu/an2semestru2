package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.AppManager;
import main.FriendsEntity;
import main.MembersEntity;
import main.PersonController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class friendsController implements Initializable {

    @FXML
    private Button friend;
    static loginController lg = new loginController();
    static MembersEntity loggedUser = lg.getLog();
    static AppManager app = new AppManager();
    static PersonController p = new PersonController(app.getEmf());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println(p.getAllFriendsByEntity(loggedUser));
//        System.out.println(f.get(0));
        List<FriendsEntity> f = p.getAllFriendsByEntity(loggedUser);
        for(int i = 0; i< f.size();i++){
            System.out.println();
            friend.setText(p.findById(f.get(i).getIdMember2()).getFname() + friend.getText());
        }

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
