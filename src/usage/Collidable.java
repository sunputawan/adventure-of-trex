package usage;

import actor.Trex;

public interface Collidable {
    public boolean checkCollide(Trex trex);
    public void collideAction(Trex trex);
}
