package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * this class inherits from Tube
 * the constructor accepts two doubles
 * fields include: height and radius which was inherited 
 * public methods consist of: getNormal
 * no overridden methods
 */
public class Cylinder extends Tube{
	private double height;
	
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
	 * @param p (Point)
	 * return null for now
	 */
	public Vector getNormal(Point p)
	{
		return null;
	}

}
