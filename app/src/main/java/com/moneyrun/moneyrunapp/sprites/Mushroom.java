package com.moneyrun.moneyrunapp.sprites;

import com.moneyrun.moneyrunapp.graphics.Animation;

/**
 *En creature som är en fiende och som rör sig på marken
 *@author Victor SöderBerg & Christopher Visser
 */
public class Mushroom extends Creature {

    /**
     * Skapar en landfiende med animationer
     * @param left animationen för vänster
     * @param right animationen för höger
     * @param deadLeft animationen för död-vänster
     * @param deadRight animationen för död-höger
     */
    public Mushroom(Animation left, Animation right,
                    Animation deadLeft, Animation deadRight){

        super(left, right, deadLeft, deadRight);
    }

    /**
     * Hämtar maxhastigheten
     * @return maxhastigheten
     */
    public float getMaxSpeed() {
        return 0.05f;
    }

}

