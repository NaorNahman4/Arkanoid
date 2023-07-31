// Naor Nahman 207829185

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private biuoop.GUI gui;
    private int speed;
    private int width;


    /**
     * Instantiates a new Paddle.
     *
     * @param rectangle the rectangle
     * @param gui       the gui
     * @param keyboard  the keyboard
     */
    public Paddle(Rectangle rectangle, GUI gui, KeyboardSensor keyboard) {
        this.gui = gui;
        this.rectangle = rectangle;
        this.keyboard = keyboard;
    }

    /**
     * Instantiates a new Paddle.
     * constractor to support different paddles , each level has different paddle.
     *
     * @param speed    the speed
     * @param width    the width
     * @param keyboard the keyboard
     * @param gui
     */
    public Paddle(int speed, int width, KeyboardSensor keyboard, GUI gui) {
        this.keyboard = keyboard;
        int heightPuddle = 10, startPuddleX = 400 - width / 2, startPuddleY = 565;
        this.rectangle = new Rectangle(new Point(startPuddleX, startPuddleY), width, heightPuddle);
        this.speed = speed;
        this.width = width;
        this.gui = gui;

    }

    /**
     * Sets keyboard.
     */
    public void setKeyboard() {
        this.keyboard = this.gui.getKeyboardSensor();
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if (this.getCollisionRectangle().getUpperLeft().getX() - this.speed >= 25) {
            Point point = new Point(this.getCollisionRectangle().getUpperLeft().getX() - this.speed,
                    this.getCollisionRectangle()
                    .getUpperLeft().getY());
            Rectangle newRectangle = new Rectangle(point, this.getCollisionRectangle().getWidth(), this.
                    getCollisionRectangle().getHeight());
            this.setRectangle(newRectangle);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if (this.getCollisionRectangle().getUpperLeft().getX() + this.speed + this.width <= 775) {
            Point point = new Point(this.getCollisionRectangle().getUpperLeft().getX() + this.speed,
                    this.getCollisionRectangle()
                    .getUpperLeft().getY());
            Rectangle newRectangle = new Rectangle(point, this.getCollisionRectangle().getWidth(), this.
                    getCollisionRectangle().getHeight());
            this.setRectangle(newRectangle);
        }
    }

    /**
     * Sets rectangle.
     *
     * @param rectangle the rectangle
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    // Sprite
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        if (this.rectangle.getColor() != null) {
            d.setColor(this.rectangle.getColor());
        } else {
            this.rectangle.setColor(Color.MAGENTA);
            d.setColor(Color.MAGENTA);
        }
        d.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                ((int) this.getCollisionRectangle().getUpperLeft().getY()), (int)
                        this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
    }

    // Collidable
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // check if the hit is on the lines in the sides=with side inclince as x=0;
        if (this.rectangle.getLineArr()[0].pointInLine(collisionPoint) || this.rectangle.getLineArr()[2].
                pointInLine(collisionPoint)) {
            return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
        }
        double xCollision = collisionPoint.getX();
        double leftX = this.rectangle.getUpperLeft().getX();
        double diff = this.rectangle.getWidth() / 6.0;
        double region1 = leftX;
        double region2 = region1 + diff;
        double region3 = region2 + diff;
        double region4 = region3 + diff;
        double region5 = region4 + diff;
        double region6 = region5 + diff;
        Velocity newVelocity = null;
        double speed = 5;
        if (region1 <= xCollision && xCollision <= region2) {
            newVelocity = Velocity.fromAngleAndSpeed(330, -speed);
        } else if (region2 < xCollision && xCollision <= region3) {
            newVelocity = Velocity.fromAngleAndSpeed(300, -speed);
        } else if (region3 < xCollision && xCollision <= region4) {
            newVelocity = Velocity.fromAngleAndSpeed(90, speed);
        } else if (region4 < xCollision && xCollision <= region5) {
            newVelocity = Velocity.fromAngleAndSpeed(60, speed);
        } else if (region5 < xCollision && xCollision <= region6) {
            newVelocity = Velocity.fromAngleAndSpeed(30, speed);
        }
        if (newVelocity == null) {
            newVelocity = Velocity.fromAngleAndSpeed(60, speed);
        }
        if (newVelocity.getDy() > 0) {
            newVelocity = new Velocity(newVelocity.getDx(), -1 * newVelocity.getDy());
        }
        return newVelocity;
    }

    // Add this paddle to the game.
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}