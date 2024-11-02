package geometries;
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
	/**
	 * @param p1 (Point)
	 * @param p2 (Point)
	 * @param p3 (Point)
	 * constructor that receives 3 points and assigns them to the object
	 */
	public Triangle(Point p1, Point p2, Point p3)
	{
		super(p1, p2, p3);
	}

	/**
	 * @param p (Point)
	 * @return the parent normal
	 */
	/*
	 * @Override public Vector getNormal(Point p) { return super.plane.getNormal(p); }
	 */

	/**
	 * Finds the intersection points between a ray and the triangle
	 * @param ray (Ray) ray intersecting with the triangle
	 * @return A list of intersection points (can be empty if there are no intersections)
	 */
	
	protected List<Point> getVertices() { return vertices; } 
	
	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<Point> intersections = plane.findIntersections(ray);
		if( intersections == null) 
			return null;
		
		//get the points of the triangle from the polygon
		List<Point> points = this.getVertices();
		
		Vector v1 = points.get(0).subtract(ray.getHead());
		Vector v2 = points.get(1).subtract(ray.getHead());
		Vector v3 = points.get(2).subtract(ray.getHead());
		
		Vector normV1 = (v1.crossProduct(v2)).normalize();
		Vector normV2 = (v2.crossProduct(v3)).normalize();
		Vector normV3 = (v3.crossProduct(v1)).normalize();
		
		double result1 = ray.getDirection().dotProduct(normV1);
		double result2 = ray.getDirection().dotProduct(normV2);
		double result3 = ray.getDirection().dotProduct(normV3);
		
		if( (result1 < 0 && result2 < 0 && result3 < 0) 
			|| (result1 > 0 && result2 > 0 && result3 > 0)) {
			return List.of(new GeoPoint( this, intersections.get(0)));
		}
		return null;
	}
}
