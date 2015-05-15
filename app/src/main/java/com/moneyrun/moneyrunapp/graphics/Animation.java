package com.moneyrun.moneyrunapp.graphics;

//TODO fix image issues animation class
//TODO check what android Animation class is
import java.awt.Image;
import java.util.ArrayList;

/**
 * Hanterar frames. Dels vilka bilder som ska visas och dess varaktighet.
 * @author Victor Söderberg & Christopher Visser
 */
public class Animation {

    private ArrayList<AnimFrame> frames;
    private int currFrameIndex;
    private long animTime;
    private long totalDuration;


    /**
     * Skapar en ny animation
     */
    public Animation() {
        this(new ArrayList<AnimFrame>(), 0);
    }

    /**
     * Till animationen läggs frames med en  varaktighet till
     * @param frames aktuella frames i en ArrayList
     * @param totalDuration Hur länge hela animationen pågår
     */
    private Animation(ArrayList<AnimFrame> frames, long totalDuration) {

        this.frames = frames;
        this.totalDuration = totalDuration;
        start();
    }

    /**
     * Clonar Animation
     * @return Animation (en kopia)
     */
    public Object clone() {
        return new Animation(frames, totalDuration);
    }

    /**
     * Till animationen läggs en bild med en  varaktighet till
     * @param image den frame som ska läggas till
     * @param duration hur länge den ska spelas upp
     */
    public synchronized void addFrame(Image image,long duration){
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

    /**
     * Startar animationen
     */
    public synchronized void start() {
        animTime = 0;
        currFrameIndex = 0;
    }

    /**
     * Uppdaterar animationens beroende på hur lång tid
     * som gått
     * @param elapsedTime den tid gått
     */
    public synchronized void update(long elapsedTime) {
        if (frames.size() > 1) {
            animTime += elapsedTime;

            if (animTime >= totalDuration) {
                animTime = animTime % totalDuration;
                currFrameIndex = 0;
            }

            while (animTime > getFrame(currFrameIndex).endTime) {
                currFrameIndex++;
            }
        }
    }

    /**
     * Hämtar animationens bild
     * @return null, då ingen  bild finns  <br>
     * nuvarande frames Image
     */
    public synchronized Image getImage() {
        if (frames.size() == 0) {
            return null;
        }
        else {
            return getFrame(currFrameIndex).image;
        }
    }

    /**
     * Hämtar specifik frame
     * @param i indexet på framen
     * @return AnimeFrame framen
     */
    private AnimFrame getFrame(int i) {
        return (AnimFrame)frames.get(i);
    }

    private class AnimFrame {

        Image image;
        long endTime;

        /**
         * Skapar en Frame för Animation
         * @param image aktuell frame
         * @param endTime Tiden som den ska spelas upp
         */
        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
}
