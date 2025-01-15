package actor;

import base.BaseActor;
import base.BaseCharacter;
import base.BaseWeapon;
import javafx.scene.input.KeyCode;
import logic.GamePlayController;
import usage.Movable;
import utils.GamePlayConfig;
import utils.Position;
import utils.StatConfig;

import java.util.Set;

public class Trex extends BaseCharacter implements Movable {
    private static int level = StatConfig.currentLevel;
    private static int money = StatConfig.currentMoney;
    private boolean isJumping = false;
    private boolean isCrouching = false;
    private boolean isAttacking = false;
    private long lastAttackTime = 0;
    private double playerYSpeed = 0;
    private static Trex instance;
    private BaseWeapon weapon;

    public static Trex getInstance() {
        if (instance == null) {
            instance = new Trex(level);
        }
        return instance;
    }

    public Trex(int level) {
        setLevel(level);
        setMAX_HP(GamePlayConfig.TREX_HP.get(level));
        setHp(GamePlayConfig.TREX_HP.get(level));
        setAtk(GamePlayConfig.TREX_ATK.get(level));
        setWeapon(GamePlayConfig.WEAPON.get(level/10));
    }
    public void move(Set<KeyCode> pressedKeys){
        moveLeftAndRight(pressedKeys);
        if (isJumping) {
            this.setPlayerYSpeed(this.getPlayerYSpeed() + (this.isCrouching ? 2.0: GamePlayConfig.GRAVITY));
            this.move(0, this.getPlayerYSpeed());
            if (this.getPos().getY() >= GamePlayConfig.MAX_Y - GamePlayConfig.PLAYER_SIZE) {
                this.getPos().setY(GamePlayConfig.MAX_Y- GamePlayConfig.PLAYER_SIZE);
                this.setPlayerYSpeed(0);
                this.setJumping(false);
            }
        }
       handleCrouching(pressedKeys);
        if (pressedKeys.contains(KeyCode.W) && !isJumping && !isCrouching) {
            this.setJumping(true);
            this.setPlayerYSpeed( -GamePlayConfig.PLAYER_JUMP_SPEED);
        }
        attackAllmonster(pressedKeys);
        handleAttackCooldown();

    }
    private void handleCrouching(Set<KeyCode> pressedKeys) {
        if (isCrouching && !pressedKeys.contains(KeyCode.S)) {
            this.setCrouching(false);
        }
        if (pressedKeys.contains(KeyCode.S) && !isCrouching) {
            this.setCrouching(true);
        }
    }

    private void handleAttackCooldown(){
        if (isAttacking) {
            long elapsedTime = System.nanoTime() - lastAttackTime;
            if (elapsedTime < 250_000_000) { // Show attack animation for 500 milisecond
                // Draw attack animation
                this.setShowImage(weapon.getAttackAnimationImage()); ;

            } else {
                // End attack animation
                isAttacking = false;
                this.setShowImage(weapon.getRunImage());
            }
        }
    }
    private void moveLeftAndRight(Set<KeyCode> pressedKeys){
        if (pressedKeys.contains(KeyCode.A) && this.getPos().getX() > 0) {
            this.move(- GamePlayConfig.PLAYER_SPEED,0);
        }
        if (pressedKeys.contains(KeyCode.D) && this.getPos().getX() < GamePlayConfig.WIDTH - GamePlayConfig.PLAYER_SIZE) {
            this.move(GamePlayConfig.PLAYER_SPEED,0);
        }
    }

    private boolean isCanAttack(BaseActor monster) {
        double tx = this.getPos().getX();
        double ty = this.getPos().getY();
        boolean canAttack;
        if(monster instanceof Monster){
            monster = ((Monster) monster);
            canAttack = tx + GamePlayConfig.PLAYER_SIZE < monster.getPos().getX() + GamePlayConfig.MONSTER_SIZE &&
                    tx + GamePlayConfig.PLAYER_SIZE - GamePlayConfig.BLANK_AREA + this.getWeapon().getRange() > monster.getPos().getX() && ty < monster.getPos().getY() + GamePlayConfig.MONSTER_SIZE && ty + GamePlayConfig.PLAYER_SIZE > monster.getPos().getY();
        } else if (monster instanceof BossMonster) {
            monster = ((BossMonster) monster);
            canAttack = tx + GamePlayConfig.PLAYER_SIZE < monster.getPos().getX() + GamePlayConfig.BOSS_SIZE &&
                    tx + GamePlayConfig.PLAYER_SIZE - GamePlayConfig.BLANK_AREA + this.getWeapon().getRange() > monster.getPos().getX() &&
                    ty < monster.getPos().getY() + GamePlayConfig.BOSS_SIZE &&
                    ty + GamePlayConfig.PLAYER_SIZE > monster.getPos().getY();
        }
        else{return false;}
        return canAttack;
    }

    private void attackAllmonster(Set<KeyCode> pressedKeys){
        if (pressedKeys.contains(KeyCode.SPACE) && !isAttacking) {
            long currentTime = System.nanoTime();
            if (currentTime - lastAttackTime >= GamePlayConfig.PLAYER_ATTACK_COOLDOWN) {
                isAttacking = true;
                this.setLastAttackTime(currentTime);
                attackBoss();
                for (int i = 0; i < GamePlayController.getInstance().getMonsters().size(); i++) {
                    Monster monster = GamePlayController.getInstance().getMonsters().get(i);
                    if (isCanAttack(monster)) {
                        // Collision detected
                        this.attack(monster); // Decrease monster's HP
                        if (monster.getHp() <= 0) {
                            // Remove monster if its HP falls below or equal to 0
                            GamePlayController.getInstance().getMonsters().remove(i);
                            i--; // Decrement index since size of list changes
                            GamePlayController.getInstance().setInGameCoin(GamePlayController.getInstance().getInGameCoin() + monster.getCost()); ;
                        }
                    }
                }
            }
        }
    }

    private void attackBoss(){
        BossMonster bossMonster = GamePlayController.getInstance().getBossMonster();
        if(bossMonster != null){
            if(isCanAttack(bossMonster)){
//                System.out.println("wow2");
                this.attack(bossMonster);
                if (bossMonster.getHp() <= 0){
                    GamePlayController.getInstance().setInGameCoin(GamePlayController.getInstance().getInGameCoin() + bossMonster.getCost()); ;
                    GamePlayController.getInstance().setIsWin(true);
//                    System.out.println("Victory!!!");
                }
            }
        }
    }

    public void evolve(int currentLevel) {
        this.setWeapon(GamePlayConfig.WEAPON.get(currentLevel / 10));
    }

    @Override
    public void move(double x, double y) {
        double currentX = this.getPos().getX();
        double currentY = this.getPos().getY();
        this.setPos(new Position(
                Math.max(0, currentX + x),
                Math.max(0, currentY + y)
        ));
    }

    public BaseWeapon getWeapon() {
        return weapon;
    }

    public void setWeapon(BaseWeapon weapon) {
        this.weapon = weapon;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Trex.level = level;
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        Trex.money = money;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isCrouching() {
        return isCrouching;
    }

    public void setCrouching(boolean crouching) {
        isCrouching = crouching;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public long getLastAttackTime() {
        return lastAttackTime;
    }

    public void setLastAttackTime(long lastAttackTime) {
        this.lastAttackTime = lastAttackTime;
    }

    public double getPlayerYSpeed() {
        return playerYSpeed;
    }

    public void setPlayerYSpeed(double playerYSpeed) {
        this.playerYSpeed = playerYSpeed;
    }

    @Override
    public void attack(BaseActor actor){
        if (actor instanceof Monster) {
            ((Monster) actor).getDamage(this.getAtk());
        }
        else if(actor instanceof BossMonster){
            ((BossMonster) actor).getDamage(this.getAtk());
        }
    }
    @Override
    public void getDamage(int atk){
        this.setHp(Math.max(0, this.getHp() - atk));
    }
}
