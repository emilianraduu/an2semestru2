import controller.ConnectionController;
import javafx.application.Application;

import javafx.stage.Stage;

public class HelloFX extends Application {
    @Override
    public void start(Stage stage) {
        ConnectionController c=new ConnectionController();
    }

    public static void main(String args[]) {
        launch(args);
    }
}