import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    private java.util.List<Collidable> list;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.list = new ArrayList<>();
    }


    /**
     * Add collidable.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.list.add(c);
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public java.util.List<Collidable> getList() {
        return this.list;
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collides
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestCollisionPoint = null;
        Collidable closestCollidable = null;
        for (int i = 0; i < this.list.size(); i++) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(this.list.get(i).getCollisionRectangle());
            if (intersection != null) {
                if (closestCollisionPoint == null) {
                    closestCollisionPoint = intersection;
                    closestCollidable = this.list.get(i);
                } else if (intersection.distance(trajectory.start())
                        < closestCollisionPoint.distance(trajectory.start())) {
                    closestCollisionPoint = intersection;
                    closestCollidable = this.list.get(i);
                }
            }
        }
        //means that the intersection point is always null, the line is not collide with any of the collides
        if (closestCollidable == null) {
            return null;
        } else {
            return new CollisionInfo(closestCollisionPoint, closestCollidable);
        }

    }

    /**
     * Gets collideables.
     *
     * @return the collideables
     */
    public List<Collidable> getCollideables() {
        return this.list;
    }

}