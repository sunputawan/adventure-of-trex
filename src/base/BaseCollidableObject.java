package base;

import actor.Trex;
import usage.Collidable;
import usage.Movable;
import utils.GamePlayConfig;
import utils.Position;

public abstract class BaseCollidableObject extends BaseActor implements Collidable, Movable {
    private int collideAtk;

    @Override
    public boolean checkCollide(Trex trex) {
        double tx = trex.getPos().getX();
        double ty = trex.getPos().getY();
        double x = this.getPos().getX();
        double y = this.getPos().getY();


        return tx + GamePlayConfig.PLAYER_SIZE < x + GamePlayConfig.MONSTER_SIZE &&
                tx + GamePlayConfig.PLAYER_SIZE - GamePlayConfig.BLANK_AREA > x &&
                ty > y + GamePlayConfig.MONSTER_SIZE &&
                ty + GamePlayConfig.PLAYER_SIZE > y;
//        return (x - 5 <= tx && tx <= x + 5) && (y - 5 <= ty && ty <= y + 5);
    }
    @Override
    public void collideAction(Trex trex) {
        System.out.println("attack Trex damage:" + this.getCollideAtk());
        trex.getDamage(this.getCollideAtk());
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

    public int getCollideAtk() {
        return collideAtk;
    }

    public void setCollideAtk(int collideAtk) {
        this.collideAtk = collideAtk;
    }

    public abstract void move();
}
