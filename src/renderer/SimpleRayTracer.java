package renderer;

import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable;
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
		List<Point> intersections = new LinkedList();
		
		for (Intersectable intersect : scene.intersectables) {
			List<Point> intersectToAdd = intersect.findIntersections(ray);
			if (intersectToAdd != null)
				intersections.addAll(intersectToAdd);
		}
		//Point point = ray.findClosestPoint(scene.geometries.findIntersections(ray)); 
		//if (point == null)
		if (intersections.isEmpty())
			return scene.background;
		else
			return calcColor(ray.findClosestPoint(intersections));
	}

	/**
	 * @param point
	 * @return ambientLight for now
	 */
	private Color calcColor(Point point) {
		return scene.ambientLight.getIntensity();
	}
}
