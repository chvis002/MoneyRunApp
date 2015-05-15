package com.moneyrun.moneyrunapp.input;

//TODO Make KeyListener and KeyEvent work with android instead of java awt
import android.text.method.KeyListener;
import android.view.KeyEvent;


import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;


/**
 * InputManager hanterar input av tangenter mha GameAction
 * @author Christopher Visser & Victor Söderberg
 *
 */
public class InputManager implements KeyListener {

    //Innefattar de mest använda tangentkoderna
    private static final int NUM_KEY_CODES = 600;

    private GameAction[] keyActions =
            new GameAction[NUM_KEY_CODES];

    private Component comp;

    /**
     * Skapar en ny InputManager som lyssnar på input
     * från den specificerade komponenten
     * @param comp komponenten
     */
    public InputManager(Component comp) {
        this.comp = comp;

        this.comp.addKeyListener(this);

        this.comp.setFocusTraversalKeysEnabled(false);
    }

    /**
     * Mappar en GameAction till en specifik tangent.
     * @param gameAction Den action som ska mappas
     * @param keyCode Tangentens virtuella kod
     */
    public void mapToKey(GameAction gameAction, int keyCode) {
        keyActions[keyCode] = gameAction;
    }

    /**
     * Rensar alla mappade tangenter för den specificerade
     * GameAction.
     * @param gameAction Den GameAction som ska rensas
     */
    public void clearMap(GameAction gameAction) {
        for (int i=0; i<keyActions.length; i++) {
            if (keyActions[i] == gameAction) {
                keyActions[i] = null;
            }
        }

        gameAction.reset();
    }

    /**
     * Hämtar en lista med namn för tangenter som är länkad till
     * den specificerade GameAction.
     * @param gameCode GameAction
     * @return Listan med namn
     */
    public List<String> getMaps(GameAction gameCode) {
        ArrayList<String> list = new ArrayList<String>();

        for (int i=0; i<keyActions.length; i++) {
            if (keyActions[i] == gameCode) {
                list.add(getKeyName(i));
            }
        }

        return list;
    }

    /**
     * Resettar alla GameActions så att det verkar som att de
     * inte har blivit nedtryckta
     */
    public void resetAllGameActions() {
        for (int i=0; i<keyActions.length; i++) {
            if (keyActions[i] != null) {
                keyActions[i].reset();
            }
        }
    }

    /**
     * Hämtar namnet av en tangentkod
     * @param keyCode tangenten
     * @return namnet
     */
    public static String getKeyName(int keyCode) {
        return KeyEvent.getKeyText(keyCode);
    }

    /**
     * Hämtar GameAction för den specificerade tangenteventet
     *
     * @param e Tangenten som tryckts
     * @return GameAction
     */
    private GameAction getKeyAction(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode < keyActions.length) {
            return keyActions[keyCode];
        }
        else {
            return null;
        }
    }


    /**
     * Sätter GameAction för den specificerade tagenteventets nyckel
     * som nedtryckt
     */
    public void keyPressed(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        if (gameAction != null) {
            gameAction.press();
        }
        e.consume();
    }


    /**
     * Sätter GameAction för den specificerade tagenteventets nyckel
     * som släppt
     */
    public void keyReleased(KeyEvent e) {
        GameAction gameAction = getKeyAction(e);
        if (gameAction != null) {
            gameAction.release();
        }
        e.consume();
    }


    /**
     * Ser till att tangenteventet in används för en annan
     * process
     */
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

}
