package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Bella & Rachel
 * abstract base class for rayTracer
 */
public abstract class RayTracerBase {
	protected Scene scene;

	/**
	 * constructor
	 * @param s is assigned to scene
	 */
	public RayTracerBase(Scene s) {
		scene = s;
	}
	
	/**
	 * @param ray
	 * @return a color
	 */
	public abstract Color traceRay(Ray ray) ;
	
}
