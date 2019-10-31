package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainFrame extends Stage {
    public MainFrame() {
           setTitle("JavaFX Welcome");
            login();
        BorderPane border = new BorderPane();

        border.setRight(login());
        border.setCenter(message());
        Scene scene = new Scene(border, 1024, 576);
        setScene(scene);
        show();
    }


    private GridPane login(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.BOTTOM_RIGHT);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10, 50, 0, 50));

        Label userName = new Label("EMAIL");

        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setStyle("-fx-background-color: #E2DDDC; -fx-background-radius: 50px;");
        grid.add(userTextField, 0, 2);

        Label pw = new Label("PASSWORD");
        grid.add(pw, 0, 3);

        PasswordField pwBox = new PasswordField();
        pwBox.setStyle("-fx-background-color: #E2DDDC; -fx-background-radius: 50px; -fx-padding: 5px 10px;");
        grid.add(pwBox, 0, 4);


        Button btn = new Button("GO");
        btn.setTextFill(Color.WHITE);
        btn.setStyle("-fx-background-color: #D83E3E; color: white; -fx-background-radius: 100px; -fx-padding: 3px 1em;");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);

        grid.add(hbBtn, 0, 6);
        Button login = new Button("LOGIN");
        login.setStyle("-fx-background-color: none; color: white; -fx-background-radius: 100px; -fx-padding: 3px 1em;");
        Button reg = new Button("REGISTER");
        HBox hlogin = new HBox(10);
        hlogin.setPadding(new Insets(10, 0, 10, 10));
        hlogin.setAlignment(Pos.BOTTOM_CENTER);
        hlogin.getChildren().addAll(login,reg);
        grid.add(hlogin, 0, 8);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 0, 6);

        btn.setOnAction((EventHandler<ActionEvent>) e -> actiontarget.setText("Sign in button pressed"));
        actiontarget.setId("actiontarget");

//        scene.getStylesheets().add("test.css");
        return grid;
    }

    private GridPane message(){
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: #D83E3E");
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(130, 0, 0, 100));

        Text scenetitle = new Text("SHARE YOUR");
        scenetitle.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 30));
        scenetitle.setFill(Color.WHITE);
//        scenetitle.setStyle("-fx-font-size: 30px; -fx-font-weight: black; -fx-font-family: Helvetica;");

        Text taste = new Text("TASTE");
        taste.setFont(Font.font("helvetica", FontWeight.BLACK, FontPosture.ITALIC, 60));
        taste.setFill(Color.WHITE);

        grid.add(scenetitle, 0, 0, 4, 1);
        grid.add(taste,2,1,3,2);
        return grid;
    }
    private void init() {
        Boolean x = false;
    }
}
