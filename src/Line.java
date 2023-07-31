// Naor Nahman 207829185

/**
 * .
 * class line
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * . constructor .
     *
     * @param start point
     * @param end   point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * . constructor .
     *
     * @param x1 double
     * @param y1 double
     * @param x2 double
     * @param y2 double
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * .
     * // Return the length of the line
     * accessors
     *
     * @return double length
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * .
     * Returns the middle point of the line
     * accessors
     *
     * @return point
     */
    public Point middle() {
        return new Point(((this.start.getX() + this.end.getX()) / 2), ((this.start.getY() + this.end.getY()) / 2));
    }

    /**
     * .
     * Returns the start point of the line
     * accessors
     *
     * @return point
     */
    public Point start() {
        return this.start;
    }

    /**
     * .
     * Returns the end point of the line
     * accessors
     *
     * @return point
     */
    public Point end() {
        return this.end;
    }

    /**
     * .
     * Function  calculate if two lines have intersection point in the infinite term
     *
     * @param a line
     * @param b line
     * @return intersection Point or null
     */
    public static Point pointFind(Line a, Line b) {
        //took the inspiration of this mathematical equation from geek
        double thisEquation = ((a.end.getY() - a.start.getY()) * a.start.getX()) + ((a.start.getX() - a.end.getX())
                * a.start.getY());
        double otherEquation = ((b.end.getY() - b.start.getY()) * b.start.getX()) + ((b.start.getX()
                - b.end.getX()) * b.start.getY());
        // asking if lines are parallel, if yes so the lines are not intersected
        double determinant = ((a.end.getY() - a.start.getY()) * (b.start.getX() - b.end.getX()))
                - ((b.end.getY() - b.start.getY()) * (a.start.getX() - a.end.getX()));
        if (determinant == 0) {
            return null;
        } else {
            double x = (thisEquation * (b.start.getX() - b.end.getX()) - otherEquation * (a.start.getX()
                    - a.end.getX())) / determinant;
            double y = (otherEquation * (a.end.getY() - a.start.getY()) - thisEquation * (b.end.getY()
                    - b.start.getY())) / determinant;
            return new Point(x, y);

        }
    }

    /**
     * .
     * Function  that check if one line is  contained in the other line
     *
     * @param a line
     * @param b line
     * @return true/false
     */
    public boolean isContained(Line a, Line b) {
        if (!sameIncline(a, b)) {
            return false;
        }
        // check who is the bigger line (longer)
        if (a.start.distance(a.end) > b.start.distance(b.end)) {
            // line a is bigger
            if (a.pointInLine(b.start) && a.pointInLine(b.end)) {
                return true;
            }
            // line  b is bigger
        } else if (b.pointInLine(a.start) && b.pointInLine(a.end)) {
            return true;
        }
        // line with same length, only option left for contained is to be equals
        return a.equals(b);
    }

    /**
     * .
     * Function  calculate if two lines have the same incline if yes return true,if not false
     *
     * @param a line
     * @param b line
     * @return true/false
     */
    public boolean sameIncline(Line a, Line b) {
        double inclinea = (a.start.getY() - a.end.getY()) / (a.start.getX() - a.end.getX());
        double inclinb = (b.start.getY() - b.end.getY()) / (b.start.getX() - b.end.getX());
        return inclinb == inclinea;
    }

    /**
     * .
     * Function  calculate if a point is in the line
     *
     * @param point
     * @return true/false
     */
    public boolean pointInLine(Point point) {
        if (point == null) {
            return false;
        }
        return (this.start.getX() <= point.getX() && point.getX() <= this.end.getX()
                || this.end.getX() <= point.getX() && point.getX() <= this.start.getX())
                && (this.start.getY() <= point.getY() && point.getY() <= this.end.getY()
                || this.end.getY() <= point.getY() && point.getY() <= this.start.getY());

        //  float a = (float) this.start.distance(point);
        //  float b = (float) point.distance(this.end);
        //  float c = (float) this.start.distance(this.end);
        //   return (a + b == c);
    }

    /**
     * .
     * Function  that check if a point is in the range of two lines
     *
     * @param a     line
     * @param b     line
     * @param point point
     * @return true/false
     */
    public boolean pointInRange(Line a, Line b, Point point) {
         return a.pointInLine(point) && b.pointInLine(point);
        /* fixing the line that start is the point with the lower x value(than end point)
        Line aCheck;
        Line bCheck;
        if (a.start.getX() > a.end.getX()) {
            aCheck = new Line(a.end, a.start);
        } else {
            aCheck = new Line(a.start, a.end);
        }
        if (b.start.getX() > b.end.getX()) {
            bCheck = new Line(b.end, b.start);
        } else {
            bCheck = new Line(b.start, b.end);
        }
   /*           if (aCheck.start().getX() == aCheck.end.getX()) {
            return aCheck.start.getX() <= point.getX() && point.getX() <= aCheck.end.getX()
                    && bCheck.start.getX() <= point.getX() && point.getX() <= bCheck.end.getX()
                    && aCheck.start.getY() <= point.getY() && point.getY() <= aCheck.end.getY();
        } else if (bCheck.start().getX() == bCheck.end.getX()) {
            return aCheck.start.getX() <= point.getX() && point.getX() <= aCheck.end.getX()
                    && bCheck.start.getX() <= point.getX() && point.getX() <= bCheck.end.getX()
                    && bCheck.start.getY() <= point.getY() && point.getY() <= bCheck.end.getY();
        }
        /*

        return aCheck.start.getX() <= point.getX() && point.getX() <= aCheck.end.getX()
                && bCheck.start.getX() <= point.getX() && point.getX() <= bCheck.end.getX();
        */
    }

    /**
     * .
     * Function Returns true if the lines intersect, false otherwise
     *
     * @param other line
     * @return true/false
     */
    public boolean isIntersecting(Line other) {
        Line thisLine = new Line(this.start, this.end);
        //asking if the lines equals/contained/continuance of each other, if one of them is true so the lines intersect
        if (thisLine.equals(other) || thisLine.exactOnePointShared(other) != null || isContained(thisLine, other)) {
            return true;
        }
        Point pointIntersect = pointFind(thisLine, other);
        if (pointIntersect == null) {
            return false;
        }
        // asking if the lines are contained even in 'ultimate range'-mean in x=(- infinity , + infinity) as an equation
        // in Coordinate system
        return pointInRange(thisLine, other, pointIntersect);
    }

    /**
     * .
     * Function return point of the two line share the same start / end point (exact one of them, can be start-end),
     * null otherwise
     *
     * @param a line
     * @return return point or null if they dont share
     */
    public Point exactOnePointShared(Line a) {
        if (a.start.equals(this.start) && !a.end.equals(this.end)) {
            // check if not Contained
            if (!a.pointInLine(this.end) && !this.pointInLine(a.end)) {
                return a.start;
            }
        }
        if (a.start.equals(this.end) && !a.end.equals(this.start)) {
            // check if not Contained
            if (!a.pointInLine(this.start) && !this.pointInLine(a.end)) {
                return a.start;
            }
        }
        if ((a.end.equals(this.start) && !a.start.equals(this.end))) {
            // check if not Contained
            if (!a.pointInLine(this.end) && !this.pointInLine(a.start)) {
                return a.end;
            }
        }
        if ((a.end.equals(this.end) && !a.start.equals(this.start))) {
            // check if not Contained
            if (!a.pointInLine(this.start) && !this.pointInLine(a.start)) {
                return a.end;
            }
        }
        return null;
    }

    /**
     * .
     * Function Returns the intersection point if the lines intersect,and null otherwise.
     *
     * @param other line
     * @return true/false
     */
    public Point intersectionWith(Line other) {
        Line thisLine = new Line(this.start, this.end);
        // point does not  exist
        if (!thisLine.isIntersecting(other)) {
            return null;
        }
        //more than 1 point
        if (thisLine.equals(other) || isContained(thisLine, other)) {
            return null;
            // if not contained and equlas, check if share exact 1 point
        } else {
            Point pointCheck = thisLine.exactOnePointShared(other);
            if (pointCheck != null) {
                return pointCheck;
            }
        }
        Point point = pointFind(thisLine, other);
        if (point == null) {
            return null;
        } else if (pointInRange(thisLine, other, point)) {
            return point;
        }
        return null;
    }

    /**
     * .
     * Function // If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to thestart of the line.
     *
     * @param rect line
     * @return closestPoint
     */
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> list;
        list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        if (this.start.distance(list.get(0)) < this.start.distance(list.get(1))) {
            return list.get(0);
        } else {
            return list.get(1);
        }
    }

  /*  public Point clostestPoint(java.util.List<Point> list) {
        Point start = this.start;
        Point minDistance = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance(start) < minDistance.distance(start)) {
                minDistance = list.get(i);
            }
        }
        return minDistance;
    }
    */


    /**
     * .
     * Function  equals -- return true is the lines are equal, false otherwise
     *
     * @param other line
     * @return true/false
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

}
