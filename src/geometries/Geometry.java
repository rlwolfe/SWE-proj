package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Bella & Rachel
 * Geometry interface
 * no fields or overridden methods
 * public methods consist of: getNormal
 */
public interface Geometry {

	/**
	 * @param point
	 * @return nothing because it is a method in an interface to be inherited by children 
	 */
	public Vector getNormal(Point point);
}
