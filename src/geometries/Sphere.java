package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * constructors accepts a double
 * fields: center
 * public methods consist of: getNormal
 * no Overridden methods
 */
public class Sphere extends RadialGeometry{
	private final Point center;
	/**
	 * @param radius
	 * constructor accepts radius and sends it to the parent c-tor and center as null
	 */
	public Sphere(double radius) {
		super(radius);
		this.center = null;
	}
	
	/**
	 * @param p (Point)
	 * @return null for now
	 */
	public Vector getNormal(Point p) {
		return null;
	}
	

}
