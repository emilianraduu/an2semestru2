package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.AppManager;
import main.MembersEntity;
import main.PasswordController;

import javax.persistence.EntityManagerFactory;
import javax.swing.text.View;
import java.io.IOException;

public class ViewController {
    AppManager app = new AppManager();
    EntityManagerFactory emf = app.getEmf();
    PasswordController pw = new PasswordController(emf);

    MembersEntity loggedUser = new MembersEntity();
    String logged = "";
    ViewController(Stage primaryStage) throws IOException {
//        if(!pw.getBoolean()){
            logged = "login.fxml";
//        }
//        else
//        {
//            logged = "newreleases.fxml";
//        }
        Parent root = FXMLLoader.load(getClass().getResource(logged));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public AppManager getApp() {
        return app;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public MembersEntity getLoggedUser() {
        return loggedUser;
    }
}
