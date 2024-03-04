package geometries;
import java.util.LinkedList;
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
    Point point; // 3 points
    Vector normal; // 3 points with a direciton 

    /**
     * @param p1 (Point)
     * @param p2 (Point)
     * @param p3 (Point)
     * constructor that receives 3 coordinates, calculates and assigns them to the object
     */
    public Plane(Point p1, Point p2, Point p3){ //fix this so that it throws an exception 
    	if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("Two or more points are equal");
        }
        
        this.point = p1;
        Vector side1 = p2.subtract(p1);
        Vector side2 = p3.subtract(p1);
        this.normal = side1.crossProduct(side2);
    	 // to get a normal you need to take two sides(vectors) 
        //and cross product, will give you a new vector that is called the normal . // this finds what is perpendicular (normal)
        // For now, you are just approximating the normal vector based on the cross product.
        }
    
    
    
    
    /**
     * @param point
     * @param normal
     * constructor that receives a Point and a Vector and assigns them to the object
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize(); // makes sure it is normalizes ( made into a length of 1) 
        //should check validity
    }
    
    /**
     * @return current normal of the object
     */
    public Vector getNormal() {
    	return normal.normalize(); // makes sure it is normalized 
    }
    
    /**
	 * @param point (Point)
     * @return normal of the object with given point
     */
    public Vector getNormal(Point point) {
    	Vector v1 = point.subtract(this.point); // you find the vector between two points 
    	Vector normalAtPoint = v1.crossProduct(normal);
    	// Normalize the result
        return normalAtPoint.normalize(); // make sure it is normalized
    }
    /**
     * Finds the intersection points between a ray and this plane
     * @param ray (Ray) ray intersecting with the plane
     * @return A list of intersection points (can be empty if there are no intersections)
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = new LinkedList<>(); // Use List interface

        // Calculate the dot product between the ray's direction and the plane's normal 
        double denom = ray.direction.dotProduct(normal);

        // If the dot product is zero, the ray is parallel to the plane
        if (isZero(denom)) {
            // Ray is parallel to the plane, no intersection
            return null; // Return empty list instead of null
        }
        
        boolean isParallel = Math.abs(denom) < 1e-10; // this is checking if the ray and plane are parallel
        
        if(isParallel)
        return null; //if it is paraellel then no intersections. 

        // Calculate the distance from the ray's origin to the plane
        Vector p0l0 = point.subtract(ray.head);
        double t = p0l0.dotProduct(normal) / denom; 

        // Ensure the intersection point is in front of the ray's head (t > 0)
        if (t > 0) {
            // Calculate the intersection point and add it to the list
            Point intersection = ray.getPoint(t);
            intersections.add(intersection);
        }

        return intersections; // Return list of intersection points
    }

}
   
