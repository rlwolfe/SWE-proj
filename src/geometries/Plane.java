package geometries;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * Bella & Rachel
 * this class inherits from Geometry
 * the constructor accepts 3 Points
 * fields include: point and normal
 * public methods consist of: getNormal with Point parameters and without
 * no overridden methods
 */
public class Plane extends Geometry {
    private final Point point; // 3 points
    private final Vector normal; // 3 points with a direction 

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
    	
    	if (normal.equals(Point.ZERO)) {
    		throw new IllegalArgumentException("These point are on the same line");
    	}
    }   

    /**
     * @param point
     * @param normal
     * constructor that receives a Point and a Vector and assigns them to the object
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal;// .normalize(); // makes sure it is normalizes ( made into a length of 1) 
        //should check validity
    }
   
    /**
	 * @param point (Point)
     * @return normal of the object with given point
     */
    public Vector getNormal(Point point) {
        return normal.normalize(); // make sure it is normalized
    }
    
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
    	if(point.equals(ray.getHead()))
    		return null;

    	//List<Point> intersections = new LinkedList<>(); // Use List interface

    	// Calculate the dot product between the ray's direction and the plane's normal 
    	double denom = normal.dotProduct(ray.getDirection());

    	// If the dot product is zero, the ray is parallel to the plane
    	if (Util.alignZero(denom) == 0) {
    		// Ray is parallel to the plane, no intersection
    		return null; // Return empty list instead of null
    	}

    	// Calculate the distance from the ray's origin to the plane
    	double numer = normal.dotProduct(point.subtract(ray.getHead()));
    	double t = numer / denom; 

    	// Ensure the intersection point is in front of the ray's head (t > 0)
    	if (t <= 0) 
    		return null;
    	
    	return List.of(new GeoPoint(this, ray.findRayPoint(t))); // Return list of intersection points
    }
}
   
