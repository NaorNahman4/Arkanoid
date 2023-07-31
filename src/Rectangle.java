// Naor Nahman 207829185

import java.awt.Color;
import java.util.ArrayList;

/**
 * .
 * class Rectangle
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] arr;
    private Color color;


    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
// Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.arr = new Line[4];
        Point leftDown, leftUp, rightUp, rightDown;
        leftDown = new Point(upperLeft.getX(), upperLeft.getY() + height);
        leftUp = upperLeft;
        rightDown = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        rightUp = new Point(upperLeft.getX() + width, upperLeft.getY());
        // arr in the index 0,2 describes the vertical  lines  , index 1,3 the horizontal
        this.arr[0] = new Line(leftDown, leftUp);
        this.arr[1] = new Line(leftUp, rightUp);
        this.arr[2] = new Line(rightUp, rightDown);
        this.arr[3] = new Line(rightDown, leftDown);
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return list java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Point intersection = this.arr[i].intersectionWith(line);
            if (intersection != null) {
                list.add(intersection);
            }
        }
        return list;
    }

    /**
     * Get line arr line [ ].
     *
     * @return arr of lines
     */
    public Line[] getLineArr() {
        return this.arr;
    }

    /**
     * .
     * Return the width of the rectangle
     *
     * @return width width
     */
// Return the width and height of the rectangle
    public double getWidth() {
        return this.width;

    }

    /**
     * Sets upper left.
     *
     * @param point the point
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }

    /**
     * .
     * Return the height of the rectangle
     *
     * @return height height
     */
    public double getHeight() {
        return this.height;

    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return upper left point
     */
// Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;

    }
}