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
	 * @return normal from axis
	 */
	public Vector getNormal(Point p) {
		// Calculate the vector from the center axis to the specified point
	    Vector axisToPoint = p.subtract(axis.head);
	    // Project the vector onto the direction of the center axis
	    double projectionScalar = axisToPoint.dotProduct(axis.direction);
	    Vector projection = axis.direction.scale(projectionScalar);
	    // Subtract the projected vector from the original vector to get the normal
	    Vector normal = axisToPoint.subtract(projection).normalize();

	    // Check if the normal is the zero vector (which occurs at the center)
	    // If it is, return the direction opposite to the axisRay as the normal
	    return normal.equals(Vector.ZERO) ? axis.direction.scale(-1).normalize() : normal;
	}
	

}
