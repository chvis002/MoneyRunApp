package com.moneyrun.moneyrunapp.sprites;


import java.lang.reflect.Constructor;
import com.*;
import com.moneyrun.moneyrunapp.graphics.Animation;
import com.moneyrun.moneyrunapp.graphics.Sprite;

/**
 * Det är en sprite som används som grund för spelobjekten
 * @author Victor Söderberg & Christopher Visser
 *
 */
public abstract class Creature extends Sprite {

    /**
     * Tid mellan STATE_DYING och STATE_DEAD.
     */
    private static final int DIE_TIME = 1000;

    public static final int STATE_NORMAL = 0;
    public static final int STATE_DYING = 1;
    public static final int STATE_DEAD = 2;

    private Animation left;
    private Animation right;
    private Animation deadLeft;
    private Animation deadRight;
    private int state;
    private long stateTime;

    /**
     * Skapar en creature med animationer
     * @param left animationen för vänster
     * @param right animationen för höger
     * @param deadLeft animationen för död-vänster
     * @param deadRight animationen för död-höger
     */
    public Creature(Animation left, Animation right,
                    Animation deadLeft, Animation deadRight){

        super(right);
        this.left = left;
        this.right = right;
        this.deadLeft = deadLeft;
        this.deadRight = deadRight;
        state = STATE_NORMAL;
    }

    /**
     * Kopierar en creature med animationer
     * @return en klon av creature
     */
    public Object clone() {

        Constructor<?> constructor = getClass().getConstructors()[0];
        try {
            return constructor.newInstance(new Object[] {
                    (Animation)left.clone(),
                    (Animation)right.clone(),
                    (Animation)deadLeft.clone(),
                    (Animation)deadRight.clone()
            });
        }
        catch (Exception ex) {
            return null;
        }
    }

    /**
     * Hämtar maxhastighet för creaturen
     * @return 0
     */
    public float getMaxSpeed() {
        return 0;
    }

    /**
     * Startar creaturen då den dyker upp på skärmen
     */
    public void wakeUp() {
        if (getState() == STATE_NORMAL && getVelocityX() == 0) {
            setVelocityX(-getMaxSpeed());
        }
    }

    /**
     * Hämtar statusen, state, för creaturen
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * Sätter statusen, state, för creaturen
     * @param state
     */
    public void setState(int state) {
        if (this.state != state) {
            this.state = state;
            stateTime = 0;
            if (state == STATE_DYING) {
                setVelocityX(0);
                setVelocityY(0);
            }
        }
    }

    /**
     * Kontrollerar om creaturen lever
     * @return boolean true om den lever
     */
    public boolean isAlive() {
        return (state == STATE_NORMAL);
    }

    /**
     * Kontrollerar om creaturen flyger
     * @return false per default
     */
    public boolean isFlying() {
        return false;
    }

    /**
     * Vid en horisontell kollision med en tile.
     */
    public void collideHorizontal() {
        setVelocityX(-getVelocityX());
    }


    /**
     *Vid en vertikal kollision med en tile.Sätter den vertikala hastigheten till noll
     */
    public void collideVertical() {
        setVelocityY(0);
    }

    /**
     * Uppdaterar creaturen beroende på den tid som gått
     * @param elapsedTime den tid som gått
     */
    public void update(long elapsedTime) {
        // Väljer rätt animation
        Animation newAnim = anim;
        if (getVelocityX() < 0) {
            newAnim = left;
        }
        else if (getVelocityX() > 0) {
            newAnim = right;
        }
        if (state == STATE_DYING && newAnim == left) {
            newAnim = deadLeft;
        }
        else if (state == STATE_DYING && newAnim == right) {
            newAnim = deadRight;
        }

        // uppdaterar animation
        if (anim != newAnim) {
            anim = newAnim;
            anim.start();
        }
        else {
            anim.update(elapsedTime);
        }

        // uppdaterar state till deadstate
        stateTime += elapsedTime;
        if (state == STATE_DYING && stateTime >= DIE_TIME) {
            setState(STATE_DEAD);
        }
    }

}
