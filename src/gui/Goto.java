package gui;

import actor.Trex;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.GamePlayController;
import utils.Config;

public class Goto {
    private static RootPane rootPane;
    private static int currentMap;
    private static final Button BACK_BUTTON = createBackButton();
    private static final Button START_BUTTON = createStartButton();
    private static Stage primaryStage; // Add a reference to the primaryStage

    private static MediaPlayer backgroundMusic;

    public static void setRootPane(RootPane rootPane) {
        Goto.rootPane = rootPane;
    }
    public static RootPane getRootPane() {
        return rootPane;
    }



    private static void clear() {

        rootPane.getChildren().clear();

    }
    private static void clearBackgroundMusic(){
        if(backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic = null;
        }
    }

    public static void mainPage() {
        clear();
        setBackgroundMusic(new MediaPlayer(new Media(ClassLoader.getSystemResource("mainPage/backgroundMusic.mp3").toString())));
        rootPane.getChildren().add(MainPage.getInstance());
        rootPane.setBackground(loadBackground());
        rootPane.setAlignment(Pos.CENTER);
        rootPane.setOnMouseClicked(e -> {
            Goto.menuPage();
            rootPane.setOnMouseClicked(null);
        });
    }

    public static void menuPage() {
        clear();
        setBackgroundMusic(new MediaPlayer(new Media(ClassLoader.getSystemResource("mainPage/backgroundMusic.mp3").toString())));
        rootPane.getChildren().add(MenuPage.getInstance());
        rootPane.setBackground(loadBackground());
        rootPane.setAlignment(Pos.CENTER);
    }

    public static void mapSelectPage() {
        clear();
        HBox buttonBox = new HBox(Config.BUTTON_BOX_PADDING);
        buttonBox.getChildren().addAll(BACK_BUTTON, START_BUTTON);
        buttonBox.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(MapSelectPage.getInstance(), buttonBox);
        rootPane.setBackground(loadBackground());
        rootPane.setAlignment(Pos.CENTER);
    }

    public static void gamePlay(){
        Trex trex = GamePlayController.getInstance().getTrex();
        clearBackgroundMusic();
        GamePlayController.getInstance().initGame(Trex.getLevel(),currentMap);
        GamePlayController.getInstance().clearBossMonster();
        new GamePlay(primaryStage);

    }
    public static void upgradePage() {
        clear();
        HBox buttonBox = new HBox(Config.BUTTON_BOX_PADDING);
        buttonBox.getChildren().addAll(BACK_BUTTON, START_BUTTON);
        START_BUTTON.setVisible(false);
        buttonBox.setAlignment(Pos.CENTER);
        rootPane.getChildren().addAll(UpgradePage.getInstance(), buttonBox);
        rootPane.setBackground(loadBackground());
        rootPane.setAlignment(Pos.CENTER);
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage; // Set the primary stage
    }
    private static Background loadBackground() {
        Image backgroundImage = new Image(Config.BACKGROUND_IMAGE_PATH);
        return new Background(new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)));
    }

    private static Button createBackButton() {
        Button button = createButton(Config.BACK_BUTTON_TEXT_PATH, Config.BACK_BUTTON_TEXT_WIDTH, Config.BACK_BUTTON_TEXT_HEIGHT);
        button.setOnAction(e -> {
            if (MapSelectPage.getInstance().getCurrentButton() != null) {
                MapSelectPage.getInstance().getCurrentButton().setStyle("-fx-background-color: transparent;");
                MapSelectPage.getInstance().resetCurrentButton();
            }
            Goto.menuPage();
        });
        return button;
    }

    private static Button createStartButton() {
        Button button = createButton(Config.START_BUTTON_TEXT_PATH, Config.START_BUTTON_TEXT_WIDTH, Config.START_BUTTON_TEXT_HEIGHT);
        button.setOnAction(e -> {
            if (MapSelectPage.getInstance().getCurrentButton() != null) {
                MapSelectPage.getInstance().getCurrentButton().setStyle("-fx-background-color: transparent;");
                MapSelectPage.getInstance().resetCurrentButton();
            }
            Goto.gamePlay();
        });
        button.setVisible(false);
        return button;
    }

    private static Button createButton(String imagePath, int textWidth, int textHeight) {
        ImageView buttonStyle = new ImageView(new Image(Config.BUTTON_IMAGE_PATH));
        buttonStyle.setFitWidth(Config.GOTO_BUTTON_IMAGE_WIDTH);
        buttonStyle.setFitHeight(Config.GOTO_BUTTON_IMAGE_HEIGHT);

        ImageView buttonText = new ImageView(new Image(imagePath));
        buttonText.setFitWidth(textWidth);
        buttonText.setFitHeight(textHeight);

        // Create a stack pane and add the image view and text
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(buttonStyle, buttonText);

        // Create a button and set the stack pane as its graphic
        Button button = new Button();
        button.setGraphic(stackPane);
        button.setStyle("-fx-background-color: transparent;");

        setMouseHoverAction(button);
        return button;
    }

    private static void setMouseHoverAction(Button button) {
        ScaleTransition mouseIn = new ScaleTransition(Duration.seconds(Config.MOUSE_BUTTON_HOVER_DURATION), button);
        mouseIn.setToX(Config.INCREASING_SCALE);
        mouseIn.setToY(Config.INCREASING_SCALE);

        ScaleTransition mouseOut = new ScaleTransition(Duration.seconds(Config.MOUSE_BUTTON_HOVER_DURATION), button);
        mouseOut.setToX(1.0);
        mouseOut.setToY(1.0);

        button.setOnMouseEntered(e -> mouseIn.play());
        button.setOnMouseExited(e -> mouseOut.play());
    }

    public static void setCurrentMap(int map) {
        currentMap = map;
        System.out.println("Selected map " + currentMap);
    }

    public static Button getStartButton() {
        return START_BUTTON;
    }

    public static void setBackgroundMusic(MediaPlayer mediaClip){
        if(backgroundMusic == null){
            backgroundMusic = mediaClip;
            backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusic.play();
        }

    }
}
