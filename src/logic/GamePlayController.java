package logic;

import actor.BossMonster;
import actor.Monster;
import actor.Trex;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import utils.GamePlayConfig;
import utils.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GamePlayController {
    private int map;
    private int inGameCoin;
    private static ArrayList<Monster> monsters;
    private static BossMonster bossMonster;
    private boolean isBossSpawned = false;
    private static GamePlayController instance;
    private static Trex trex;

    private Image backgroundImage;

    private long gameStartTime;

    private final Set<KeyCode> pressedKeys;

    private long lastSpawnTime;
    private boolean isWin;

    public GamePlayController(int TrexLevel, int map) {
        GamePlayController.monsters = new ArrayList<>();
        this.pressedKeys = new HashSet<>();
        initGame(TrexLevel, map);
        instance = this;
    }

    public static GamePlayController getInstance() {
        if(instance == null){
            instance = new GamePlayController(1,1);
        }
        return instance;
    }

    public void initGame(int TrexLevel, int map) {
        trex = new Trex(TrexLevel);
        trex.setPos(new Position( GamePlayConfig.PLAYER_SIZE,GamePlayConfig.MAX_Y - GamePlayConfig.PLAYER_SIZE)); // (0,0) ??
        this.setInGameCoin(0);
        this.setMap(map);
        this.backgroundImage = GamePlayConfig.BACKGROUND_IMAGE.get(map);
        System.out.println("map: "+map);
    }

    public void update() {
        trex.move(pressedKeys);
        if(bossMonster != null) {
            bossMonster.move();}
        long currentTime = System.nanoTime();
        if (currentTime - lastSpawnTime >= GamePlayConfig.SPAWN_RATE) {
            lastSpawnTime = currentTime;
            if (Math.random() < GamePlayConfig.SPAWN_PROBABILITY) {
                spawnMonster();
            }
        }
        for (int i = 0; i < monsters.size(); i++) {
            Monster monster = monsters.get(i);
            monster.move();
            if(monster.isOutOfRange()){
                monsters.remove(i);
                i--;
            }
            else if(monster.checkCollide(trex)){
                monster.collideAction(trex);
                monsters.remove(i);
                if(trex.getHp() <= 0){
                    System.out.println("Game over");
                }
            }
        }
    }


    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public int getInGameCoin() {
        return inGameCoin;
    }

    public void setInGameCoin(int inGameCoin) {
        this.inGameCoin = inGameCoin;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void handleKeyPress(KeyCode keyCode) {
        pressedKeys.add(keyCode);
    }

    public void handleKeyRelease(KeyCode keyCode) {
        pressedKeys.remove(keyCode);
    }


    public long getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(long gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public Image getBackgroundImage(){
        return backgroundImage;
    }

    public void spawnMonster() {
        Monster monster;
        spawnBoss();
        if (0.5 > Math.random()) {
            monster = new Monster(GamePlayConfig.MONSTER_HP.get(map), GamePlayConfig.MONSTER_ATK.get(map), GamePlayConfig.MONSTER_COST.get(map), new Image("monster/monster_1_1_run.gif"));
            monster.getPos().setX(GamePlayConfig.WIDTH);
            monster.getPos().setY(GamePlayConfig.MAX_Y - GamePlayConfig.MONSTER_SIZE + 50);
        } else {
            monster = new Monster(GamePlayConfig.MONSTER_HP.get(map), GamePlayConfig.MONSTER_ATK.get(map), GamePlayConfig.MONSTER_COST.get(map), new Image("monster/monster_1_2_run.gif"));
            monster.getPos().setX(GamePlayConfig.WIDTH);
            monster.getPos().setY(GamePlayConfig.MAX_Y - GamePlayConfig.MONSTER_SIZE - 35);
        }
        monsters.add(monster);

    }

    private void spawnBoss(){
        long elapsedTime = System.currentTimeMillis() - this.getGameStartTime();
        long remainingTime = GamePlayConfig.TIME_LIMIT - elapsedTime;
        if(remainingTime / GamePlayConfig.TIME_DIVIDE <= GamePlayConfig.BOSS_SPAWN_TIME && !isBossSpawned){
            bossMonster = new BossMonster(GamePlayConfig.BOSS_HP.get(map),GamePlayConfig.BOSS_ATK.get(map),GamePlayConfig.BOSS_COST.get(map));
            isBossSpawned = !isBossSpawned;
        }

    }
    public boolean isGameOver() {
        long elapsedTime = System.currentTimeMillis() - this.getGameStartTime();
        long remainingTime = GamePlayConfig.TIME_LIMIT - elapsedTime;
        return trex.getHp() <= 0 || remainingTime <= 0;
    }

    public Trex getTrex(){
        return trex;
    }
    public BossMonster getBossMonster(){
        return bossMonster;
    }

    public void clearBossMonster(){
        bossMonster = null;
        isBossSpawned = false;
        isWin = false;
    }

    public boolean isWin(){
        return isWin;
    }

    public void setIsWin(boolean isWin){
        this.isWin = isWin;
    }

}
