package application;

import gui.Goto;
import gui.RootPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Config;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(RootPane.getInstance(), Config.WIDTH, Config.HEIGHT);
        // Set up the stage
        stage.setTitle("Adventure of TREX");
        stage.setScene(scene);
        Goto.setPrimaryStage(stage);
        stage.show();
    }
}
