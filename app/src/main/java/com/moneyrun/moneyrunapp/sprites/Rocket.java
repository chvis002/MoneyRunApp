package com.moneyrun.moneyrunapp.sprites;

import com.moneyrun.moneyrunapp.graphics.Animation;

/**
 * En creature som �r en fiende och som flyger
 * @author Victor S�derberg & Christopher Visser
 */
public class Rocket extends Creature {
    /**
     * Skapar en raket med animationer
     * @param left animationen f�r v�nster
     * @param right animationen f�r h�ger
     * @param deadLeft animationen f�r d�d-v�nster
     * @param deadRight animationen f�r d�d-h�ger
     */
    public Rocket(Animation left, Animation right,
                  Animation deadLeft, Animation deadRight){

        super(left, right, deadLeft, deadRight);
    }

    /**
     * H�mtar maxhastigheten
     * @return maxhastigheten 0.2f
     */
    public float getMaxSpeed() {
        return 0.2f;
    }

    /**
     * Kontrollerar om den flyger
     * @return <code>true</code> om den lever <br>
     * <code>false</code> om den �r d�d, dvs p�verkas av
     * gravitation
     */
    public boolean isFlying() {
        return isAlive();
    }
}

