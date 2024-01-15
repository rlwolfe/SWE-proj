package geometries;
import primitives.Point;
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
	public Vector getNormal(Point p) {
		return super.getNormal(p);
	}
}
