package geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
//Bella & Rachel
/**
 * constructors accepts a double
 * fields: center
 * public methods consist of: getNormal
 * no Overridden methods
 */
public class Sphere extends RadialGeometry{
	private final Point center;
	/**
	 * @param center
	 * @param radius
	 * constructor accepts radius and sends it to the parent c-tor and center as null
	 */
	public Sphere(Point center, double radius) {
		super(radius);
		this.center = center;
	}
	
	/**
	 * @param p (Point)
	 * @return normal from center of sphere
	 */
	public Vector getNormal(Point p) {
		Vector normal = p.subtract(center);
        // Normalize the vector to get a unit vector
        return normal.normalize();
	}

	public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = new ArrayList<>();

        // Calculate the vector from the ray's origin to the sphere's center
        Vector rayToCenter = center.subtract(ray.head);

        // Calculate the projection of rayToCenter onto the ray's direction
        double tClosestApproach = ray.direction.dotProduct(rayToCenter);

        // Calculate the distance from the ray's origin to the closest approach point
        Vector projection = ray.direction.scale(tClosestApproach);
        double distanceToCenterSquared = rayToCenter.lengthSquared();

        // Calculate the distance between the closest approach point and the sphere's center
        double dSquared = projection.subtract(rayToCenter).lengthSquared();

        // Calculate the squared radius of the sphere
        double radiusSquared = radius * radius;

        // If the distance between the closest approach point and the sphere's center
        // is greater than the radius of the sphere, there is no intersection
        if (dSquared > radiusSquared)
            return null;

        // Calculate the half chord length between the closest approach point and the intersection points
        double halfChordLength = Math.sqrt(radiusSquared - dSquared);

        // Calculate the distance from the ray's origin to the intersection points
        double t1 = tClosestApproach - halfChordLength;
        double t2 = tClosestApproach + halfChordLength;

     // If the distances are negative, there are no intersections in the direction of the ray
        if (t1 >= 0) {
            Point intersection1 = ray.head.add(ray.direction.scale(t1));
            intersections.add(intersection1);
        }
        if (t2 >= 0) {
            Point intersection2 = ray.head.add(ray.direction.scale(t2));
            intersections.add(intersection2);
        }

        if (intersections.isEmpty()) {
            return null;
        }

        return intersections;
    }
}
