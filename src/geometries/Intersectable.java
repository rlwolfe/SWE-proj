package geometries;
import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * Bella & Rachel
 * Intersectable interface
 * no fields
 * public methods consist of: findIntersections
 */
public interface Intersectable {
	
	/**
	 * @param ray
	 * @return nothing because this is part of an interface 
	 */
	public List<Point> findIntersections(Ray ray);
	

}
