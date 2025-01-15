package gui;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import utils.Config;

import java.awt.*;
import java.net.URI;

public class MenuPage extends VBox{
    private static MenuPage instance;
    public static MenuPage getInstance() {
        if (instance == null) {
            instance = new MenuPage();
        }
        return instance;
    }

    private MenuPage() {
        Button startButton = createButton(Config.PLAY_IMAGE_PATH,
                Config.PLAY_TEXT_WIDTH);
        Button upgradeButton = createButton(Config.UPGRADE_IMAGE_PATH,
                Config.UPGRADE_TEXT_WIDTH);
        Button guideButton = createButton(Config.GUIDE_IMAGE_PATH,
                Config.GUIDE_TEXT_WIDTH);
        Button exitButton = createButton(Config.EXIT_IMAGE_PATH,
                Config.EXIT_TEXT_WIDTH);

        // Set actions for buttons
        startButton.setOnAction(e -> Goto.mapSelectPage());
        upgradeButton.setOnAction(e -> Goto.upgradePage());
        guideButton.setOnAction(e -> openGUIDE_LINK());
        exitButton.setOnAction(e -> Platform.exit());

        // Layout for the main page
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(
                startButton, upgradeButton, guideButton, exitButton);
    }

    private Button createButton(String imagePath, int textWidth) {
        ImageView buttonStyle = new ImageView(new Image(Config.BUTTON_IMAGE_PATH));
        buttonStyle.setFitWidth(Config.BUTTON_WIDTH);
        buttonStyle.setFitHeight(Config.BUTTON_HEIGHT);

        ImageView buttonText = new ImageView(new Image(imagePath));
        buttonText.setFitWidth(textWidth);
        buttonText.setFitHeight(Config.TEXT_HEIGHT);

        StackPane stackPane = new StackPane();  // Create a stack pane and add the image view and text
        stackPane.getChildren().addAll(buttonStyle, buttonText);

        Button button = new Button(); // Create a button and set the stack pane as its graphic
        button.setGraphic(stackPane);
        button.setStyle("-fx-background-color: transparent;");

        setMouseHoverAction(button);

        return button;
    }

    private void setMouseHoverAction(Button button) {
        ScaleTransition mouseIn = new ScaleTransition(Duration.seconds(Config.MOUSE_BUTTON_HOVER_DURATION), button);
        mouseIn.setToX(Config.INCREASING_SCALE);
        mouseIn.setToY(Config.INCREASING_SCALE);

        ScaleTransition mouseOut = new ScaleTransition(Duration.seconds(Config.MOUSE_BUTTON_HOVER_DURATION), button);
        mouseOut.setToX(1.0);
        mouseOut.setToY(1.0);

        button.setOnMouseEntered(e -> mouseIn.play());
        button.setOnMouseExited(e -> mouseOut.play());
    }

    private void openGUIDE_LINK() {
        try {
            Desktop.getDesktop().browse(new URI(Config.GUIDE_LINK));
        } catch (Exception ex) {
            ex.printStackTrace(); // Handle any exceptions, such as URISyntaxException or IOException
        }
    }

}
