package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.AmbientLight;
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
		var intersections = scene.geometries.findGeoIntersections(ray);
		return intersections == null ? scene.background :
			calcColor(ray.findClosestGeoPoint(intersections), ray);
//		List<Point> intersections = new LinkedList();
//		
//		for (Intersectable intersect : scene.intersectables) {
//			List<Point> intersectToAdd = intersect.findIntersections(ray);
//			if (intersectToAdd != null)
//				intersections.addAll(intersectToAdd);
//		}
//		//Point point = ray.findClosestPoint(scene.geometries.findIntersections(ray)); 
//		//if (point == null)
//		if (intersections.isEmpty())
//			return scene.background;
//		else
//			return calcColor(ray.findClosestPoint(intersections));
	}

	/**
	 * @param point
	 * @return ambientLight for now
	 */
	private Color calcColor(GeoPoint gp, Ray r) {
		Color color = gp.geometry.getEmission();
		AmbientLight amb = scene.ambientLight;
		Color colorWAmb = color.add(amb.getIntensity());
		return colorWAmb;
		//return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
		}
//	private Color calcColor(Point point) {
//		return scene.ambientLight.getIntensity();
//	}
}
