import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private boolean ifWin;
    private Counter score;
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param ifWin    the if win
     * @param score    the score
     * @param keyboard
     */
    public EndScreen(boolean ifWin, Counter score, KeyboardSensor keyboard) {
        this.ifWin = ifWin;
        this.score = score;
        this.stop = false;
        this.keyboard = keyboard;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.ifWin) {
            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 5, d.getHeight() / 2,
                    "You Win! Your score is: " + this.score.getValue(), 40);
        } else {
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 5, d.getHeight() / 2,
                    "Game Over! Your score is: " + this.score.getValue(), 40);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
