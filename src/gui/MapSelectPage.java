package gui;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import utils.Config;


public class MapSelectPage extends VBox {
    private static MapSelectPage instance;
    private Button currentButton;

    public static MapSelectPage getInstance() {
        if (instance == null) {
            instance = new MapSelectPage();
        }
        return instance;
    }

    private MapSelectPage() {
        currentButton = null;
        ImageView titleImageView = createTitleImageView();
        HBox mapBox123 = new HBox(Config.MAP_SELECT_PADDING);
        mapBox123.getChildren().addAll(
                createButton(1), createButton(2), createButton(3)
        );
        HBox mapBox456 = new HBox(Config.MAP_SELECT_PADDING);
        mapBox456.getChildren().addAll(
                createButton(4), createButton(5), createButton(6)
        );
        Button map7Button = createButton(7);
        mapBox123.setAlignment(Pos.CENTER);
        mapBox456.setAlignment(Pos.CENTER);
        VBox.setMargin(titleImageView, new Insets(Config.MAP_SELECT_PAGE_TITLE_IMAGE_VIEW_MARGIN));
        VBox.setMargin(mapBox456, new Insets(Config.MAP_SELECT_PAGE_MIDDLE_HBOX_MARGIN));
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(
                titleImageView, mapBox123, mapBox456, map7Button
        );
    }

    private ImageView createTitleImageView() {
        ImageView titleImage = new ImageView(new Image(Config.MAP_SELECT_TEXT_IMAGE_PATH));
        titleImage.setFitWidth(Config.MAP_SELECT_WIDTH);
        titleImage.setFitHeight(Config.MAP_SELECT_HEIGHT);
        return titleImage;
    }

    private Button createButton(int map) {
        ImageView buttonStyle = new ImageView(new Image(Config.MAP_SELECT_IMAGE_PATH.get(map)));
        buttonStyle.setFitWidth(Config.MAP_SELECT_BUTTON_WIDTH);
        buttonStyle.setFitHeight(Config.MAP_SELECT_BUTTON_HEIGHT);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-0.5); // Adjust the brightness to make it darker (-1.0 to 1.0)
        buttonStyle.setEffect(colorAdjust);
        ImageView buttonText = new ImageView(new Image(Config.MAP_SELECT_TEXT_PATH.get(map)));
        buttonText.setFitWidth(Config.MAP_SELECT_MAP_TEXT_WIDTH.get(map));
        buttonText.setFitHeight(Config.MAP_SELECT_MAP_TEXT_HEIGHT.get(map));

        StackPane stackPane = new StackPane(); // Create a stack pane and add the image view and text
        stackPane.getChildren().addAll(buttonStyle, buttonText);

        Button button = new Button(); // Create a button and set the stack pane as its graphic
        button.setGraphic(stackPane);
        button.setStyle("-fx-background-color: transparent;");

        setMouseHoverAction(button);
        setMouseClickedAction(button, map);
        return button;
    }

    private void setMouseClickedAction(Button button, int map) {
        button.setOnAction(e -> {
            RootPane.getInstance().setBackground(loadBackground(Config.MAP_SELECT_IMAGE_PATH.get(map)));
            if (currentButton != null && currentButton != button) {
                currentButton.setScaleX(1.0);
                currentButton.setScaleY(1.0);
                currentButton.setStyle("-fx-background-color: transparent;");
            }
            button.setStyle("-fx-background-radius: 20; -fx-background-color: yellow");
            Goto.setCurrentMap(map);
            Goto.getStartButton().setVisible(true);
            currentButton = button;
        });
    }
    private void setMouseHoverAction(Button button) {
        ScaleTransition mouseIn = new ScaleTransition(Duration.seconds(Config.MOUSE_BUTTON_HOVER_DURATION), button);
        mouseIn.setToX(Config.MAP_SELECT_INCREASING_SCALE);
        mouseIn.setToY(Config.MAP_SELECT_INCREASING_SCALE);

        ScaleTransition mouseOut = new ScaleTransition(Duration.seconds(Config.MOUSE_BUTTON_HOVER_DURATION), button);
        mouseOut.setToX(1.0);
        mouseOut.setToY(1.0);

        button.setOnMouseEntered(e -> mouseIn.play());
        button.setOnMouseExited(e -> mouseOut.play());
    }

    private static Background loadBackground(String imagePath) {
        Image backgroundImage = new Image(imagePath);
        return new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)));
    }

    public Button getCurrentButton() {
        return currentButton;
    }

    public void resetCurrentButton() {
        currentButton = null;
        Goto.getStartButton().setVisible(false);
    }


}
