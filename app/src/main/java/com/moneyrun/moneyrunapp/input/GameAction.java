package com.moneyrun.moneyrunapp.input;
/**
 The GameAction class is an abstract to a user-initiated
 action, like jumping or moving. GameActions can be mapped
 to keys or the mouse with the InputManager.
 */

/**
 * GameAction är en klass hanterar olika sätt att interagera
 * med tangenter.
 * @author Christopher Visser & Victor Söderberg
 *
 */
public class GameAction {

    /**
     * Normalt beteenede, isPressed() returnerar true så länge som
     * nyckeln är intryckt.
     */
    public static final int NORMAL = 0;

    /**
     * Initialt trycka-ner-beteenede. isPressed() retunerar true endast när man
     * först tryckt ner nyckeln.
     */
    public static final int DETECT_INITAL_PRESS_ONLY = 1;

    private static final int STATE_RELEASED = 0;
    private static final int STATE_PRESSED = 1;
    private static final int STATE_WAITING_FOR_RELEASE = 2;

    private String name;
    private int behavior;
    private int amount;
    private int state;

    /**
     * Skapa en ny GameAction med valt beteende
     * @param name GameActions namn
     * @param behavior beteendet
     */
    public GameAction(String name, int behavior) {
        this.name = name;
        this.behavior = behavior;
        reset();
    }

    /**
     * Skapa en ny GameAction med normalt beteenede
     * @param name GameActions namn
     */
    public GameAction(String name) {
        this(name, NORMAL);
    }

    /**
     * Resettar GameAction så att det verkar som den inte varit nedtryckt
     */
    public void reset() {
        state = STATE_RELEASED;
        amount = 0;
    }

    /**
     * Signalerar att tangent har blivit nedtryckt ett specifikt
     * antal gånger
     * @param amount antal gånger tangenten har blivit nedtryckt
     */
    public synchronized void press(int amount) {
        if (state != STATE_WAITING_FOR_RELEASE) {
            this.amount+=amount;
            state = STATE_PRESSED;
        }

    }

    /**
     * Signalerar att man tryckt på en tangent
     */
    public synchronized void press() {
        press(1);
    }

    /**
     * Signalerar att tangenten har blivit släppt
     */
    public synchronized void release() {
        state = STATE_RELEASED;
    }


    /**
     * Returnerar ifall tangenten har blivit nedtryck sedan
     * senaste gången man tittade
     * @return true om den har blivit nedtryckt
     */
    public synchronized boolean isPressed() {
        return (getAmount() != 0);
    }

    /**
     * Returnerar antalet gånger nyckeln blivit nedtryckt
     * sen senaste gången man tittade
     * @return antalet gånger
     */
    public synchronized int getAmount() {
        int retVal = amount;
        if (retVal != 0) {
            if (state == STATE_RELEASED) {
                amount = 0;
            }
            else if (behavior == DETECT_INITAL_PRESS_ONLY) {
                state = STATE_WAITING_FOR_RELEASE;
                amount = 0;
            }
        }
        return retVal;
    }
}
