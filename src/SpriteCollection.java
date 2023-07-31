// Naor Nahman 207829185

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {
    private java.util.List<Sprite> spriteList;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        List<Sprite> copyList = new ArrayList<>(this.spriteList);
        for (Sprite sprite : copyList) {
            sprite.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList) {
            sprite.drawOn(d);
        }
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public List<Sprite> getSprites() {
        return this.spriteList;
    }
}
