package base;

import usage.Attackable;

public abstract class BaseCharacter extends BaseActor implements Attackable {

    private int hp;
    private int maxHp;
    private int atk;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMAX_HP() {
        return maxHp;
    }

    public void setMAX_HP(int MAX_HP) {
        this.maxHp = MAX_HP;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    @Override
    public void getDamage(int atk) {
        this.setHp(Math.max(0, this.getHp()-atk));
    }
}
