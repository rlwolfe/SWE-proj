package geometries;
import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Bella & Rachel
 * constructors accepts three Points
 * currently doesn't have any of its own fields, inherits 3 doubles from Polygon
 * public methods consist of: getNormal
 * no Overridden methods  
 */
public class Triangle extends Polygon{
	Point P1;
	Vector normal;
	/**
	 * @param p1 (Point)
	 * @param p2 (Point)
	 * @param p3 (Point)
	 * constructor that receives 3 points and assigns them to the object
	 */
	public Triangle(Point p1, Point p2, Point p3)
	{
		super(p1, p2, p3);
		this.P1 = p1;
		this.normal = super.plane.getNormal();
	}

	/**
	 * @param p (Point)
	 * @return the parent normal
	 */
	@Override
	public Vector getNormal(Point p) {		
		return  super.plane.getNormal(p);
	}

	/**
	 * Finds the intersection points between a ray and the triangle
	 * @param ray (Ray) ray intersecting with the triangle
	 * @return A list of intersection points (can be empty if there are no intersections)
	 */
	@Override
	public List<Point> findIntersections(Ray ray) {
		
	    List<Point> intersections = new ArrayList<>();
	    

	    // Calculate the dot product between the ray's direction and the triangle's normal
	    double denom = ray.direction.dotProduct(normal);

	    // If the dot product is zero, the ray is parallel to the plane
	    if (isZero(denom)) {
	        // Ray is parallel to the plane, no intersection
	        return null; // Return an empty list
	    }

	    // Calculate the distance from the ray's origin to the triangle
	    Vector p0l0 = vertices.get(0).subtract(ray.head);
	    double t = p0l0.dotProduct(normal) / denom;

	    // Ensure the intersection point is in front of the ray's head (t > 0)
	    if (t > 0) {
	        // Calculate the intersection point
	        Point intersection = ray.getPoint(t);

	        // Add the intersection point to the list
	        intersections.add(intersection);
	    }

	    return intersections.isEmpty() ? null : intersections;
	}
}
