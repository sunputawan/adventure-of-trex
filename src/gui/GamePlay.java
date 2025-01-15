package gui;

import actor.BossMonster;
import actor.Monster;
import actor.Trex;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.GamePlayController;
import utils.GamePlayConfig;

public class GamePlay {

    private Image coinImage;
    private Image gameOver;
    private Image victoryImage;
    private Font pressStartFont;

    private Image clickToContinue;
    private Image totalCoins;

    private MediaPlayer backgroundMusic;
    private MediaPlayer gameOverSound;
    private boolean isGameOverSoundPlayed = false;
    private boolean isRunning = true;

    public GamePlay(Stage primaryStage){
        Canvas canvas = new Canvas(GamePlayConfig.WIDTH, GamePlayConfig.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        initGraphic(gc,canvas);
        StackPane root = new StackPane(canvas);
        Goto.getRootPane().getChildren().clear();
        Goto.getRootPane().getChildren().addAll(root);
        drawAll(gc);// Initial draw

        canvas.requestFocus();// Request focus on canvas to capture key events
        backgroundMusic = new MediaPlayer(new Media( ClassLoader.getSystemResource("utils/backgroundMusic1.mp3").toString()));
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.play();
        gameOverSound = new MediaPlayer(new Media( ClassLoader.getSystemResource("utils/gameOverSound.mp3").toString()));
        gameOverSound.setCycleCount(1);
        // Game loop
        handleAnimationTimer(gc);

    }
    private void initGraphic(GraphicsContext gc,Canvas canvas){
        gc.setFill(Color.GRAY);
        gc.fillRect(0, 0, GamePlayConfig.WIDTH,  GamePlayConfig.HEIGHT);
        initUtils();
        GamePlayController.getInstance().setGameStartTime(System.currentTimeMillis());
        canvas.setOnKeyPressed(event -> {        // Handle key events
            GamePlayController.getInstance().handleKeyPress(event.getCode());
        });
        canvas.setOnKeyReleased(event -> {
            GamePlayController.getInstance().handleKeyRelease(event.getCode());
        });
        canvas.setOnMouseClicked(event -> {
            if(GamePlayController.getInstance().isGameOver() || GamePlayController.getInstance().isWin()){
                isRunning = false;
                Trex.setMoney(Trex.getMoney() + GamePlayController.getInstance().getInGameCoin());
                Goto.menuPage();
            }
        });
    }
    private void handleAnimationTimer(GraphicsContext gc){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(isRunning){
                    gc.setFont(pressStartFont);
                    if(!GamePlayController.getInstance().isGameOver() && !GamePlayController.getInstance().isWin()){
                        GamePlayController.getInstance().update();
                        drawAll(gc);
                    }else if(GamePlayController.getInstance().isWin()){
                        victoryScene(gc);
                    }
                    else{
                        if(!isGameOverSoundPlayed){
                            gameOverSound.play();
                            isGameOverSoundPlayed = !isGameOverSoundPlayed;
                        }
                        endScene(gc);
                    }
                }
                else{this.stop();}}};
        timer.start();}

    public void initUtils(){
        coinImage = GamePlayConfig.COIN_IMAGE;
        gameOver = GamePlayConfig.GAME_OVER_IMAGE;
        clickToContinue = GamePlayConfig.CLICK_TO_CONTINUE_IMAGE;
        totalCoins = GamePlayConfig.TOTAL_COINS_IMAGE;
        pressStartFont  = GamePlayConfig.FONT_TEMPLATE;
        victoryImage = GamePlayConfig.VICTORY_IMAGE;
    }

    public void drawAll(GraphicsContext gc) {
        gc.clearRect(0, 0, GamePlayConfig.WIDTH, GamePlayConfig.HEIGHT);
        gc.drawImage( GamePlayController.getInstance().getBackgroundImage(), 0, 0, GamePlayConfig.WIDTH, GamePlayConfig.HEIGHT);
        drawBoss(gc);
        drawTrex(gc);

        drawMonster(gc);
        gc.setFill(Color.WHITE);
        drawHpBar(gc);

        long elapsedTime = System.currentTimeMillis() - GamePlayController.getInstance().getGameStartTime();
        long remainingTime = GamePlayConfig.TIME_LIMIT - elapsedTime;
        drawTimer(gc,elapsedTime,remainingTime);
        drawProgressBar(gc,elapsedTime,remainingTime);
    }

    public void drawBoss(GraphicsContext gc){
        BossMonster bossMonster = GamePlayController.getInstance().getBossMonster();
        if(bossMonster != null && bossMonster.getHp() > 0){
            double tx = bossMonster.getPos().getX();
            double ty = bossMonster.getPos().getY();
            if (bossMonster.isAttacking()) {
                gc.drawImage(GamePlayConfig.BOSS_ATTACK_EFFECT, tx - GamePlayConfig.BLANK_AREA, ty, GamePlayConfig.BOSS_SIZE , GamePlayConfig.BOSS_SIZE);
            }
            gc.drawImage(GamePlayConfig.BOSS_IMAGE, tx, ty, GamePlayConfig.BOSS_SIZE , GamePlayConfig.BOSS_SIZE);
        }

    }


    public void drawTrex(GraphicsContext gc){
        Trex trex = GamePlayController.getInstance().getTrex();
        double tx = trex.getPos().getX();
        double ty = trex.getPos().getY();
        if (trex.isAttacking()) {
            gc.drawImage(trex.getWeapon().getAttackEffectImage(), tx + trex.getWeapon().getRange(), ty, GamePlayConfig.PLAYER_SIZE , GamePlayConfig.PLAYER_SIZE);
        }
        if(trex.isCrouching()){
            gc.drawImage(trex.getWeapon().getCrouchingImage(), tx, ty, GamePlayConfig.PLAYER_SIZE , GamePlayConfig.PLAYER_SIZE);
        }else {
            gc.drawImage(trex.getWeapon().getRunImage(), tx, ty, GamePlayConfig.PLAYER_SIZE , GamePlayConfig.PLAYER_SIZE);
        }
    }
    public void drawMonster(GraphicsContext gc){
        for (Monster monster : GamePlayController.getInstance().getMonsters()) {
            gc.drawImage(monster.getShowImage(),monster.getPos().getX(), monster.getPos().getY(), GamePlayConfig.MONSTER_SIZE , GamePlayConfig.MONSTER_SIZE);
        }
    }

    public void drawTimer(GraphicsContext gc,long elapsedTime,long remainingTime){
        if (remainingTime <= 0 || GamePlayController.getInstance().isGameOver()) {
            // Game over
            // You can add game over logic here
        }else{
            gc.setFill(Color.WHITE);
            String time = "Time: " + remainingTime / GamePlayConfig.TIME_DIVIDE + "s";
            gc.fillText(time, GamePlayConfig.TIME_POSITION.getX(),  GamePlayConfig.TIME_POSITION.getY());
        }
    }
    public void drawProgressBar(GraphicsContext gc,long elapsedTime,long remainingTime){
        // Draw remaining time
        gc.drawImage(new Image("utils/progressbar.png"),GamePlayConfig.PROGRESSBAR_POSITION.getX(), GamePlayConfig.PROGRESSBAR_POSITION.getY(),GamePlayConfig.PROGRESSBAR_WIDTH,GamePlayConfig.PROGRESSBAR_HEIGHT);
        gc.drawImage(new Image("utils/redflag.png"),GamePlayConfig.RED_FLAG_POSITION.getX(),GamePlayConfig.RED_FLAG_POSITION.getY(),GamePlayConfig.RED_FLAG_SIZE,GamePlayConfig.RED_FLAG_SIZE);
        double progressbar = ((double) elapsedTime / GamePlayConfig.TIME_LIMIT <= 1 ? (double) elapsedTime / GamePlayConfig.TIME_LIMIT : 1 )  * GamePlayConfig.MAX_PROGRESSBAR;
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(GamePlayConfig.CURRENT_PROGRESSBAR.getX(),GamePlayConfig.CURRENT_PROGRESSBAR.getY(), progressbar, GamePlayConfig.CURRENT_PROGRESSBAR_WIDTH);
    }
    public void drawHpBar(GraphicsContext gc){
        Trex trex = GamePlayController.getInstance().getTrex();
        gc.drawImage(coinImage,GamePlayConfig.COIN_POSITION.getX(),GamePlayConfig.COIN_POSITION.getY(),GamePlayConfig.COIN_WIDTH,GamePlayConfig.COIN_HEIGHT);
        gc.fillText("x" + GamePlayController.getInstance().getInGameCoin(), GamePlayConfig.COIN_COUNT_POSITION.getX(),GamePlayConfig.COIN_COUNT_POSITION.getY());
        gc.drawImage(new Image("utils/hp_bar.png"),GamePlayConfig.HP_BAR_POSITION.getX(),GamePlayConfig.HP_BAR_POSITION.getY(),GamePlayConfig.HP_BAR_WIDTH,GamePlayConfig.HP_BAR_HEIGHT);
        double hpBarWidth = (double) trex.getHp() / trex.getMAX_HP() * GamePlayConfig.MAX_HP_BAR;
        //System.out.println("Hp:" + trex.getHp() + "MaxHp: "+ trex.getMAX_HP());
        gc.setFill(Color.RED);
        gc.fillRect(GamePlayConfig.CURRENT_HP_POSITION.getX(), GamePlayConfig.CURRENT_HP_POSITION.getY(), hpBarWidth, GamePlayConfig.CURRENT_HP_HEIGHT);
    }

    public void endScene(GraphicsContext gc){
        backgroundMusic.stop();
        drawAll(gc);
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.85);
        gc.fillRect(0, 0,  GamePlayConfig.WIDTH, GamePlayConfig.HEIGHT);
        gc.setGlobalAlpha(1);
        gc.setFill(Color.WHITE);
        gc.drawImage(gameOver, GamePlayConfig.GAME_OVER_IMAGE_POSITION.getX(),GamePlayConfig.GAME_OVER_IMAGE_POSITION.getY(),GamePlayConfig.GAME_OVER_IMAGE_WIDTH,GamePlayConfig.GAME_OVER_IMAGE_HEIGHT);
        gc.drawImage(coinImage,GamePlayConfig.COIN_IMAGE_POSITION.getX(),GamePlayConfig.COIN_IMAGE_POSITION.getY(),GamePlayConfig.COIN_IMAGE_WIDTH,GamePlayConfig.COINT_IMAGE_HEIGHT);
        gc.drawImage(totalCoins,GamePlayConfig.TOTAL_COINS_IMAGE_POSITION.getX(), GamePlayConfig.TOTAL_COINS_IMAGE_POSITION.getY(),GamePlayConfig.TOTAL_COINS_IMAGE_WIDTH,GamePlayConfig.TOTAL_COINS_IMAGE_HEIGHT);
        gc.fillText("x" + GamePlayController.getInstance().getInGameCoin(), GamePlayConfig.TOTAL_COINS_TEXT_POSITION.getX(),GamePlayConfig.TOTAL_COINS_TEXT_POSITION.getY());
        gc.drawImage(clickToContinue,GamePlayConfig.CLICK_TO_COTINUE_IMAGE_POSITION.getX(),GamePlayConfig.CLICK_TO_COTINUE_IMAGE_POSITION.getY(),GamePlayConfig.CLICK_TO_CONTINUE_IMAGE_WIDTH,GamePlayConfig.CLICK_TO_CONTINUE_IMAGE_HEIGHT);
    }
    public void victoryScene(GraphicsContext gc){
        backgroundMusic.stop();
        drawAll(gc);
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.85);
        gc.fillRect(0, 0,  GamePlayConfig.WIDTH, GamePlayConfig.HEIGHT);
        gc.setGlobalAlpha(1);
        gc.setFill(Color.WHITE);
        gc.drawImage(victoryImage, (GamePlayConfig.WIDTH - victoryImage.getWidth())/2,GamePlayConfig.GAME_OVER_IMAGE_POSITION.getY(),GamePlayConfig.VICTORY_IMAGE_WIDTH,GamePlayConfig.VICTORY_IMAGE_HEIGHT);
        gc.drawImage(coinImage,GamePlayConfig.COIN_IMAGE_POSITION.getX(),GamePlayConfig.COIN_IMAGE_POSITION.getY(),GamePlayConfig.COIN_IMAGE_WIDTH,GamePlayConfig.COINT_IMAGE_HEIGHT);
        gc.drawImage(totalCoins,GamePlayConfig.TOTAL_COINS_IMAGE_POSITION.getX(), GamePlayConfig.TOTAL_COINS_IMAGE_POSITION.getY(),GamePlayConfig.TOTAL_COINS_IMAGE_WIDTH,GamePlayConfig.TOTAL_COINS_IMAGE_HEIGHT);
        gc.fillText("x" + GamePlayController.getInstance().getInGameCoin(), GamePlayConfig.TOTAL_COINS_TEXT_POSITION.getX(),GamePlayConfig.TOTAL_COINS_TEXT_POSITION.getY());
        gc.drawImage(clickToContinue,GamePlayConfig.CLICK_TO_COTINUE_IMAGE_POSITION.getX(),GamePlayConfig.CLICK_TO_COTINUE_IMAGE_POSITION.getY(),GamePlayConfig.CLICK_TO_CONTINUE_IMAGE_WIDTH,GamePlayConfig.CLICK_TO_CONTINUE_IMAGE_HEIGHT);
    }
}
