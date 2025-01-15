package gui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import utils.Config;

public class MainPage extends VBox {
    private static MainPage instance;

    public static MainPage getInstance() {
        if (instance == null) {
            instance = new MainPage();
        }
        return instance;
    }

    private MainPage() {
        ImageView titleImage = createTitleImageView();
        ImageView click = createClickImageView();
        // Layout for the main page
        this.setAlignment(Pos.CENTER);
        Region spacer = new Region();
        spacer.setPadding(new Insets(Config.MAIN_PAGE_PADDING));
        this.getChildren().addAll(titleImage, spacer, click);
    }

    private void createTextBlinkingEvent(ImageView imageView) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(Config.BLINKING_DURATION), e -> {
                    imageView.setVisible(!imageView.isVisible());
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play();
    }

    private ImageView createClickImageView() {
        Image image = new Image(Config.CLICK_IMAGE_PATH);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(Config.CLICK_WIDTH);
        imageView.setFitHeight(Config.CLICK_HEIGHT);
        imageView.setOpacity(Config.CLICK_OPACITY);
        createTextBlinkingEvent(imageView);

        return imageView;
    }

    private ImageView createTitleImageView() {
        ImageView titleImage = new ImageView(new Image(Config.TITLE_IMAGE_PATH));
        titleImage.setFitWidth(Config.TITLE_WIDTH);
        titleImage.setFitHeight(Config.TITLE_HEIGHT);


        return titleImage;
    }
}
