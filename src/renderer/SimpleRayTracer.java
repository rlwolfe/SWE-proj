package renderer;

import java.util.List;

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
	public SimpleRayTracer(Scene s) { super(s); }
	/**
	 * amount to move ray's head (shadow rays)
	 */
	private static final double DELTA = 0.1;
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final Double3 INITIAL_K = new Double3(1.0);

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		return intersections == null ? scene.background : calcColor(findClosestIntersection(ray), ray);
		// GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
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
	 * @param gp
	 * @param ls
	 * @param l
	 * @param n
	 * @return if shadow should appear or not
	 */
	private boolean unshaded(GeoPoint gp, LightSource ls, Vector l, Vector n) { 
		Vector lightDirection = l.scale(-1);	//from pt to light source
		Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
		Point point = gp.point.add(delta);
		Ray shadowRay = new Ray(point, lightDirection);
		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(shadowRay);
		
		if (intersections == null)
			return true;
		
		double lightDist = ls.getDistance(shadowRay.getHead());
		double intersectDist = shadowRay.getHead().distance(gp.point);
		for (GeoPoint geoP : intersections) {
			intersectDist = shadowRay.getHead().distance(geoP.point);
			if (intersectDist < lightDist)
				return false;
		}	//intersection list contains point closer to light source (than object at end of ray)
		return true;			//intersection list doesn't contain a blocking point from light
	}

	private Double3 transparency(GeoPoint gp, LightSource ls, Vector l, Vector n) {
		Vector lightDirection = l.scale(-1);
		Ray lightRay = new Ray(gp.point, lightDirection, n);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

		Double3 ktr = Double3.ONE;
		if (intersections == null) return ktr;

		double rayIntersectionDistance, rayLightDistance = ls.getDistance(lightRay.getHead());
		
		for (GeoPoint geopoint : intersections) {
			rayIntersectionDistance = lightRay.getHead().distance(geopoint.point);
			if (rayIntersectionDistance < rayLightDistance)
				ktr = geopoint.geometry.getMaterial().kT.product(ktr);
		}
		return ktr;
	}
	
	/**
	 * @param gp
	 * @param ray
	 * @return
	 */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
//		return scene.ambientLight.getIntensity()
//				.add(calcLocalEffects(gp, ray));
//				.add(calcGlobalEffects(gp, ray));
//				return color;
	}

	/**
	 * @param point
	 * @return the color calculated using the phong method
	 */
	private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = gp.geometry.getEmission().add(calcLocalEffects(gp, ray, k));
		return 1 == level ? color : color.add(calcGlobalEffects(gp , ray, level, k));
		
//		Material m = gp.geometry.getMaterial();
//		Color Ie = gp.geometry.getEmission();
//		Point pt = gp.point;
//		Vector tempV, norm = gp.geometry.getNormal(pt);
//		Color finalColor = Ie.add(scene.ambientLight.getIntensity());
//		Double3 tempD1, tempD2;
//		for(LightSource ls : this.scene.lights) {
//			if(Util.alignZero((ls.getL(pt).dotProduct(norm)) * r.getDirection().dotProduct(norm)) > 0)
//			{
//				if(unshaded(gp, ls, ls.getL(gp.point), gp.geometry.getNormal(gp.point))) {
//				tempV = ls.getL(pt).subtract(norm.scale(ls.getL(pt).dotProduct(norm))).normalize();
//				tempD1 = m.kD.scale(Math.abs(ls.getL(pt).dotProduct(norm)));
//				tempD2 = m.kS.scale(Math.pow(Math.max(0, r.getDirection().scale(-1.0).normalize().dotProduct(tempV)), m.nShininess));
//				finalColor = finalColor.add(ls.getIntensity(pt).scale(tempD1), ls.getIntensity(pt).scale(tempD2));
//				}
//			}
//		}
//		return finalColor; 
		//return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
		//return scene.ambientLight.getIntensity();
	}
	
	private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = Color.BLACK;
		Material material = gp.geometry.getMaterial();
		Double3 kr = material.kR;
		Double3 kkr = k.product(kr);
		Double3 kt = material.kT;
		Double3 kkt = k.product(kt);
		
		if(!kkr.lowerThan(MIN_CALC_COLOR_K)){
			Ray reflectedRay = constructReflectedRay(gp, ray.getDirection());
			color = color.add(calcGlobalEffects(reflectedRay, kr, level, kkr));
		}
		
		if(!kkt.lowerThan(MIN_CALC_COLOR_K)) {
			Ray refractedRay = constructRefractedRay(gp, ray);
			color = color.add(calcGlobalEffects(refractedRay, kt, level, kkt));
		}
		return color;
//		return calcGlobalEffect(constructRefractedRay(gp, ray), material.kR, level, k)
//				.add(calcGlobalEffect(constructReflectedRay(gp, ray), material.kT, level, k));
	}

	private Ray constructReflectedRay(GeoPoint gp, Vector vector) {
		Vector norm = gp.geometry.getNormal(gp.point).normalize();
		Vector vtr = vector.subtract(norm.scale(2 * (vector.dotProduct(norm))));
		return new Ray(gp.point, vtr, norm);
	}

	private Ray constructRefractedRay(GeoPoint gp, Ray ray) {
        return new Ray(gp.point, ray.getDirection(), gp.geometry.getNormal(gp.point));
	}

	private Color calcGlobalEffects(Ray ray, Double3 kx, int level, Double3 kkx) {
		GeoPoint gp = findClosestIntersection(ray);
		return gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);
	}

	private GeoPoint findClosestIntersection(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
	}

	private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
		//Color color = gp.geometry.getEmission();
		Vector v = ray.getDirection();
		Vector l, n = gp.geometry.getNormal(gp.point);
		double nl, nv = Util.alignZero(n.dotProduct(v));
		Material material= gp.geometry.getMaterial();
		Color color = Color.BLACK; 
		Double3 ktr;
		
		for (LightSource lightSource: scene.lights) {
			l = lightSource.getL(gp.point);
			nl= Util.alignZero(n.dotProduct(l));
			
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				ktr = transparency(gp, lightSource, l, n);
				//if (unshaded(gp, lightSource, l, n)) {
				if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
					Color lI = lightSource.getIntensity(gp.point).scale(ktr);
					color = color.add(calcDiffusive(material, nl, lI), calcSpecular(material, n, l, v, lI));
				}
			}
		}
		return color;
	}
	
	private Color calcDiffusive(Material material, double nl, Color lI) {
		nl = Math.abs(nl);
		return lI.scale(material.kD.scale(nl));
	}

	private Color calcSpecular(Material material, Vector n, Vector l, Vector v, Color lI) {
		Vector vtr = l.subtract(n.scale(2 * (l.dotProduct(n))));
		double spec = Math.max(0, v.scale(-1).dotProduct(vtr));
		double pow = Math.pow(spec, material.nShininess);
		return lI.scale(material.kS.scale(pow));
	}

}
