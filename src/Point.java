
/**
 * .
 * class Point
 */
public class Point {
    private double x;
    private double y;

    /**
     * . constructor .
     *
     * @param x double
     * @param y double
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * .
     * distance -- return the distance of this point to the other point
     *
     * @param other point
     * @return double distance
     */
    // distance -- return the distance of this point to the other point
    public double distance(Point other) {
        float distance = (float) (Math.sqrt((this.x - other.x) * (this.x - other.x)
                + (this.y - other.y) * (this.y - other.y)));
        // because its show as x.5125616511161616 sometimes adding two float point is incorrect while is correct.
        return (float) Math.round(distance * 10) / 10.0;
    }

    /**
     * .
     * equals -- return true is the points are equal, false otherwise
     *
     * @param other point
     * @return true /false
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return this.x == other.x && this.y == other.y;
    }

    /**
     * . Return the x
     * accessors
     *
     * @return double x
     */
    public double getX() {
        return this.x;
    }

    /**
     * . Return the y
     * accessors
     *
     * @return double y
     */
    public double getY() {
        return this.y;
    }
    /**.
     * change the x vaule
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**.
     * change the y vaule
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }
}
