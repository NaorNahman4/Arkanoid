import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private boolean running;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long startTime;
    private double numOfSeconds;
    private int firstCount;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.running = true;
        this.startTime = System.currentTimeMillis();
        this.firstCount = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {

        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLUE);
        int x = 400, y = 400, size = 50;
        d.drawText(x, y, Integer.toString(this.countFrom), size);
        long numOfMillis = (long) (numOfSeconds * 1200);
        if (System.currentTimeMillis() - this.startTime > numOfMillis / this.firstCount) {
            this.startTime = System.currentTimeMillis();
            this.countFrom--;
        }
              if (this.countFrom == 0) {
                this.running = false;
           }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}