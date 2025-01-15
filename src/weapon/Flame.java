package weapon;

import base.BaseWeapon;
import javafx.scene.image.Image;

public class  Flame extends BaseWeapon {
    private static Flame instance;

    public Flame(int range, Image runImage,Image crouchingImage,Image attackAnimation,Image attackEffectImage) {
        super(range,runImage,crouchingImage,attackAnimation,attackEffectImage);
    }

    public static Flame getInstance() {
        if (instance == null)
            instance = new Flame(100,  new Image("trex/Trex_run.gif"),new Image("trex/Trex_crouching.gif"),new Image("trex/Trex_run.gif"),new Image("trex/attack_effect.gif"));
        return instance;
    }
}
