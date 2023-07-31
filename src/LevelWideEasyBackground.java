// Naor Nahman 207829185
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Level wide easy background.
 */
public class LevelWideEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        // create the lines of the sun
        int xStart = 15;
        int yStart = 300;
        while (xStart <= 700) {
            d.setColor(new Color(240, 230, 150));
            //draw line from 170,170 to xstart,ystart
            d.drawLine(170, 170, xStart, yStart);
            xStart += 5;
        }
        // the sun
        d.setColor(new Color(240, 230, 150));
        d.fillCircle(170, 170, 60);
        d.setColor(new Color(240, 215, 50));
        d.fillCircle(170, 170, 50);
        d.setColor(new Color(255, 225, 30));
        d.fillCircle(170, 170, 40);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
