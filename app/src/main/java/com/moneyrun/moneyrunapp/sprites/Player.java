package com.moneyrun.moneyrunapp.sprites;

import com.moneyrun.moneyrunapp.graphics.Animation;

/**
 * Spelkarakt�ren
 * @author Victor S�derberg & Christopher Visser
 */
public class Player extends Creature {
    //S�tter hastigheten f�r hopp
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
     * S�tter hastigheten till noll vid en horisontell (x) kollision
     */
    public void collideHorizontal() {
        setVelocityX(0);
    }

    /**
     *  S�tter hastigheten till noll vid en vertikal (y) kollision. Kontrollerar �ven om
     */
    public void collideVertical() {
        // Kontrollerar om det �r en kollision med marken
        if (getVelocityY() > 0) {
            onGround = true;
        }
        setVelocityY(0);
    }

    /**
     * S�tter den vertikala (y) positionen
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
     * Spelkarat�ren hoppar om den �r p� marken eller om forceJump �r sant
     * @param forceJump
     */
    public void jump(boolean forceJump) {
        if (onGround || forceJump) {
            onGround = false;
            setVelocityY(JUMP_SPEED);
        }
    }

    /**
     * H�mtar maxhastigheten
     * @return maxhastigheten
     */
    public float getMaxSpeed() {
        return 0.5f; //todo change!
    }

}
