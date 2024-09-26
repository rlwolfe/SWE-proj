package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
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
		if (intersections == null)
			return scene.background;
		GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
		return calcColor(closestPoint, ray);

//		List<GeoPoint> intersections = new LinkedList<GeoPoint>();
//		
//		for (Intersectable intersect : scene.intersectables) {
//			List<GeoPoint> intersectToAdd = intersect.findGeoIntersections(ray);
//			if (intersectToAdd != null)
//				intersections.addAll(intersectToAdd);
//		}
//		if (intersections.isEmpty())
//			return scene.background;
//		else
//			return calcColor(ray.findClosestGeoPoint(intersections), ray);
	}

	/**
	 * @param point
	 * @return the color calculated using the phong method
	 */
	private Color calcColor(GeoPoint gp, Ray r) {
		Material m = gp.geometry.getMaterial();
		Color Ie = gp.geometry.getEmission();
		Point pt = gp.point;
		Vector tempV, norm = gp.geometry.getNormal(pt);
		Color finalColor = Ie.add(scene.ambientLight.getIntensity());
		Double3 tempD1, tempD2;
		for(LightSource ls : this.scene.lights) {
			if(Util.alignZero((ls.getL(pt).dotProduct(norm)) * r.getDirection().dotProduct(norm)) > 0)
			{
				tempV = ls.getL(pt).subtract(norm.scale(ls.getL(pt).dotProduct(norm))).normalize();
				tempD1 = m.kD.scale(Math.abs(ls.getL(pt).dotProduct(norm)));
				tempD2 = m.kS.scale(Math.pow(Math.max(0, r.getDirection().scale(-1.0).normalize().dotProduct(tempV)), m.nShininess));
				finalColor = finalColor.add(ls.getIntensity(pt).scale(tempD1), ls.getIntensity(pt).scale(tempD2));
			}
		}
		return finalColor; 
		//return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
		//return scene.ambientLight.getIntensity();
	}
}
