package view;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

public class Menu {
    public void getMenu(){
        if (System.getProperty("os.name").contains("Mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }

        MenuBar menuBar = new MenuBar();
        final String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Mac"))
            menuBar.useSystemMenuBarProperty().set(true);
        javafx.scene.control.Menu menu1 = new javafx.scene.control.Menu("Menu 1");
        menuBar.getMenus().add(menu1);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
    }
}
