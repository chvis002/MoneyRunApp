package com.moneyrun.moneyrunapp.sprites;


import java.lang.reflect.Constructor;
import com.*;
import com.moneyrun.moneyrunapp.graphics.Animation;
import com.moneyrun.moneyrunapp.graphics.Sprite;

/**
 * Det �r en sprite som anv�nds som grund f�r spelobjekten
 * @author Victor S�derberg & Christopher Visser
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
     * @param left animationen f�r v�nster
     * @param right animationen f�r h�ger
     * @param deadLeft animationen f�r d�d-v�nster
     * @param deadRight animationen f�r d�d-h�ger
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
     * H�mtar maxhastighet f�r creaturen
     * @return 0
     */
    public float getMaxSpeed() {
        return 0;
    }

    /**
     * Startar creaturen d� den dyker upp p� sk�rmen
     */
    public void wakeUp() {
        if (getState() == STATE_NORMAL && getVelocityX() == 0) {
            setVelocityX(-getMaxSpeed());
        }
    }

    /**
     * H�mtar statusen, state, f�r creaturen
     * @return state
     */
    public int getState() {
        return state;
    }

    /**
     * S�tter statusen, state, f�r creaturen
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
     *Vid en vertikal kollision med en tile.S�tter den vertikala hastigheten till noll
     */
    public void collideVertical() {
        setVelocityY(0);
    }

    /**
     * Uppdaterar creaturen beroende p� den tid som g�tt
     * @param elapsedTime den tid som g�tt
     */
    public void update(long elapsedTime) {
        // V�ljer r�tt animation
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
