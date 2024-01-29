package geometries;
import primitives.Point;
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
	

}
