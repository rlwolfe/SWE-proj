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
		return  super.plane.getNormal();
		// this is where I get an error or failure when running the test. that the triangle is not orthogonal 
	}

	/**
	 * Finds the intersection points between a ray and the triangle
	 * @param ray (Ray) ray intersecting with the triangle
	 * @return A list of intersection points (can be empty if there are no intersections)
	 */
	@Override
	public List<Point> findIntersections(Ray ray) {
		//we need to see if its in the plane first 
		List<Point> planeIntersections = plane.findIntersections(ray);
	    if (planeIntersections == null) {
	        // If there are no intersections with the plane, return null
	        return null;
	    }
	 // Check if the intersection points are inside the triangle
	 // Check if the intersection points are inside the triangle
	    List<Point> intersections = new ArrayList<>();
	    for (Point intersection : planeIntersections) {
	        // Calculate barycentric coordinates
	    	
	        Vector v0 = vertices.get(2).subtract(vertices.get(0));
	        Vector v1 = vertices.get(1).subtract(vertices.get(0));
	        Vector v2 = intersection.subtract(vertices.get(0));

	        double dot00 = v0.dotProduct(v0);
	        double dot01 = v0.dotProduct(v1);
	        double dot02 = v0.dotProduct(v2);
	        double dot11 = v1.dotProduct(v1);
	        double dot12 = v1.dotProduct(v2);

	        double invDenom = 1 / (dot00 * dot11 - dot01 * dot01);
	        double u = (dot11 * dot02 - dot01 * dot12) * invDenom;
	        if (u < 0 || u > 1) {
	            continue;
	        }

	        double v = (dot00 * dot12 - dot01 * dot02) * invDenom;
	        if (v < 0 || v > 1) {
	            continue;
	        }

	        double w = 1 - u - v;
	        if (w >= 0 && w <= 1) {
	            // If the intersection point is inside the triangle, add it to the list
	            intersections.add(intersection);
	        }
	    }

	    return intersections.isEmpty() ? null : intersections;
	}
}
