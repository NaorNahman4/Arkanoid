import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the d
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    void timePassed();

    /**
     * .
     * add the sprite to the game.
     *
     * @param g
     */
    void addToGame(GameLevel g);
}