package geometries;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;
//Bella & Rachel

/**
 * this class inherits from RadialGeometry
 * the constructor accepts a double and a Ray
 * fields include: axis and radius which was inherited 
 * public methods consist of: getNormal
 * no overridden methods
 */
public class Tube extends RadialGeometry {
	protected Ray axis;
	
	/**
	 * @param radius (double)
	 * @param axis (double) 
	 * constructor calls parents c-tor with received radius and sets the axis
	 */
	public Tube(double radius, Ray axis) {
		super(radius);
		this.axis = axis;
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
