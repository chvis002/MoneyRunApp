package com.moneyrun.moneyrunapp.gameManager;


import android.media.Image;

import com.moneyrun.moneyrunapp.graphics.Sprite;

import java.util.LinkedList;
import java.util.Iterator;


/**
 * Inneh�ller data f�r en tile-map som hanterar tiles med referenser till bilder samt inkluderar sprites.
 * @author Victor S�derberg & Christopher Visser
 */
public class TileMap {

    private Image[][] tiles;
    private LinkedList<Sprite> sprites;
    private Sprite player;

    /**
     * Skapar en tilemap
     * @param width tilemapens bredd
     * @param height tilemappens h�jd
     */
    public TileMap(int width, int height) {
        tiles = new Image[width][height];
        sprites = new LinkedList<Sprite>();
    }

    /**
     * H�mtar tilemap:ens bredd.
     * @return antal tiles (bredd)
     */
    public int getWidth() {
        return tiles.length;
    }


    /**
     * H�mtar tilemap:ens h�jd.
     * @return antal tiles (h�jd)
     */
    public int getHeight() {
        return tiles[0].length;
    }

    /**
     * H�mtar en tilen fr�n en position
     * @param x x-positionen
     * @param y y-position
     * @return tiles <p>
     * null, om det inte finns n�gon tile eller om positionen �r utanf�r kartan
     */
    public Image getTile(int x, int y) {
        if (x < 0 || x >= getWidth() ||
                y < 0 || y >= getHeight()) {

            return null;
        }
        else {
            return tiles[x][y];
        }
    }

    /**
     * S�tter tile till en position
     * @param x tilens x-position
     * @param y tilens y-position
     * @param tile
     */
    public void setTile(int x, int y, Image tile) {
        tiles[x][y] = tile;
    }

    /**
     * H�mtar player sprite
     * @return player
     */
    public Sprite getPlayer() {
        return player;
    }

    /**
     * S�tter player sprite
     * @param player spelaren
     */
    public void setPlayer(Sprite player) {
        this.player = player;
    }

    /**
     * S�tter ut en sprite p� tilemap:en
     * @param sprite den sprite som ska l�ggas till
     */
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    /**
     * tar bort en sprite fr�n tilemap:en
     * @param sprite den sprite som ska tas bort
     */
    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    /**
     * H�mtar en itterator f�r alla Sprites p� den aktuella banan
     * exklusiva Spriten Player
     * @return
     */
    public Iterator<Sprite> getSprites() {
        return sprites.iterator();
    }

}
