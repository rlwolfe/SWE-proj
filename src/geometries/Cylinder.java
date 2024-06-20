package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Bella & Rachel
 * this class inherits from Tube
 * the constructor accepts two doubles
 * fields include: height and radius which was inherited 
 * public methods consist of: getNormal
 * no overridden methods
 */
public class Cylinder extends Tube{
	//private double height;
	protected final double height;
	/**
	 * @param radius (double)
	 * @param axis (ray)
	 * @param height (double) 
	 * constructor calls parents c-tor with received radius and sets the height 
	 */
	public Cylinder(double radius, Ray axis, double height) {
		super(radius, axis);
		this.height = height;
	}	
	
	/**
	 * @return null because we are not doing this bonus
	 */
	public Vector getNormal() { return null; }
	
	/**
	 * @param p (Point)
	 * return null because we are not doing this bonus
	 */
	public Vector getNormal(Point p) { return null; }

}
