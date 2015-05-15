package com.moneyrun.moneyrunapp.graphics;

//TODO fix image issues animation class
//TODO check what android Animation class is
import java.awt.Image;
import java.util.ArrayList;

/**
 * Hanterar frames. Dels vilka bilder som ska visas och dess varaktighet.
 * @author Victor S�derberg & Christopher Visser
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
     * Till animationen l�ggs frames med en  varaktighet till
     * @param frames aktuella frames i en ArrayList
     * @param totalDuration Hur l�nge hela animationen p�g�r
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
     * Till animationen l�ggs en bild med en  varaktighet till
     * @param image den frame som ska l�ggas till
     * @param duration hur l�nge den ska spelas upp
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
     * Uppdaterar animationens beroende p� hur l�ng tid
     * som g�tt
     * @param elapsedTime den tid g�tt
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
     * H�mtar animationens bild
     * @return null, d� ingen  bild finns  <br>
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
     * H�mtar specifik frame
     * @param i indexet p� framen
     * @return AnimeFrame framen
     */
    private AnimFrame getFrame(int i) {
        return (AnimFrame)frames.get(i);
    }

    private class AnimFrame {

        Image image;
        long endTime;

        /**
         * Skapar en Frame f�r Animation
         * @param image aktuell frame
         * @param endTime Tiden som den ska spelas upp
         */
        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
}
