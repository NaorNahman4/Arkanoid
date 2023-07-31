// naor nahman 207829185

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Score indicator.
 */
// naor nahman 207829185
public class ScoreIndicator implements Sprite {
    private final Rectangle rectangle;
    private final Counter score;
    private String levelName;
    private Counter lives;

    /**
     * Instantiates a new Score indicator.
     *
     * @param counter   the counter
     * @param rectangle the rectangle
     * @param lives
     */
    public ScoreIndicator(Counter counter, Counter lives, Rectangle rectangle) {
        this.score = counter;
        this.rectangle = rectangle;
        this.lives = lives;
    }

    /**
     * Sets level name.
     *
     * @param levelName the level name
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Sets lives.
     *
     * @param lives the lives
     */
    public void setLives(Counter lives) {
        this.lives = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x, y, width, height;
        x = (int) this.rectangle.getUpperLeft().getX();
        y = (int) this.rectangle.getUpperLeft().getY();
        width = (int) this.rectangle.getWidth();
        height = (int) this.rectangle.getHeight();
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.BLACK);
        // magic numbers
        int xVaule = (width / 2) - 100, yVaule = y + height - 4, size = 20;
        d.drawText(xVaule, yVaule, "Score: " + this.score.getValue(), size);
        d.drawText(xVaule + 250, yVaule, "Level Name: " + this.levelName, size);
        d.drawText(xVaule - 250, yVaule, "Lives: " + this.lives.getValue(), 20);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
