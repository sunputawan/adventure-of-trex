package weapon;

import base.BaseWeapon;
import javafx.scene.image.Image;

public class Sword extends BaseWeapon {
    private static Sword instance;

    public Sword(int range, Image runImage,Image crouchingImage,Image attackAnimation,Image attackEffectImage) {
        super(range,runImage,crouchingImage,attackAnimation,attackEffectImage);
    }

    public static Sword getInstance() {
        if (instance == null)
            instance = new Sword(50,  new Image("trex/Trex_sword_run.gif"),new Image("trex/Trex_crouching.gif"),new Image("trex/Trex_sword_run.gif"),new Image("trex/Trex_sword_effect.gif"));
        return instance;
    }
}
