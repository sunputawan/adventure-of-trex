package weapon;

import base.BaseWeapon;
import javafx.scene.image.Image;

public class Gun extends BaseWeapon {
    private static Gun instance;

    public Gun(int range, Image runImage,Image crouchingImage,Image attackAnimation,Image attackEffectImage) {
        super(range,runImage,crouchingImage,attackAnimation,attackEffectImage);
    }

    public static Gun getInstance() {
        if (instance == null)
            instance = new Gun(75,  new Image("trex/Trex_gun_run.gif"),new Image("trex/Trex_crouching.gif"),new Image("trex/Trex_gun_run.gif"),new Image("trex/attack_effect.gif"));
        return instance;
    }
}
