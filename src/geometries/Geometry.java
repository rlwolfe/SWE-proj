package geometries;

import primitives.*;

/**
 * Bella & Rachel
 * Geometry interface
 * no fields or overridden methods
 * public methods consist of: getNormal
 */
public abstract class Geometry extends Intersectable {

	/**
	 * @param point
	 * @return nothing because it is a method in an interface to be inherited by children 
	 */
	public abstract Vector getNormal(Point point);
	private Material material = new Material();
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
	public Geometry setEmission(Color e) {
		emission = e;
		return this;
	}
	

	/**
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public Geometry setMaterial(Material material) {
		this.material = material;
		return this;
	}
}
