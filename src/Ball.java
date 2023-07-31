// Naor Nahman 207829185

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * .
 * class ball
 */
public class Ball implements Sprite {
    private GameEnvironment gameEnvi;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private Point point;
    private int rangeUpRight;
    private int rangeDownLeft;
    private double dxCheck;
    private double yxCheck;

    /**
     * . constructor .
     *
     * @param x     double
     * @param y     double
     * @param r     int radius
     * @param color color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;

    }

    /**
     * .
     * get gameEnvi..
     *
     * @return gameEnviroment game envi
     */
    public GameEnvironment getGameEnvi() {
        return this.gameEnvi;
    }

    /**
     * Sets game envi.
     *
     * @param gameEnvi the game envi
     */
    public void setGameEnvi(GameEnvironment gameEnvi) {
        this.gameEnvi = gameEnvi;
    }


    /**
     * . constructor .
     *
     * @param center double
     * @param r      int radius
     * @param color  color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;

    }

    /**
     * .
     * accessors
     *
     * @return point center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * .
     * accessors
     *
     * @param x1 double
     * @param y1 double
     */
    public void setCenter(double x1, double y1) {

        this.center = new Point(x1, y1);
    }

    /**
     * .
     * accessors
     *
     * @return int x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * .
     * accessors
     *
     * @return int y
     */
    public int getY() {
        return (int) this.center.getY();

    }

    /**
     * .
     * accessors
     *
     * @return range range up right
     */
    public int getRangeUpRight() {
        return this.rangeUpRight;
    }

    /**
     * .
     * accessors
     *
     * @return range range down left
     */
    public int getRangeDownLeft() {
        return rangeDownLeft;
    }

    /**
     * .
     * accessors
     *
     * @param rangeDownLeft int range
     */
    public void setRangeDownLeft(int rangeDownLeft) {
        this.rangeDownLeft = rangeDownLeft;
    }

    /**
     * .
     * accessors
     *
     * @param rangeUpRight int range
     */
    public void setRangeUpRight(int rangeUpRight) {
        this.rangeUpRight = rangeUpRight;
    }

    /**
     * .
     * accessors
     *
     * @return return radius
     */
    public int getSize() {
        return this.r;
    }

    /**
     * .
     * .
     * accessors
     *
     * @return color color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * .
     *
     * @param surface draw the ball on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    //    surface.drawLine((int) this.center.getX(), this.getY(), (int) (this.getX() + this.velocity.getDx()),
       //         (int) (this.getY() + this.velocity.getDy()));
     //   surface.setColor(Color.RED);
       // surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), 1);
    }

    /**
     * .
     * Sprite function
     * move one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * .
     * accessors
     *
     * @param v set..
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;

    }

    /**
     * .
     * accessors
     *
     * @param dx double
     * @param dy double
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * .
     * accessors
     *
     * @return velocity velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * .
     * move one step-moving the ball one step depends on his velocity and collidable
     */
    public void moveOneStep() {
        Point pointNextStep = new Point(this.getX() + (this.velocity.getDx() * 3),
                this.getY() + ((this.velocity.getDy() * 3)));
        Line trajectory = new Line(this.center, pointNextStep);
        Point pointNextStep2 = new Point(pointNextStep.getX() + (this.velocity.getDx() * 3),
                pointNextStep.getY() + ((this.velocity.getDy() * 3)));
        // traj2 is to check if the next step is going to hit.
        Line trajectory2 = new Line(pointNextStep, pointNextStep2);

        CollisionInfo info = this.gameEnvi.getClosestCollision(trajectory);
        if (info == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            CollisionInfo info2 = this.gameEnvi.getClosestCollision(trajectory2);
            //hit in the next step, to avoid it, change the velocity..
            if (info2 != null) {
                this.setVelocity(info2.collisionObject().hit(this, info2.collisionPoint(), this.velocity));
                this.center = this.getVelocity().applyToPoint(this.center);
            }
        } else {
            this.setVelocity(info.collisionObject().hit(this, info.collisionPoint(), this.velocity));
            this.center = this.getVelocity().applyToPoint(this.center);
        }
    }

    /**
     * .
     * addToGame-add this ball
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
