package com.moneyrun.moneyrunapp.gameManager;


import android.media.Image;

import com.moneyrun.moneyrunapp.graphics.Sprite;

import java.util.LinkedList;
import java.util.Iterator;


/**
 * Innehåller data för en tile-map som hanterar tiles med referenser till bilder samt inkluderar sprites.
 * @author Victor Söderberg & Christopher Visser
 */
public class TileMap {

    private Image[][] tiles;
    private LinkedList<Sprite> sprites;
    private Sprite player;

    /**
     * Skapar en tilemap
     * @param width tilemapens bredd
     * @param height tilemappens höjd
     */
    public TileMap(int width, int height) {
        tiles = new Image[width][height];
        sprites = new LinkedList<Sprite>();
    }

    /**
     * Hämtar tilemap:ens bredd.
     * @return antal tiles (bredd)
     */
    public int getWidth() {
        return tiles.length;
    }


    /**
     * Hämtar tilemap:ens höjd.
     * @return antal tiles (höjd)
     */
    public int getHeight() {
        return tiles[0].length;
    }

    /**
     * Hämtar en tilen från en position
     * @param x x-positionen
     * @param y y-position
     * @return tiles <p>
     * null, om det inte finns någon tile eller om positionen är utanför kartan
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
     * Sätter tile till en position
     * @param x tilens x-position
     * @param y tilens y-position
     * @param tile
     */
    public void setTile(int x, int y, Image tile) {
        tiles[x][y] = tile;
    }

    /**
     * Hämtar player sprite
     * @return player
     */
    public Sprite getPlayer() {
        return player;
    }

    /**
     * Sätter player sprite
     * @param player spelaren
     */
    public void setPlayer(Sprite player) {
        this.player = player;
    }

    /**
     * Sätter ut en sprite på tilemap:en
     * @param sprite den sprite som ska läggas till
     */
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    /**
     * tar bort en sprite från tilemap:en
     * @param sprite den sprite som ska tas bort
     */
    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
    }

    /**
     * Hämtar en itterator för alla Sprites på den aktuella banan
     * exklusiva Spriten Player
     * @return
     */
    public Iterator<Sprite> getSprites() {
        return sprites.iterator();
    }

}
