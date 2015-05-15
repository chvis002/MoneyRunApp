package com.moneyrun.moneyrunapp.graphics;


import android.media.Image;

import com.moneyrun.*;
/**
 * Ett objekt med en animation som kan r�ra p� sig
 * @author Victor S�derberg & Christopher Visser
 *
 */
public class Sprite {

    protected Animation anim;
    // position f�r pixlar
    private float x;
    private float y;
    // hastighet i pixlar
    private float dx;
    private float dy;

    /**
     * Skapar en ny sprite med en animation
     * @param anim Spritens animation
     */
    public Sprite(Animation anim) {
        this.anim = anim;
    }

    /**
     * Uppdaterar spritens animation samt dess position mha den vertikala och horisontella hastigheten
     * @param elapsedTime Den tid som g�tt sedan senaste uppdateringen
     */
    public void update(long elapsedTime) {
        x += dx * elapsedTime;
        y += dy * elapsedTime;
        anim.update(elapsedTime);
    }

    /**
     * @return spritens horisontella (x) position
     */
    public float getX() {
        return x;
    }

    /**
     * @return spritens vertikala (y) position
     */
    public float getY() {
        return y;
    }

    /**
     * S�tter spritens horisontella (x) position
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * S�tter spritens vertikala (y) position
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * @return spritens bredd
     */
    public int getWidth() {
        return anim.getImage().getWidth(null);
    }

    /**
     * @return spritens h�jd
     */
    public int getHeight() {
        return anim.getImage().getHeight(null);
    }

    /**
     * @return spritens horisonetlla (x) hastighet
     */
    public float getVelocityX() {
        return dx;
    }

    /**
     * @return spritens vertikala (y) hastighet
     */
    public float getVelocityY() {
        return dy;
    }

    /**
     * S�tter spritens horisontella (x) hastighet
     * @param dx
     */
    public void setVelocityX(float dx) {
        this.dx = dx;
    }

    /**
     * S�tter spritens vertikala (y) hastighet
     * @param dy
     */
    public void setVelocityY(float dy) {
        this.dy = dy;
    }

    /**
     *
     * @return spritens bild
     */
    public Image getImage() {
        return anim.getImage();
    }

    /**
     * @return Sprite (en kopia)
     */
    public Object clone() {
        return new Sprite(anim);
    }
}
