package geometries;
import java.util.ArrayList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Bella & Rachel
 * this class inherits from Geometry
 * the constructor accepts 3 Points
 * fields include: point and normal
 * public methods consist of: getNormal with Point parameters and without
 * no overridden methods
 */
public class Plane implements Geometry {
    Point point;
    Vector normal;

    /**
     * @param p1 (Point)
     * @param p2 (Point)
     * @param p3 (Point)
     * constructor that receives 3 coordinates, calculates and assigns them to the object
     */
    public Plane(Point p1, Point p2, Point p3){
    	this.point = p1;
    	Vector side1 = p2.subtract(p1);
        Vector side2 = p3.subtract(p1);
        this.normal = side1.crossProduct(side2);
        // For now, you are just approximating the normal vector based on the cross product.
    }
    
    /**
     * @param point
     * @param normal
     * constructor that receives a Point and a Vector and assigns them to the object
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
        //should check validity
    }
    
    /**
     * @return current normal of the object
     */
    public Vector getNormal() {
    	return normal.normalize();
    }
    
    /**
	 * @param point (Point)
     * @return normal of the object with given point
     */
    public Vector getNormal(Point point) {
    	Vector v1 = point.subtract(this.point);
    	Vector normalAtPoint = v1.crossProduct(normal);
    	// Normalize the result
        return normalAtPoint.normalize();
    }
    /**
     * Finds the intersection points between a ray and this plane
     * @param ray (Ray) ray intersecting with the plane
     * @return A list of intersection points (can be empty if there are no intersections)
     */
	@Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = new ArrayList<>();

        // Calculate the dot product between the ray's direction and the plane's normal
        double denom = ray.direction.dotProduct(normal);

        // If the dot product is zero, the ray is parallel to the plane
        if (isZero(denom)) {
            // Ray is parallel to the plane, no intersection
            return null;
        }

        // Calculate the distance from the ray's origin to the plane
        Vector p0l0 = point.subtract(ray.head);
        double t = p0l0.dotProduct(normal) / denom; // ?alignZero(nQMinusP0 / nv);

        // Ensure the intersection point is in front of the ray's head (t > 0)
        if (t > 0) {
            // Calculate the intersection point and add it to the list
            Point intersection = ray.head.add(ray.direction.scale(t));
            intersections.add(intersection);
        }
        return null;
    }
}
   
