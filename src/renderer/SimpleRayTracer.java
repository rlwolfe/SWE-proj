package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import geometries.Triangle;
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
	public SimpleRayTracer(Scene s) { super(s); }
	/**
	 * amount to move ray's head (shadow rays)
	 */
	//private static final double DELTA = 0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10;  //recursion stop conditions of reflection/transparency
	private static final Double3 INITIAL_K = new Double3(1.0);
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final double EPS = 0.1;

	/**
	 * @param gp
	 * @param ls
	 * @param l
	 * @param n
	 * @return if shadow should appear or not
	 */
	private boolean unshaded(GeoPoint gp, LightSource ls, Vector l, Vector n) { 
		Vector lightDirection = l.scale(-1); //from gp to ls
		//Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		//Point point = gp.point.add(delta);
		//Ray shadowRay = new Ray(point, lightDirection);
		Ray lightRay = new Ray(gp.point, lightDirection, n);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);
		
		if (intersections == null)
			return true;
		
		double lightDist = ls.getDistance(lightRay.getHead());
		double intersectDist = lightRay.getHead().distance(gp.point);
		for (GeoPoint geoP : intersections) {
			if (geoP.geometry.getMaterial().kT == Double3.ZERO) {
				intersectDist = lightRay.getHead().distance(geoP.point);
				if (intersectDist < lightDist)
					return false;	//intersection list contains point closer to light source
			}
		}
		return true;	//intersection list doesn't contain a blocking point from light
	}
	
	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return scene.background;
		//GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
		//return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
		return intersections == null ? scene.background : calcColor(findClosestIntersection(ray), ray);
		//return calcColor(closestPoint, ray);

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
	 * @return the color calculated using the new method from recitation
	 */
	private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
		Color color = calcLocalEffects(intersection, ray);
		return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
		/*
		 * Material m = gp.geometry.getMaterial(); Color Ie = gp.geometry.getEmission();
		 * Point pt = gp.point; Vector tempV, norm = gp.geometry.getNormal(pt); Color
		 * finalColor = Ie.add(scene.ambientLight.getIntensity()); Double3 tempD1,
		 * tempD2; for(LightSource ls : this.scene.lights) {
		 * if(Util.alignZero((ls.getL(pt).dotProduct(norm)) *
		 * r.getDirection().dotProduct(norm)) > 0) { if(unshaded(gp, ls,
		 * ls.getL(gp.point), gp.geometry.getNormal(gp.point))) { tempV =
		 * ls.getL(pt).subtract(norm.scale(ls.getL(pt).dotProduct(norm))).normalize();
		 * tempD1 = m.kD.scale(Math.abs(ls.getL(pt).dotProduct(norm))); tempD2 =
		 * m.kS.scale(Math.pow(Math.max(0,
		 * r.getDirection().scale(-1.0).normalize().dotProduct(tempV)), m.nShininess));
		 * finalColor = finalColor.add(ls.getIntensity(pt).scale(tempD1),
		 * ls.getIntensity(pt).scale(tempD2)); } } } return finalColor;

		 */
		//return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
		//return scene.ambientLight.getIntensity();
	}
	
	/**
	 * helper method of original above
	 * @param gp
	 * @param ray
	 * @return
	 */
	private Color calcColor(GeoPoint closestPoint, Ray ray) {
		return calcColor(closestPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
				.add(scene.ambientLight.getIntensity());
	}
	
	private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
		Material material = gp.geometry.getMaterial();
		return calcGlobalEffect(ray, level, k, k); //TODO FIX
		//return calcColorGLobalEffect(constructRefractedRay(gp, ray),
		//		material.kR, level, k).add(calcColorGLobalEffect(constructReflectedRay(gp, ray),
		//			material.kT, level, k));
		}
	
	private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
		Double3 kkx = k.product(kx);
		if (kkx.lowerThan(MIN_CALC_COLOR_K))
			return Color.BLACK;
		GeoPoint gp = findClosestIntersection(ray);
		
		return (gp == null ?
				scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
	}

	private Color calcLocalEffects(GeoPoint gp , Ray ray) {
		Color color = gp.geometry.getEmission();
		Vector l, n = gp.geometry.getNormal(gp.point);
		double nl, nv = Util.alignZero(n.dotProduct(ray.getDirection()));
		Material mat;
		for (LightSource lightSource : scene.lights) {
			l = lightSource.getL(gp.point);
			nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0 ) { // sign(nl) == sing(nv)
				if(unshaded(gp, lightSource, l, n)) {
					Color iL = lightSource.getIntensity(gp.point);
					//color = color.add(iL.scale(calcDiffusive(mat, nl)),
					//			iL.scale(calcSpecular(mat, n, l, nl, v)));
				}
			}
		}
		return color;
	}

	private GeoPoint findClosestIntersection(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
	}

}
