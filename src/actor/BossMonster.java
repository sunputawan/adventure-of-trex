package actor;

import base.BaseActor;
import base.BaseCharacter;
import logic.GamePlayController;
import utils.GamePlayConfig;


public class BossMonster extends BaseCharacter {

    private int cost;
    private int range;
    private boolean isAttacking =false;
    private long lastAttackTime = 0;

    public BossMonster(int MAX_HP, int atk, int cost) {
        this.setMAX_HP(MAX_HP);
        this.setHp(MAX_HP);
        this.setAtk(atk);
        this.getPos().setX(GamePlayConfig.BOSS_POSITION.getX());
        this.getPos().setY(GamePlayConfig.BOSS_POSITION.getY());// at midRight
        this.setCost(cost);
        this.range = GamePlayController.getInstance().getTrex().getWeapon().getRange();
    }
    @Override
    public void attack(BaseActor baseActor) {
        if (baseActor instanceof Trex) {
            ((Trex) baseActor).getDamage(this.getAtk());
        }
    }
    @Override
    public void getDamage(int atk){
        this.setHp(Math.max(0,getHp()-atk));
    }

    public void move(){
        long currentTime = System.nanoTime();
        if (currentTime - lastAttackTime >= GamePlayConfig.BOSS_ATTACK_COOLDOWN * GamePlayConfig.BOSS_ATTACK_COOLDOWN_SEC) {
            isAttacking = true;
            this.lastAttackTime = currentTime;
            if (isCanAttack()) {
                // Collision detected
//                System.out.println("Boss Attack");
                this.attack(GamePlayController.getInstance().getTrex());
            }
        }
        if (isAttacking) {
            long elapsedTime = System.nanoTime() - lastAttackTime;
            if (elapsedTime >= 250_000_000) {
                // End attack animation
                isAttacking = false;
            }
        }
    }

    private boolean isCanAttack() {
        Trex trex = GamePlayController.getInstance().getTrex();
        double tx = trex.getPos().getX();
        double ty = trex.getPos().getY();
        boolean canAttack = tx + GamePlayConfig.PLAYER_SIZE < this.getPos().getX() + GamePlayConfig.MONSTER_SIZE &&
                tx + GamePlayConfig.PLAYER_SIZE - GamePlayConfig.BLANK_AREA + range > this.getPos().getX() &&
                ty < this.getPos().getY() + GamePlayConfig.MONSTER_SIZE &&
                ty + GamePlayConfig.PLAYER_SIZE > this.getPos().getY();
        return canAttack;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getRange(){
        return range;
    }

    public boolean isAttacking(){
        return isAttacking;
    }

}
