// Naor Nahman 207829185
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Level green 3 background.
 */
public class LevelGreen3Background implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(7, 241, 101, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.fillRectangle(98, 410, 130, 250);
        d.setColor(Color.WHITE);
        int xStart = 105;
        int yStart = 420;
        // row
        for (int i = 0; i < 5; i++) {
            // columns
            for (int j = 0; j < 6; j++) {
                d.fillRectangle(xStart, yStart, 15, 27);
                yStart += 34;
            }
            d.setColor(Color.WHITE);
            yStart = 420;
            xStart += 25;
        }
        d.setColor(Color.GRAY);
        d.fillRectangle(140, 350, 50, 60);
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(155, 150, 20, 200);

        d.setColor(Color.YELLOW);
        d.fillCircle(165, 140, 12);
        d.setColor(Color.PINK);
        d.fillCircle(165, 140, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(165, 140, 4);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}
