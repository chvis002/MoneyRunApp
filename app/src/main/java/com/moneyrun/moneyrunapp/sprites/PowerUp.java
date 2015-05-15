package com.moneyrun.moneyrunapp.sprites;


import java.lang.reflect.Constructor;
import com.moneyrun.*;
import com.moneyrun.moneyrunapp.graphics.Sprite;
import com.moneyrun.moneyrunapp.graphics.Animation;

/**
 * Det �r en sprite som spelkarakt�ren kan f�
 * @author Victor S�derberg & Christopher Visser
 */
public abstract class PowerUp extends Sprite {
    /**
     * Skapar en PowerUp med animationer
     * @param anim aktuell animation
     */
    public PowerUp(Animation anim) {
        super(anim);
    }

    /**
     * Skapar en PowerUp med animationer
     * @return en clone av PowerUp
     */
    public Object clone() {

        Constructor<?> constructor = getClass().getConstructors()[0];
        try {
            return constructor.newInstance(
                    new Object[] {(Animation)anim.clone()});
        }
        catch (Exception ex){
            return null;
        }
    }

    /**
     * Skapar en dollar-PowerUp
     */
    public static class Dollar extends PowerUp {
        public Dollar(Animation anim) {
            super(anim);
        }
    }

    /**
     * Skapar en nyckel-PowerUp
     */
    public static class Goal extends PowerUp {
        public Goal(Animation anim) {
            super(anim);
        }
    }

    /**
     * Skapar en hj�rta-PowerUp
     */
    public static class Heart extends PowerUp {
        public Heart(Animation anim) {
            super(anim);
        }
    }

}

