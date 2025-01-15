package base;

import javafx.scene.image.Image;
import usage.Damageable;
import utils.Position;

public abstract class BaseActor implements Damageable {
    private Position pos;
    private Image showImage;

    public BaseActor(){
        pos = new Position(0,0);
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Image getShowImage() {
        return showImage;
    }

    public void setShowImage(Image image) {
        this.showImage = image;
    }
}
