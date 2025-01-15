package gui;

import javafx.scene.layout.VBox;

public class RootPane extends VBox{

    private static RootPane instance;

    private RootPane() {
        Goto.setRootPane(this);
        Goto.mainPage();
    }

    public static RootPane getInstance() {
        if(instance == null) {
            instance = new RootPane();
        }
        return instance;
    }

}
