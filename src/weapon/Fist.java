package weapon;

import base.BaseWeapon;
import javafx.scene.image.Image;

public class Fist extends BaseWeapon {
    private static Fist instance;

    public Fist(int range, Image runImage,Image crouchingImage,Image attackAnimation,Image attackEffectImage) {
        super(range,runImage,crouchingImage,attackAnimation,attackEffectImage);
    }

    public static Fist getInstance() {
        if (instance == null)
            instance = new Fist(25,  new Image("trex/Trex_run.gif"),new Image("trex/Trex_crouching.gif"),new Image("trex/Trex_run.gif"),new Image("trex/attack_effect.gif"));
        return instance;
    }
}
