import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle block;
    private final List<HitListener> hitListeners;

    /**
     * Instantiates a new Block.
     *
     * @param rect the rect
     */
    public Block(Rectangle rect) {
        this.block = rect;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Gets rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return this.block;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.getRectangle();
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // the arr of lines in the index 0,2 describes the vertical  lines  , index 1,3 the horizontal
        //asking if the collisionpoint intersect with the vertical  lines if yes change the velocity of x ,
        this.notifyHit(hitter);
        if ((this.getRectangle().getLineArr()[0].pointInLine(collisionPoint)
                || this.getRectangle().getLineArr()[2].pointInLine(collisionPoint))
                && (this.getRectangle().getLineArr()[1].pointInLine(collisionPoint)
                || this.getRectangle().getLineArr()[3].pointInLine(collisionPoint))) {
            return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        if (this.getRectangle().getLineArr()[0].pointInLine(collisionPoint)
                || this.getRectangle().getLineArr()[2].pointInLine(collisionPoint)) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            // mean that the point intersect with horizontal  lines , change the velocity of y.
        } else if (this.getRectangle().getLineArr()[1].pointInLine(collisionPoint)
                || this.getRectangle().getLineArr()[3].pointInLine(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        }
        return new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.block.getColor() != null) {
            d.setColor(this.block.getColor());
        } else {
            d.setColor(Color.RED);
        }
        d.fillRectangle((int) this.getRectangle().getUpperLeft().getX(), ((int) this.getRectangle().getUpperLeft().
                getY()), (int) this.getRectangle().getWidth(), (int) this.getRectangle().getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.getRectangle().getUpperLeft().getX(), ((int) this.getRectangle().getUpperLeft().
                getY()), (int) this.getRectangle().getWidth(), (int) this.getRectangle().getHeight());
        //  }
    }

    @Override
    public void timePassed() {
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hit listener.
     *
     * @param hl the hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify hit.
     * notify all of the registered HitListener objects by calling their hitEvent method.
     * @param hitter the hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }


    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}