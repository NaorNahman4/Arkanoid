import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level final four background.
 */
public class LevelFinalFourBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.fillRectangle(25, 525, 750, 75);
        d.fillRectangle(25, 50, 750, 75);
        d.setColor(Color.WHITE);
        d.fillRectangle(25, 125, 750, 400);
        d.setColor(Color.BLUE);
        int x1 = 400, y1 = 365, x2 = 345, y2 = 276;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, y1, x2, y2);
            x1++;
            x2++;
        }
        x1 = 400;
        y1 = 365;
        x2 = 455;
        y2 = 276;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, y1, x2, y2);
            x1++;
            x2++;
        }
        x1 = 459;
        y1 = 285;
        x2 = 350;
        y2 = 285;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, y1, x2, y2);
            y1--;
            y2--;
        }
        // second
        x1 = 459;
        y1 = 345;
        x2 = 350;
        y2 = 345;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, y1, x2, y2);
            y1--;
            y2--;
        }
        x1 = 401;
        y1 = 252;
        x2 = 345;
        y2 = 345;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, y1, x2, y2);
            x1++;
            x2++;
        }
        x1 = 399;
        y1 = 252;
        x2 = 455;
        y2 = 345;
        for (int i = 0; i < 10; i++) {
            d.drawLine(x1, y1, x2, y2);
            x1++;
            x2++;
        }
        d.drawText(80, 250, "Love Israel", 40);
        d.drawText(550, 250, "Love IDF", 40);
        d.setColor(Color.BLACK);
        d.drawText(200, 500, "Remove All The Block To See The Full Background", 20);


    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}
