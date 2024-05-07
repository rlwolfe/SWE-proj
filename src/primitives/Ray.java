package primitives;

import java.util.List;

/**
 * Bella & Rachel
 * constructors accept a point and a vector
 * fields include: head, direction
 * public methods consist of: equals
 * Overridden methods is: toSring 
 */
public class Ray {
	/**
	 * head of the ray 
	 */
	final public Point head;
	/**
	 * direction of the ray
	 */
	final public Vector direction;
		
	/**
	 * @param head (head)
	 * @param direction (vector)
	 * constructor that receive a point and a vector and assigns them
	 */
	public Ray(Point head, Vector direction) {
		this.head = head;
		this.direction = direction.normalize();
	}

	/**
	 * @return result if called object equals given object
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		return (obj instanceof Ray other &&
				this.head.equals(other.head) &&
				this.direction.equals(other.direction));
	}

	@Override
	public String toString() {
		return "head=" + head + ", direction=" + direction;
	}
	
	/**
	 * @param t (double) 
	 * @return point on the ray that passes through given double ((The method calculates a point on the beam line, at a given distance from the beam’s head))
	 */
	public Point getPoint(double t) { //refactoring new code for ray and points on the ray
		if(t==0)
			return head;
		return head.add(direction.scale(t));
		}
	
	/**
	 * @param points (list of points that are on the ray)
	 * @return point that is the closest to the ray's head
	 */
	public Point findClosestPoint(List<Point> points) {
		if (points==null)
            return null;
		
		Point closest = null;
		double dist, currDist = Double.MAX_VALUE;
		for (Point point : points) {
            dist = head.distance(point);
            if (dist < currDist) {
                currDist = dist;
                closest = point;
            }
        }
		return closest;
	}
}