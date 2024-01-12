package geometries;
import primitives.Point;
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
	 * @param height (double) 
	 * constructor calls parents c-tor with received radius and sets the height 
	 */
	public Cylinder(double radius, double height) {
		super(radius);
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
