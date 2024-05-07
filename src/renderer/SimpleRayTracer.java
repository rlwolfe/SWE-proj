package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/**
 * Bella & Rachel
 * inherits and implements from RayTracerBase
 */
public class SimpleRayTracer extends RayTracerBase {

	/**
	 * @param s
	 */
	public SimpleRayTracer(Scene s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color traceRay(Ray ray) {
		Point point = ray.findClosestPoint(scene.geometries.findIntersections(ray)); 
		if (point == null)
			return scene.background;
		return calcColor(point);
	}

	/**
	 * @param point
	 * @return ambientLight for now
	 */
	private Color calcColor(Point point) {
		return scene.ambientLight.getIntensity();
	}
}
