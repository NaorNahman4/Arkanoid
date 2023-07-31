import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level direct hit background.
 */
public class LevelDirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        int xCenter = 400, yCenter = 170, radius = 120;
        d.drawCircle(xCenter, yCenter, radius);
        d.drawCircle(xCenter, yCenter, (radius * 2) / 3);
        d.drawCircle(xCenter, yCenter, radius / 3);
        d.drawLine(xCenter, yCenter, xCenter, yCenter - (20 + radius));
        d.drawLine(xCenter, yCenter, xCenter, yCenter + (20 + radius));
        d.drawLine(xCenter, yCenter, xCenter - (20 + radius), yCenter);
        d.drawLine(xCenter, yCenter, xCenter + (20 + radius), yCenter);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}
