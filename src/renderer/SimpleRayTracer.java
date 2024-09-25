package renderer;

import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
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
	}

	@Override
	public Color traceRay(Ray ray) {
		List<GeoPoint> intersections = new LinkedList<GeoPoint>();
		
		for (Intersectable intersect : scene.intersectables) {
			List<GeoPoint> intersectToAdd = intersect.findGeoIntersections(ray);
			if (intersectToAdd != null)
				intersections.addAll(intersectToAdd);
		}
		if (intersections.isEmpty())
			return scene.background;
		else
			return calcColor(ray.findClosestGeoPoint(intersections));
	}

	/**
	 * @param point
	 * @return ambientLight for now
	 */
	private Color calcColor(GeoPoint gp) {
		return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
		//return scene.ambientLight.getIntensity();
	}
}
