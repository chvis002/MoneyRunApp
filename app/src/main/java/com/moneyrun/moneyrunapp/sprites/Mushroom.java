package com.moneyrun.moneyrunapp.sprites;

import com.moneyrun.moneyrunapp.graphics.Animation;

/**
 *En creature som �r en fiende och som r�r sig p� marken
 *@author Victor S�derBerg & Christopher Visser
 */
public class Mushroom extends Creature {

    /**
     * Skapar en landfiende med animationer
     * @param left animationen f�r v�nster
     * @param right animationen f�r h�ger
     * @param deadLeft animationen f�r d�d-v�nster
     * @param deadRight animationen f�r d�d-h�ger
     */
    public Mushroom(Animation left, Animation right,
                    Animation deadLeft, Animation deadRight){

        super(left, right, deadLeft, deadRight);
    }

    /**
     * H�mtar maxhastigheten
     * @return maxhastigheten
     */
    public float getMaxSpeed() {
        return 0.05f;
    }

}

