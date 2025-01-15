package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {
    public static final int TREX_CLASS2_LEVEL = 10;
    public static final int TREX_CLASS3_LEVEL = 20;
    public static final int TREX_LAST_CLASS_LEVEL = 30;
    public static final int STAT_BOX_TEXT_SIZE = 25;
    public static final int STAT_BOX_TEXT_PADDING = 30;
    public static final int STAT_BOX_PADDING= 30;
    public static final int UPGRADE_BUTTON_WIDTH = 300;
    public static final int UPGRADE_BUTTON_HEIGHT = 100;
    public static final int UPGRADE_PAGE_UPGRADE_TEXT_SIZE = 25;
    public static final int UPGRADE_PAGE_MONEY_TEXT_SIZE = 20;
    public static final int UPGRADE_PANE_WIDTH = 1000;
    public static final int UPGRADE_PANE_HEIGHT = 500;
    public static final int IMAGE_AND_STAT_BOX_PADDING = 50;
    public static final int TREX_BOX_PADDING = 30;
    public static final int UPGRADE_TEXT_AND_MONEY_PADDING = 5;
    public static final int MAP_SELECT_PAGE_TITLE_IMAGE_VIEW_MARGIN = 20;
    public static final int MAP_SELECT_PAGE_MIDDLE_HBOX_MARGIN = 10;
    public static final double MOUSE_BUTTON_HOVER_DURATION = 0.05;
    public static final String FONT_PATH = "/font/font.ttf";
    public static final String TREX_IMAGE_PATH = "trex/Trex_run.gif";
    public static final String TREX_SWORD_IMAGE_PATH = "trex/Trex_sword_run.gif";
    public static final String TREX_GUN_IMAGE_PATH = "trex/Trex_gun_run.gif";
    public static final String TREX_FLAME_IMAGE_PATH = "trex/Trex_run.gif";
    public static final ArrayList<Integer> REQUIRE_MONEY = new ArrayList<>(Arrays.asList(
            null, null,
            200, 400, 600, 800, 1000, 1200, 1400, 1600, 1800, 2000,
            2200, 2400, 2600, 2800, 3000, 3200, 3400, 3600, 3800, 4000,
            4200, 4400, 4600, 4800, 5000, 6200, 6400, 6600, 6800, 7000
    ));
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String BACKGROUND_IMAGE_PATH = "mainPage/background2.gif";
    public static final String TITLE_IMAGE_PATH = "mainPage/Adventure-of-TREX.png";
    public static final String CLICK_IMAGE_PATH = "mainPage/CLICK-TO-CONTINUE.png";
    public static final String BUTTON_IMAGE_PATH = "menuPage/button.png";
    public static final String PLAY_IMAGE_PATH = "menuPage/play.png";
    public static final String UPGRADE_IMAGE_PATH = "menuPage/upgrade.png";
    public static final String GUIDE_IMAGE_PATH = "menuPage/guide.png";
    public static final String GUIDE_LINK = "https://docs.google.com/document/d/1fKeNqsBZQcjTq1Mvb4l2D0Fj0t4HPM2I3zPDHvCPAjU/edit";
    public static final String EXIT_IMAGE_PATH = "menuPage/exit.png";
    public static final String MAP_SELECT_TEXT_IMAGE_PATH = "mapSelectPage/MAP-SELECT.png";
    public static final int TITLE_WIDTH = 480;
    public static final int TITLE_HEIGHT = 250;
    public static final int MAIN_PAGE_PADDING = 20;
    public static final int CLICK_WIDTH = 550;
    public static final int CLICK_HEIGHT = 30;
    public static final double CLICK_OPACITY = 0.6;
    public static final double BLINKING_DURATION = 0.5;
    public static final int PLAY_TEXT_WIDTH = 150;
    public static final int UPGRADE_TEXT_WIDTH = 250;
    public static final int GUIDE_TEXT_WIDTH = 180;
    public static final int EXIT_TEXT_WIDTH = 120;
    public static final int TEXT_HEIGHT = 35;
    public static final int BUTTON_WIDTH = 400;
    public static final int BUTTON_HEIGHT = 120;
    public static final double INCREASING_SCALE = 1.05;
    public static final int MAP_SELECT_WIDTH = 400;
    public static final int MAP_SELECT_HEIGHT = 50;
    public static final int MAP_SELECT_PADDING = 10;
    public static final int MAP_SELECT_BUTTON_WIDTH = 280;
    public static final int MAP_SELECT_BUTTON_HEIGHT = 140;

    public static ArrayList<String> MAP_SELECT_TEXT_PATH = new ArrayList<>(Arrays.asList(
            null,
            "mapSelectPage/text1.png",
            "mapSelectPage/text2.png",
            "mapSelectPage/text3.png",
            "mapSelectPage/text4.png",
            "mapSelectPage/text5.png",
            "mapSelectPage/text6.png",
            "mapSelectPage/text7.png"
    ));

    public static ArrayList<String> MAP_SELECT_IMAGE_PATH = new ArrayList<>(Arrays.asList(
            null,
            "map/map_1.png",
            "map/map_2.png",
            "map/map_3.png",
            "map/map_4.png",
            "map/map_5.png",
            "map/map_6.png",
            "map/map_7.png"
    ));

    public static ArrayList<Integer> MAP_SELECT_MAP_TEXT_WIDTH = new ArrayList<>(Arrays.asList(
            null, 280, 280, 280, 280, 280, 280, 280
    ));

    public static ArrayList<Integer> MAP_SELECT_MAP_TEXT_HEIGHT = new ArrayList<>(Arrays.asList(
            null, 28, 31, 25, 26, 26, 26, 31
    ));

    public static final double MAP_SELECT_INCREASING_SCALE = 1.1;
    public static final String BACK_BUTTON_TEXT_PATH = "GotoPage/BACK.png";
    public static final String START_BUTTON_TEXT_PATH = "GotoPage/START.png";
    public static final int BACK_BUTTON_TEXT_WIDTH = 130;
    public static final int BACK_BUTTON_TEXT_HEIGHT = 30;
    public static final int START_BUTTON_TEXT_WIDTH = 150;
    public static final int START_BUTTON_TEXT_HEIGHT = 30;

    public static final int GOTO_BUTTON_IMAGE_WIDTH = 180;
    public static final int GOTO_BUTTON_IMAGE_HEIGHT = 70;
    public static final int BUTTON_BOX_PADDING = 750;
}
