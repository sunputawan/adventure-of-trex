package base;

import javafx.scene.image.Image;

public abstract class BaseWeapon {
    private Image crouchingImage;
    private Image runImage;
    private Image attackEffectImage;
    private Image attackAnimationImage;
    private int range;


    public BaseWeapon(int range, Image runImage,Image crouchingImage,Image attackAnimationImage,Image attackEffectImage) {
        this.setRange(range);
        this.setRunImage(runImage);
        this.setCrouchingImage(crouchingImage);
        this.setAttackAnimationImage(attackAnimationImage);
        this.setAttackEffectImage(attackEffectImage);
    }
    public Image getRunImage() {
        return runImage;
    }

    public void setRunImage(Image image) {
        this.runImage = image;
    }

    public Image getCrouchingImage() {
        return crouchingImage;
    }

    public void setCrouchingImage(Image crouchingImage) {
        this.crouchingImage = crouchingImage;
    }

    public Image getAttackEffectImage() {
        return attackEffectImage;
    }

    public void setAttackEffectImage(Image attackEffectImage) {
        this.attackEffectImage = attackEffectImage;
    }

    public Image getAttackAnimationImage() {return attackAnimationImage;}
    public void setAttackAnimationImage(Image attackAnimationImage){this.attackEffectImage = attackAnimationImage;}
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
