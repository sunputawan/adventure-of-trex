package utils;



import base.BaseWeapon;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import weapon.Fist;
import weapon.Flame;
import weapon.Gun;
import weapon.Sword;

import java.util.ArrayList;

public class GamePlayConfig {

    public static final double PLAYER_SPEED = 6;
    public static final double PLAYER_JUMP_SPEED = 10;
    public static final double GRAVITY = 0.4;
    public static final double FAST_GRAVITY = 2.0;
    public static final int RANGE_OF_LEVEL =10;
    public static final double PLAYER_ATTACK_COOLDOWN = 250_000_000;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int MAX_Y = 650;
    public static final int BLANK_AREA = 100;
    public static final int BLANK_AREA_BOSS = 300;
    public static final double PLAYER_SIZE = 250;
    public static final double MONSTER_SIZE = 300;
    public static final double MONSTER_SPEED = 8;
    public static final double SPAWN_RATE = 1_000_000_000; // 1 second in nanoseconds
    public static final double SPAWN_PROBABILITY = 0.75; // Probability of spawning a monster in each spawn interval
    public static final long TIME_LIMIT = 60000 * 1; // Time limit in milliseconds (1 minute)

    public static  final long ANIMATION_TIME = 250_000_000;

    public static final Image COIN_IMAGE = new Image("utils/coin.gif");

    public static final Position COIN_IMAGE_POSITION = new Position(WIDTH / 2 - 130, 300);

    public static final double COIN_IMAGE_WIDTH = 175;
    public static final double COINT_IMAGE_HEIGHT = 75;
    public static final Image GAME_OVER_IMAGE = new Image("utils/game_over.png");

    public static final Position GAME_OVER_IMAGE_POSITION = new Position(WIDTH / 2 - 196, 100);

    public static final double GAME_OVER_IMAGE_WIDTH = 392;
    public static final double GAME_OVER_IMAGE_HEIGHT = 52;
    public static final Image CLICK_TO_CONTINUE_IMAGE = new Image("utils/CLICK-TO-CONTINUE.png");
    public static final Position CLICK_TO_COTINUE_IMAGE_POSITION = new Position(WIDTH / 2 - 175, 520);
    public static final double CLICK_TO_CONTINUE_IMAGE_WIDTH = 350.5;
    public static final double CLICK_TO_CONTINUE_IMAGE_HEIGHT = 23.5;
    public static final Image TOTAL_COINS_IMAGE = new Image("utils/Total-coins.png");
    public static final Position TOTAL_COINS_IMAGE_POSITION = new Position(WIDTH / 2 - 119, 250);
    public static final double TOTAL_COINS_IMAGE_WIDTH = 238.125;
    public static final double TOTAL_COINS_IMAGE_HEIGHT = 33.125;

    public static final Position TOTAL_COINS_TEXT_POSITION = new Position(WIDTH/2+ 10, 350);

    public static final Font FONT_TEMPLATE = Font.loadFont(ClassLoader.getSystemResource("font/font.ttf").toString(), 24);

    public static final int MAP_LIMIT = 7;

    public static final Position TIME_POSITION = new Position(WIDTH / 2 - 90, 65);
    public static final Position PROGRESSBAR_POSITION = new Position(WIDTH / 2 - 300,HEIGHT - 70);
    public static final double PROGRESSBAR_WIDTH = 600;
    public static final double PROGRESSBAR_HEIGHT = 40;

    public static final Position RED_FLAG_POSITION = new Position(WIDTH / 2+185,HEIGHT - 145);
    public static final double RED_FLAG_SIZE =  150;

    public static final double MAX_PROGRESSBAR = 500;
    public static final Position CURRENT_PROGRESSBAR = new Position(390, HEIGHT-60);
    public static final double CURRENT_PROGRESSBAR_WIDTH = 20;

    public static final int TIME_DIVIDE = 1000;

    public static final Position COIN_POSITION = new Position(-25,100);
    public static final double COIN_WIDTH = 175;
    public static final double COIN_HEIGHT = 75;

    public static final Position COIN_COUNT_POSITION = new Position(100,150);

    public static final Position HP_BAR_POSITION = new Position(10,10);
    public static final double HP_BAR_WIDTH = 300;
    public static final double HP_BAR_HEIGHT = 75;
    public static final double  MAX_HP_BAR = 180;
    public static final Position CURRENT_HP_POSITION = new Position(97,38);
    public static final double CURRENT_HP_HEIGHT = 20;

    public static final double BOSS_SIZE = 750;
    public static final Position BOSS_POSITION = new Position(WIDTH-BOSS_SIZE + 100,MAX_Y-BOSS_SIZE + 220 );

    public static final int BOSS_SPAWN_TIME = 55; // second
    public static final long BOSS_ATTACK_COOLDOWN = 1_000_000_000;
    public static final Image BOSS_IMAGE = new Image("monster/Boss_1.gif");
    public static final Image BOSS_ATTACK_EFFECT = new Image("monster/Boss_1_attack.gif");
    public static final int BOSS_ATTACK_COOLDOWN_SEC = 3;

    public static final Image VICTORY_IMAGE = new Image("utils/Victory.png");
    public static final double VICTORY_IMAGE_WIDTH = 336;
    public static final double VICTORY_IMAGE_HEIGHT = 82;

    public static ArrayList<Integer> TREX_HP = new ArrayList<Integer>(){{ //TREX_HP[Trex.getLevel()]
        add(null);
        for (int i=100;i<=400;i+=10) { // 100, 110, 120, ..., 190 (Lv.1 - Lv.10)
            add(i);
        }
    }};
    public static ArrayList<Integer> TREX_ATK = new ArrayList<Integer>(){{ //TREX_ATK[Trex.getLevel()]
        add(null);
        for (int i=10; i<=70; i+=2) { // 10, 12, 14, ..., 28 (Lv.1 - Lv.10)
            add(i);
        }
    }};

    public static ArrayList<Image> BACKGROUND_IMAGE = new ArrayList<Image>() {{
        add(null);
        add(new Image("map/map_1.png"));
        add(new Image("map/map_2.png"));
        add(new Image("map/map_3.png"));
        add(new Image("map/map_4.png"));
        add(new Image("map/map_5.png"));
        add(new Image("map/map_6.png"));
        add(new Image("map/map_7.png"));
    }};

    public static ArrayList<Integer> MONSTER_HP = new ArrayList<Integer>() {{
        add(null);
        add(10);
        add(15);
        add(20);
        add(30);
        add(40);
        add(50);
        add(60);
    }};

    public static ArrayList<Integer> MONSTER_ATK = new ArrayList<Integer>() {{
        add(null);
        add(30);
        add(40);
        add(50);
        add(60);
        add(70);
        add(80);
        add(90);
    }};

    public static ArrayList<Integer> MONSTER_COST = new ArrayList<Integer>() {{
        add(null);
        add(100);
        add(150);
        add(200);
        add(250);
        add(300);
        add(350);
        add(400);
    }};

    public static ArrayList<Integer> BOSS_HP = new ArrayList<Integer>() {{
        int hpRate = 15;
        add(null);
        add(10 * hpRate);
        add(15 * hpRate);
        add(20 * hpRate);
        add(30 * hpRate);
        add(40 * hpRate);
        add(50 * hpRate);
        add(60 * hpRate);
    }};

    public static ArrayList<Integer> BOSS_ATK = new ArrayList<Integer>() {{
        int atkRate = 3;
        add(null);
        add(30 * atkRate);
        add(40 * atkRate);
        add(50 * atkRate);
        add(60 * atkRate);
        add(70 * atkRate);
        add(80 * atkRate);
        add(90 * atkRate);
    }};

    public static ArrayList<Integer> BOSS_COST = new ArrayList<Integer>() {{
        int costRate = 5;
        add(null);
        add(100 * costRate);
        add(150 * costRate);
        add(200 * costRate);
        add(250 * costRate);
        add(300 * costRate);
        add(350 * costRate);
        add(400 * costRate);
    }};


    public static ArrayList<BaseWeapon> WEAPON = new ArrayList<BaseWeapon>() {{
        add(Fist.getInstance());
        add(Sword.getInstance());
        add(Gun.getInstance());
        add(Flame.getInstance());
    }};

}
