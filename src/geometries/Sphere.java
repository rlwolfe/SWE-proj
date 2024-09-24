package geometries;
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
	protected final Point center;
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
	/**
     * Finds the intersection points between a ray and the sphere
     * @param ray (Ray) - ray intersecting with the sphere
     * @return A list of intersection points (can be empty if there are no intersections)
     */
//	@Override
//	public List<Point> findIntersections(Ray ray) {
//        //List<Point> intersections = new ArrayList<>();
//
//        // Calculate the vector from the ray's origin to the sphere's center
//        Vector rayToCenter = center.subtract(ray.getHead());
//
//        // Calculate the projection of rayToCenter onto the ray's direction
//        double tClosestApproach = ray.getDirection().dotProduct(rayToCenter);
//
//        // Calculate the distance from the ray's origin to the closest approach point
//        //Vector projection = ray.getDirection().scale(tClosestApproach);
//        //double distanceToCenterSquared = rayToCenter.lengthSquared();
//        double tSquared = tClosestApproach * tClosestApproach;
//
//        // Calculate the distance between the closest approach point and the sphere's center
//        //double dSquared = projection.subtract(rayToCenter).lengthSquared();
//        double dSquared = rayToCenter.lengthSquared() - tSquared;
//
//        // Calculate the squared radius of the sphere
//        double radiusSquared = getRadius() * getRadius();
//
//        // Calculate the half chord length between the closest approach point and the intersection points
//        double halfChordLength = Math.sqrt(radiusSquared - dSquared);
//        //double halfChordLength = Math.sqrt(dSquared - radiusSquared);
//        double d = Math.sqrt(dSquared);
//
//        // If the distance between the closest approach point and the sphere's center
//        // is greater than the radius of the sphere, there is no intersection
//        if (tClosestApproach < 0 || d >= getRadius())
//            return null;
//
//        // Calculate the distance from the ray's origin to the intersection points
//        double t1 = tClosestApproach - halfChordLength;
//        double t2 = tClosestApproach + halfChordLength;
//
//        // If the distances are negative, there are no intersections in the direction of the ray
//        if (t1 < 0 && t2 <= 0) {
//        	return null;
//        }
//        
//        else if (t1 > 0 && t2 > 0) {
//        	return List.of(ray.findRayPoint(t2), ray.findRayPoint(t1));
//            //return List.of(ray.getHead().add(ray.getDirection().scale(t1)));
//        }
//        else if (t1 < 0 && t2 > 0) {
//            return List.of(ray.findRayPoint(t2));
//        	//return List.of(ray.getHead().add(ray.getDirection().scale(t2)));
//        }
//
//        return List.of(ray.findRayPoint(t1));
//    }

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // Calculate the vector from the ray's origin to the sphere's center
        Vector rayToCenter = center.subtract(ray.getHead());

        // Calculate the projection of rayToCenter onto the ray's direction
        double tClosestApproach = ray.getDirection().dotProduct(rayToCenter);

        // Calculate the distance from the ray's origin to the closest approach point
        //Vector projection = ray.getDirection().scale(tClosestApproach);
        //double distanceToCenterSquared = rayToCenter.lengthSquared();
        double tSquared = tClosestApproach * tClosestApproach;

        // Calculate the distance between the closest approach point and the sphere's center
        //double dSquared = projection.subtract(rayToCenter).lengthSquared();
        double dSquared = rayToCenter.lengthSquared() - tSquared;

        // Calculate the squared radius of the sphere
        double radiusSquared = getRadius() * getRadius();

        // Calculate the half chord length between the closest approach point and the intersection points
        double halfChordLength = Math.sqrt(radiusSquared - dSquared);
        //double halfChordLength = Math.sqrt(dSquared - radiusSquared);
        double d = Math.sqrt(dSquared);

        // If the distance between the closest approach point and the sphere's center
        // is greater than the radius of the sphere, there is no intersection
        if (tClosestApproach < 0 || d >= getRadius())
            return null;

        // Calculate the distance from the ray's origin to the intersection points
        double t1 = tClosestApproach - halfChordLength;
        double t2 = tClosestApproach + halfChordLength;

        // If the distances are negative, there are no intersections in the direction of the ray
        if (t1 < 0 && t2 <= 0) {
        	return null;
        }
        
        else if (t1 > 0 && t2 > 0) 
        	return List.of(new GeoPoint(this, ray.findRayPoint(t2)), new GeoPoint(this, ray.findRayPoint(t1)));
        
        else if (t1 < 0 && t2 > 0) 
            return List.of(new GeoPoint(this, ray.findRayPoint(t2)));
        

        return List.of(new GeoPoint(this, ray.findRayPoint(t1)));
	}
}
