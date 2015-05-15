package com.moneyrun.moneyrunapp.sprites;

import com.moneyrun.moneyrunapp.graphics.Animation;

/**
 * Spelkaraktären
 * @author Victor Söderberg & Christopher Visser
 */
public class Player extends Creature {
    //Sätter hastigheten för hopp
    private static final float JUMP_SPEED = -.95f;
    private boolean onGround;

    /**
     * Skapar en player med animationer
     * @param left
     * @param right
     * @param deadLeft
     * @param deadRight
     */
    public Player(Animation left, Animation right,
                  Animation deadLeft, Animation deadRight){

        super(left, right, deadLeft, deadRight);
    }

    /**
     * Sätter hastigheten till noll vid en horisontell (x) kollision
     */
    public void collideHorizontal() {
        setVelocityX(0);
    }

    /**
     *  Sätter hastigheten till noll vid en vertikal (y) kollision. Kontrollerar även om
     */
    public void collideVertical() {
        // Kontrollerar om det är en kollision med marken
        if (getVelocityY() > 0) {
            onGround = true;
        }
        setVelocityY(0);
    }

    /**
     * Sätter den vertikala (y) positionen
     * @param y
     */
    public void setY(float y) {
        // kontroll om fall
        if (Math.round(y) > Math.round(getY())) {
            onGround = false;
        }
        super.setY(y);
    }

    /**
     * Spelkaratären hoppar om den är på marken eller om forceJump är sant
     * @param forceJump
     */
    public void jump(boolean forceJump) {
        if (onGround || forceJump) {
            onGround = false;
            setVelocityY(JUMP_SPEED);
        }
    }

    /**
     * Hämtar maxhastigheten
     * @return maxhastigheten
     */
    public float getMaxSpeed() {
        return 0.5f; //todo change!
    }

}
