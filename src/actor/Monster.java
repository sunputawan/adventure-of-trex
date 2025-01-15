package actor;

import base.BaseCollidableObject;
import javafx.scene.image.Image;
import utils.GamePlayConfig;

public class Monster extends BaseCollidableObject{

    private int cost;
    private int hp;
    private int maxHp;

    public Monster(int maxHp, int collideAtk, int cost, Image showImage) {
        this.setMaxHp(maxHp);
        this.setHp(maxHp);
        this.setCollideAtk(collideAtk);
        this.setCost(cost);
        this.setShowImage(showImage);
    }

    @Override
    public boolean checkCollide(Trex trex){
        double tx =  trex.getPos().getX();
        double ty = trex.getPos().getY();
        double mx = this.getPos().getX();
        double my = this.getPos().getY();
        return tx + GamePlayConfig.BLANK_AREA < mx + GamePlayConfig.MONSTER_SIZE - GamePlayConfig.BLANK_AREA &&
                tx + GamePlayConfig.PLAYER_SIZE - GamePlayConfig.BLANK_AREA > mx + GamePlayConfig.BLANK_AREA &&
                ty + GamePlayConfig.BLANK_AREA + (trex.isCrouching() ? (GamePlayConfig.PLAYER_SIZE - 2 * GamePlayConfig.BLANK_AREA) / 2 : 0 ) < my + GamePlayConfig.MONSTER_SIZE - GamePlayConfig.BLANK_AREA &&
                ty + GamePlayConfig.PLAYER_SIZE - GamePlayConfig.BLANK_AREA > my + GamePlayConfig.BLANK_AREA;
    }
    @Override
    public void move(){
        this.getPos().setX(this.getPos().getX() - GamePlayConfig.MONSTER_SPEED);
    }

    public boolean isOutOfRange(){
        return this.getPos().getX() + GamePlayConfig.MONSTER_SIZE < 0;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = Math.max(maxHp,0);
    }

    @Override
    public void getDamage(int atk) {
        this.setHp(Math.max(0, this.getHp() - atk));
    }

}
