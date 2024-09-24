package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Bella & Rachel
 * Geometry abstract class
 * no fields or overridden methods
 * public methods consist of: getNormal
 */
public abstract class Geometry extends Intersectable {

	protected Color emission = Color.BLACK;
	
	
	/**
	 * @return the emission
	 */
	public Color getEmission() {
		return emission;
	}
	/**
	 * @param emission the emission to set
	 */
	public Geometry setEmission(Color c) {
		emission = c;
		return this;
	}
	
	
	/**
	 * @param point
	 * @return nothing because it is a method in an interface to be inherited by children 
	 */
	public abstract Vector getNormal(Point point);
}
