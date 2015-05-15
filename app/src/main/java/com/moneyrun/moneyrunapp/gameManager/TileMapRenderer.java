package com.moneyrun.moneyrunapp.gameManager;

import android.graphics.Color;
import android.media.Image;

import com.moneyrun.moneyrunapp.graphics.Sprite;
import com.moneyrun.moneyrunapp.sprites.Creature;

import java.awt.*;
import java.util.Iterator;



/**
 * Den ritar ut en TileMap d�r det ritas ut sprites, tiles och bakgrundbild
 * @author Victor S�derberg & Christopher Visser
 */
public class TileMapRenderer {
    // Storleken p� TileMap (8x8)
    private static final int TILE_SIZE = 64;
    // Storleken p� TileMap i bits (2^6=64)
    private static final int TILE_SIZE_BITS = 6;
    private Image background;

    /**
     * Konverterar en pixels position till en tiles position
     * @param pixels pixlar i form av float
     * @return en pixels position i form av en tiles position (avrundat)
     */
    public static int pixelsToTiles(float pixels) {
        return pixelsToTiles(Math.round(pixels));
    }

    /**
     * Konverterar en pixels position till en tiles position
     * @param pixels pixlar i form av int
     * @return en pixels position i form av en tiles position
     */
    public static int pixelsToTiles(int pixels) {
        //Anv�nder shift right f�r snabbare utr�kningar
        return pixels >> TILE_SIZE_BITS;
    }

    /**
     * Konverterar en tiles position till en pixels position
     * @param numTiles
     * @return en tiles position i form av en pixels position
     */
    public static int tilesToPixels(int numTiles) {
        //Anv�nder shift left f�r snabbare utr�kningar
        return numTiles << TILE_SIZE_BITS;
    }

    /**
     * S�tter vilken bakgrund som ska anv�ndas
     * @param background
     */
    public void setBackground(Image background) {
        this.background = background;
    }

    //ToDo Get Graphics2D to work with android
    /**
     * Ritar ut en TileMap
     * @param g
     * @param map
     * @param screenWidth
     * @param screenHeight
     */
    public void draw(Graphics2D g, TileMap map,int screenWidth, int screenHeight, boolean menuState) {
        Sprite player = map.getPlayer();
        int mapWidth = tilesToPixels(map.getWidth());

        // H�mtar den horisontella (x) postionen f�r vyn med h�nsyn till att spelkarakt�ren position �r i mitten av densamma.
        int offsetX = screenWidth / 2 -
                Math.round(player.getX()) - TILE_SIZE;
        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, screenWidth - mapWidth);

        // h�mtar den vertikala (y) positionen f�r att hantera vyns utformning d� tiles och sprites ska ritas ut
        int offsetY = screenHeight - tilesToPixels(map.getHeight());

        // ritar ut en svart bakgrund vid behov
        if (background == null ||
                screenHeight > background.getHeight(null)){

            g.setColor(Color.black);
            g.fillRect(0, 0, screenWidth, screenHeight);
        }

        // Ritar ut bakgrunden som f�ljer med spelaren.
        // Detta sker l�ngsammare �n spelkarakt�ren r�r p� sig f�r att skapa ett perspektiv
        if (background != null) {
            int x = offsetX *
                    (screenWidth - background.getWidth(null)) /
                    (screenWidth - mapWidth);
            int y = screenHeight - background.getHeight(null);

            g.drawImage(background, x, y, null);
        }


        // Ritar ut synliga tiles
        int firstTileX = pixelsToTiles(-offsetX);
        int lastTileX = firstTileX +
                pixelsToTiles(screenWidth) + 1;
        for (int y=0; y<map.getHeight(); y++) {
            for (int x=firstTileX; x <= lastTileX; x++) {
                Image image = map.getTile(x, y);
                if (image != null) {
                    g.drawImage(image,
                            tilesToPixels(x) + offsetX,
                            tilesToPixels(y) + offsetY,
                            null);
                }
            }
        }
        //om man inte �r i menyn
        if (!menuState){
            // ritar ut spelkarakt�ren
            g.drawImage(player.getImage(),
                    Math.round(player.getX()) + offsetX,
                    Math.round(player.getY()) + offsetY,
                    null);

            // ritar ut sprites
            Iterator<Sprite> i = map.getSprites();
            while (i.hasNext()) {
                Sprite sprite = (Sprite)i.next();
                int x = Math.round(sprite.getX()) + offsetX;
                int y = Math.round(sprite.getY()) + offsetY;
                g.drawImage(sprite.getImage(), x, y, null);

                // aktiverar en creature d� den �r synlig p� sk�rmen
                if (sprite instanceof Creature &&
                        x >= 0 && x < screenWidth)
                {
                    ((Creature)sprite).wakeUp();
                }
            }
        }
    }

}

