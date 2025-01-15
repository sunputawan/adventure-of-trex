package gui;

import actor.Trex;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import logic.GamePlayController;
import utils.Config;
import utils.GamePlayConfig;
import utils.StatConfig;

public class UpgradePage extends VBox {
    private static UpgradePage instance;
    private static ImageView trexImageInstance;
    private static Text moneyTextInstance;
    private static int currentLevel;
    private static int currentMoney;
    private static Text levelTextInstance;
    private static Text atkTextInstance;
    private static Text hpTextInstance;
    private static Button upgradeButtonInstance;
    public static UpgradePage getInstance() {
        currentMoney = Trex.getMoney();
        currentLevel = Trex.getLevel();

        if (instance == null) {
            instance = new UpgradePage();
        }
        instance.updateUpgradeButton();
        System.out.println("currentLevel : " + currentLevel);
        return instance;
    }

    private UpgradePage() {
        VBox statBox = createStatBox();
        VBox trexBox = createTrexImage();

        HBox imageAndStatBox = new HBox(Config.IMAGE_AND_STAT_BOX_PADDING);
        imageAndStatBox.getChildren().addAll(trexBox, statBox);
        imageAndStatBox.setAlignment(Pos.CENTER);

        upgradeButtonInstance = createUpgradeButton(currentMoney, currentLevel);
        VBox imageAndStatBoxWithButton = new VBox();
        imageAndStatBoxWithButton.getChildren().addAll(imageAndStatBox);
        imageAndStatBoxWithButton.setAlignment(Pos.CENTER);

        StackPane upgradePane = createUpgradePane(imageAndStatBoxWithButton);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(upgradePane, upgradeButtonInstance);
    }
    private StackPane createUpgradePane(VBox imageAndStatBoxWithButton) {
        StackPane upgradePane = new StackPane();
        ImageView boxBackground = new ImageView(new Image(Config.BUTTON_IMAGE_PATH));
        boxBackground.setFitWidth(Config.UPGRADE_PANE_WIDTH);
        boxBackground.setFitHeight(Config.UPGRADE_PANE_HEIGHT);
        upgradePane.getChildren().addAll(boxBackground, imageAndStatBoxWithButton);
        upgradePane.setAlignment(Pos.CENTER);

        return upgradePane;
    }

    private Button createUpgradeButton(int currentMoney, int currentLevel) {
        Button upgradeButton = new Button();
        Text upgradeText = createText("UPGRADE", Config.UPGRADE_PAGE_UPGRADE_TEXT_SIZE, Color.WHITE);
        upgradeText.setTextAlignment(TextAlignment.CENTER);
        moneyTextInstance = createText(
                currentMoney + "/" + Config.REQUIRE_MONEY.get(currentLevel + 1), Config.UPGRADE_PAGE_MONEY_TEXT_SIZE, Color.WHITE);
        moneyTextInstance.setTextAlignment(TextAlignment.CENTER);
        VBox upgradeTextAndMoney = new VBox(Config.UPGRADE_TEXT_AND_MONEY_PADDING);
        upgradeTextAndMoney.getChildren().addAll(upgradeText, moneyTextInstance);
        upgradeTextAndMoney.setAlignment(Pos.CENTER);
        ImageView buttonImage = loadButtonImage();
        StackPane upgradeButtonPane = new StackPane();
        upgradeButtonPane.getChildren().addAll(buttonImage, upgradeTextAndMoney);

        upgradeButton.setGraphic(upgradeButtonPane);
        upgradeButton.setStyle("-fx-background-color: transparent;");

        setMouseHoverAction(upgradeButton);
        setMouseClickedAction(upgradeButton);
        return upgradeButton;
    }

    private void setMouseClickedAction(Button button) {
        Trex trex = GamePlayController.getInstance().getTrex();
        button.setOnAction(e -> {
            if (Config.REQUIRE_MONEY.get(currentLevel + 1) != null && currentMoney >= Config.REQUIRE_MONEY.get(currentLevel + 1)) {
                currentMoney -= Config.REQUIRE_MONEY.get(currentLevel + 1);
                Trex.getInstance().setMoney(currentMoney);
                currentLevel += 1;
                Trex.setLevel(currentLevel);
                StatConfig.currentLevel = currentLevel;
                Trex.getInstance().setAtk(GamePlayConfig.TREX_ATK.get(currentLevel));
                Trex.getInstance().setHp(GamePlayConfig.TREX_HP.get(currentLevel));
                Trex.getInstance().setMAX_HP(GamePlayConfig.TREX_HP.get(currentLevel));

                updateUpgradeButton();
                updateStats();
                updateTrex();
                System.out.println("currenWeapon : " + trex.getWeapon().getClass().getSimpleName() + "\n" +
                        "Level : " +  trex.getLevel() + ", Hp : " + trex.getHp()
                        + ", Atk : " + trex.getAtk());
            }
        });
    }

    private void updateStats() {
        Trex trex = GamePlayController.getInstance().getTrex();
        if (currentLevel == StatConfig.MAX_LEVEL) {
            levelTextInstance.setText("LEVEL -> MAX");
        }
        else {
            levelTextInstance.setText("LEVEL -> " + currentLevel);
        }
        atkTextInstance.setText("ATK -> " + GamePlayConfig.TREX_ATK.get(currentLevel));
        hpTextInstance.setText("HP -> " + GamePlayConfig.TREX_HP.get(currentLevel));
    }
    private void updateUpgradeButton() {
        if (currentLevel == StatConfig.MAX_LEVEL) {
            this.getChildren().remove(upgradeButtonInstance);
        }
        else {
            moneyTextInstance.setText(currentMoney + "/" + Config.REQUIRE_MONEY.get(currentLevel + 1));
        }
    }

    private void updateTrex() {
        Trex trex = GamePlayController.getInstance().getTrex();
        if(currentLevel == Config.TREX_CLASS2_LEVEL) {
            trexImageInstance.setImage(new Image(Config.TREX_SWORD_IMAGE_PATH));
            trex.evolve(11);
        }
        else if (currentLevel == Config.TREX_CLASS3_LEVEL) {
            trexImageInstance.setImage(new Image(Config.TREX_GUN_IMAGE_PATH));
            trex.evolve(21);
        }
        else if (currentLevel == Config.TREX_LAST_CLASS_LEVEL) {
            trexImageInstance.setImage(new Image(Config.TREX_FLAME_IMAGE_PATH));
            trex.evolve(StatConfig.MAX_LEVEL);
        }
    }

    private static void setMouseHoverAction(Button button) {
        ScaleTransition mouseIn = new ScaleTransition(Duration.seconds(Config.MOUSE_BUTTON_HOVER_DURATION * 1.2), button);
        mouseIn.setToX(Config.INCREASING_SCALE);
        mouseIn.setToY(Config.INCREASING_SCALE);

        ScaleTransition mouseOut = new ScaleTransition(Duration.seconds(Config.MOUSE_BUTTON_HOVER_DURATION * 1.2), button);
        mouseOut.setToX(1.0);
        mouseOut.setToY(1.0);

        button.setOnMouseEntered(e -> mouseIn.play());
        button.setOnMouseExited(e -> mouseOut.play());
    }

    private ImageView loadButtonImage() {
        ImageView buttonImage = new ImageView(new Image(Config.BUTTON_IMAGE_PATH));
        buttonImage.setFitWidth(Config.UPGRADE_BUTTON_WIDTH);
        buttonImage.setFitHeight(Config.UPGRADE_BUTTON_HEIGHT);
        return buttonImage;
    }

    private VBox createTrexImage() {
        trexImageInstance = new ImageView(new Image(Config.TREX_IMAGE_PATH));
        VBox trexBox = new VBox(Config.TREX_BOX_PADDING);
        trexBox.getChildren().addAll(trexImageInstance);
        trexBox.setAlignment(Pos.CENTER_LEFT);

        return trexBox;
    }

    private VBox createStatBox() {
        VBox statBox = new VBox(Config.STAT_BOX_TEXT_PADDING);
        levelTextInstance = createText(
                "LEVEL -> " + currentLevel , Config.STAT_BOX_TEXT_SIZE, Color.BLACK);
        atkTextInstance = createText(
                "ATK -> " + GamePlayConfig.TREX_ATK.get(currentLevel), Config.STAT_BOX_TEXT_SIZE, Color.BLACK);
        hpTextInstance = createText(
                "HP -> " + GamePlayConfig.TREX_HP.get(currentLevel) , Config.STAT_BOX_TEXT_SIZE, Color.BLACK);

        statBox.getChildren().addAll(levelTextInstance, atkTextInstance, hpTextInstance);
        statBox.setAlignment(Pos.CENTER_RIGHT);
        statBox.setPadding(new Insets(Config.STAT_BOX_PADDING));

        return statBox;
    }

    private Text createText(String string, int size, Color color) {
        Font font  = Font.loadFont(getClass().getResourceAsStream(Config.FONT_PATH), size);
        Text text = new Text(string);
        text.setFont(font);
        text.setFill(color);
        return text;
    }
}
