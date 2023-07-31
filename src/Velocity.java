// Naor Nahman 207829185
// Velocity specifies the change in position on the `x` and the `y` axes.

/**
 * .
 * class velocity
 */
public class Velocity {
    private double dx, dy;

    // constructor

    /**
     * .
     * constructor
     *
     * @param dx double .
     * @param dy double
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * .
     * accessors
     *
     * @return this dx double
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * .
     * accessors
     *
     * @return this dy double
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * .
     *
     * @param angle .
     * @param speed .
     * @return new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy;
        dx = speed * Math.cos(Math.toRadians(angle));
        dy = speed * Math.sin(Math.toRadians(angle));
        if (dy > 0) {
           dy = -1 * dy;
        }
        return new Velocity(dx, dy);
    }

    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)

    /**
     * .
     * .Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     *
     * @param p point
     * @return new point
     */
    public Point applyToPoint(Point p) {
        double x1 = p.getX() + this.dx;
        double y1 = p.getY() + this.dy;
        return new Point(x1, y1);
    }
}