package com.moneyrun.moneyrunapp.sprites;

import com.moneyrun.moneyrunapp.graphics.Animation;

/**
 * En creature som är en fiende och som flyger
 * @author Victor Söderberg & Christopher Visser
 */
public class Rocket extends Creature {
    /**
     * Skapar en raket med animationer
     * @param left animationen för vänster
     * @param right animationen för höger
     * @param deadLeft animationen för död-vänster
     * @param deadRight animationen för död-höger
     */
    public Rocket(Animation left, Animation right,
                  Animation deadLeft, Animation deadRight){

        super(left, right, deadLeft, deadRight);
    }

    /**
     * Hämtar maxhastigheten
     * @return maxhastigheten 0.2f
     */
    public float getMaxSpeed() {
        return 0.2f;
    }

    /**
     * Kontrollerar om den flyger
     * @return <code>true</code> om den lever <br>
     * <code>false</code> om den är död, dvs påverkas av
     * gravitation
     */
    public boolean isFlying() {
        return isAlive();
    }
}

