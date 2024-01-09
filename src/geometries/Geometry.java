package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {

	/**
	 * @param point
	 * @return
	 */
	public Vector getNormal(Point point);
}
